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
        List<String> mappedData = new ArrayList<>();
        // Logic to retrieve data from BOA
        try{
            ConfigManager config = ConfigManager.getInstance();
            String searchedWord = config.getProperty("boa.searchedWord", "informática");
            // String searchedWordURL = URLEncoder.encode(searchedWord, StandardCharsets.UTF_8.toString());
            String date = config.getProperty("boa.date", Utils.getTodayAsYYYYMMDD());
            
            System.out.println("BOA Retriever from: " + Utils.transformDateToDDMMYYYY(date) + " searching for: " + searchedWord);
            BOAController controller = new BOAController(Utils.replaceFechaPlaceholderForDate(Utils.BOA_URL_PROPERTY, date));
            BOA[] data = controller.GetBOAData();
    
            for (BOA item : data) {
                if (item.getTexto() == null) {
                    throw new IllegalArgumentException("Retrieved data is invalid");
                } else if (item.getTexto().toLowerCase().contains(searchedWord.toLowerCase())) {
                    mappedData.add(item.getTitulo());
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
