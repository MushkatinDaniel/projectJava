package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextConverter implements TextGraphicsConverter {
    private int maxWidth = 0;
    private int maxHeight = 0;
    private double maxRatio = 0;
    private TextColorSchema schema = new ColorSchema();


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();

        if (maxRatio > 0) {
            double imgRatio;
            if (width > height) {
                imgRatio = (double) width / height;
            } else {
                imgRatio = (double) height / width;
            }
            if (imgRatio > maxRatio) {
                throw new BadImageSizeException(imgRatio, maxRatio);
            }
        }

        int newWidth = width;
        int newHeight = height;

        if (maxWidth > 0 && width > maxWidth) {
            double ratio = (double) maxWidth / width;
            newWidth = maxWidth;
            newHeight = (int) (height * ratio);
        }

        if (maxHeight > 0 && newHeight > maxHeight) {
            double ratio = (double) maxHeight / newHeight;
            newHeight = maxHeight;
            newWidth = (int) (newWidth * ratio);
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        int[] pixel = new int[3];

        String result = "";
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, pixel)[0];
                char c = schema.convert(color);
                result += c;
                result += c;
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    public int getImageWidth() {
        return maxWidth;
    }

    public int getImageHeight() {
        return maxHeight;
    }

    public double getImageRatio() {
        return maxRatio;
    }
}
