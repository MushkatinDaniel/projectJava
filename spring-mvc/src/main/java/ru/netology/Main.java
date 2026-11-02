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
    // рабочая папка для Tomcat (логи, временные файлы)
    final var baseDir = Files.createTempDirectory("tomcat").toAbsolutePath().toString();

    final var tomcat = new Tomcat();
    tomcat.setBaseDir(baseDir);
    tomcat.setPort(PORT);
    tomcat.getConnector(); // создаёт HTTP-коннектор на указанном порту

    final var context = tomcat.addContext(CONTEXT_PATH, baseDir);

    // контекст Spring: настройки берутся из WebConfig
    final var springContext = new AnnotationConfigWebApplicationContext();
    springContext.register(WebConfig.class);

    // DispatcherServlet — единственный сервлет, дальше запросы раздаёт Spring
    final var dispatcherServlet = new DispatcherServlet(springContext);
    Tomcat.addServlet(context, SERVLET_NAME, dispatcherServlet).setLoadOnStartup(1);
    context.addServletMappingDecoded(SERVLET_MAPPING, SERVLET_NAME);

    tomcat.start();
    tomcat.getServer().await();
  }
}
