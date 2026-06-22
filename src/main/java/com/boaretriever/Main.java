package com.boaretriever;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.boaretriever.view.BOAView;

/**
 * BOARetriever main class
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();           
        InputStream stream = loader.getResourceAsStream("application.properties");
        prop.load(stream);
        
        System.out.println("BOA Retriever. URL: "+ prop.getProperty("boa.url"));
        BOAView view = new BOAView();
        view.display(prop.getProperty("boa.url"));
    }
}

