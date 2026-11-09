package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.netology.config.WebConfig;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
  private static final int PORT = 8080;
  private static final String CONTEXT_PATH = "";
  private static final String SERVLET_NAME = "dispatcher";
  private static final String SERVLET_MAPPING = "/";

  public static void main(String[] args) throws IOException, LifecycleException {
   
    final var baseDir = Files.createTempDirectory("tomcat").toAbsolutePath().toString();

    final var tomcat = new Tomcat();
    tomcat.setBaseDir(baseDir);
    tomcat.setPort(PORT);
    tomcat.getConnector(); 

    final var context = tomcat.addContext(CONTEXT_PATH, baseDir);

  
    final var springContext = new AnnotationConfigWebApplicationContext();
    springContext.register(WebConfig.class);

   
    final var dispatcherServlet = new DispatcherServlet(springContext);
    Tomcat.addServlet(context, SERVLET_NAME, dispatcherServlet).setLoadOnStartup(1);
    context.addServletMappingDecoded(SERVLET_MAPPING, SERVLET_NAME);

    tomcat.start();
    tomcat.getServer().await();
  }
}
