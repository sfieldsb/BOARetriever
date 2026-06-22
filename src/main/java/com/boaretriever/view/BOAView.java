package com.boaretriever.view;

import java.util.List;

import com.boaretriever.service.BOAService;

public class BOAView {
    public void display(String URL) {
        System.out.println("Retrieving data from BOA...");
        BOAService service = new BOAService();
        List<String> data = service.RetrieveBOAData(URL);
        if (data.isEmpty() || data == null) {
            System.out.println("No relevant data found in BOA.");
            return;

        }
        System.out.println(formatList(data));
    }

    private String formatList(List<String> data) {
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            sb.append("Nueva publicación BOA:").append(item).append("\n");
        }
        return sb.toString();
    }
}
