package io.github.komyagin.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.Bootstrap;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class App {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        App app = context.getBean("app", App.class);
        Bootstrap.main(args);
    }

}
