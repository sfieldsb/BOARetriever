package com.boaretriever.view;

import java.util.List;

import com.boaretriever.service.BOAService;
import com.boaretriever.service.EmailService;
import com.boaretriever.util.ConfigManager;
import com.boaretriever.util.Utils;

public class BOAView {
    public void display() {
        System.out.println("Retrieving data from BOA...");
        BOAService service = new BOAService();
        List<String> data = service.RetrieveBOAData();
        if (data.isEmpty() || data == null) {
            System.out.println("No relevant data found in BOA.");
            return;

        }
        try{
            ConfigManager config = ConfigManager.getInstance();
            String searchedWord = config.getProperty("boa.searchedWord");

            switch (config.getProperty("boa.export", "console").toLowerCase()) {
                case "file":
                    System.out.println("Exporting data to file...");
                    String exportPath = config.getProperty("boa.export.path", "../exportedDocs");
                    Utils.exportToFile(data, searchedWord, exportPath);
                    break;
                case "email":
                    System.out.println("Sending data by email...");
                    new EmailService().sendBOAUpdate(data, searchedWord);
                    break;
                case "console":
                default:
                    System.out.println(formatList(data));
                    break;
            }
        }catch(Exception e){
            System.out.println("Error loading configuration: " + e.getMessage());
        }
        // System.out.println(formatList(data));
    }

    private String formatList(List<String> data) {
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            sb.append("Nueva publicación BOA:").append(item).append("\n");
        }
        return sb.toString();
    }
}
