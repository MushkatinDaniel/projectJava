package ru.netology.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // включает @RestController, маппинги и JSON через Jackson
@ComponentScan("ru.netology") // находит @RestController, @Service, @Repository
public class WebConfig {
}
