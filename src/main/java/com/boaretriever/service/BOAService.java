package com.boaretriever.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.boaretriever.controller.BOAController;
import com.boaretriever.model.BOA;
import com.boaretriever.util.ConfigManager;
import com.boaretriever.util.Utils;

public class BOAService {
    public List<String> RetrieveBOAData() {
        // Logic to retrieve data from BOA
        BOAController controller = new BOAController(Utils.replaceFechaPlaceholderForToday(Utils.BOA_URL_PROPERTY));
        BOA[] data = controller.GetBOAData();
        List<String> mappedData = new ArrayList<>();

        try{
            ConfigManager config = ConfigManager.getInstance();
            String searchedWord = config.getProperty("boa.searchedWord", "informática");
    
            for (BOA item : data) {
                if (item.getTexto() == null) {
                    throw new IllegalArgumentException("Retrieved data is invalid");
                } else if (item.getTexto().toLowerCase().contains(searchedWord.toLowerCase())) {
                    mappedData.add(item.getTexto());
                }
            }
            return mappedData;
        }catch(IOException e){
            System.out.println("Error loading configuration: " + e.getMessage());
            return mappedData;
        }
        


    }

      private String getTodayURL(String baseURL) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(formatter);
        return baseURL.replaceAll("\\{fecha\\}", today);
    }
}
