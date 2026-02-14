
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);

        String json = listToJson(list);

        writeString(json, "data.json");

        List<Employee> listFromXml = parseXML("data.xml");
        String jsonFromXml = listToJson(listFromXml);
        writeString(jsonFromXml, "data2.json");
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {

        try (FileReader fileReader = new FileReader(fileName)) {

            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();

            strategy.setType(Employee.class);

            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(fileReader)
                    .withMappingStrategy(strategy)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            System.out.println("Ошибка чтения CSV файла: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String listToJson(List<Employee> list) {

        GsonBuilder builder = new GsonBuilder();

        builder.setPrettyPrinting();

        Gson gson = builder.create();

        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        String json = gson.toJson(list, listType);

        return json;
    }

    private static void writeString(String json, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write(json);

            fileWriter.flush();

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<Employee> parseXML(String fileName) {

        List<Employee> employeeList = new ArrayList<>();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(fileName));

            Node root = document.getDocumentElement();
            
            NodeList nodeList = root.getChildNodes();


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    Employee employee = new Employee(

                            Long.parseLong(getTagValue("id", element)),

                            getTagValue("firstName", element),

                            getTagValue("lastName", element),

                            getTagValue("country", element),

                            Integer.parseInt(getTagValue("age", element))
                    );

                    employeeList.add(employee);
                }
            }

        } catch (ParserConfigurationException e) {

            System.out.println("Ошибка конфигурации парсера: " + e.getMessage());
            e.printStackTrace();

        } catch (SAXException e) {

            System.out.println("Ошибка парсинга XML: " + e.getMessage());
            e.printStackTrace();


        } catch (IOException e) {

            System.out.println("Ошибка при чтении XML файла: " + e.getMessage());
            e.printStackTrace();
        }

        return employeeList;
    }

    private static String getTagValue(String tag, Element element) {

        NodeList nodeList = element.getElementsByTagName(tag);

        return nodeList.item(0).getChildNodes().item(0).getNodeValue();
    }
}