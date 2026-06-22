package com.boaretriever.service;

import java.util.ArrayList;
import java.util.List;

import com.boaretriever.controller.BOAController;
import com.boaretriever.model.BOA;

public class BOAService {
    public List<String> RetrieveBOAData(String URL) {
        // Logic to retrieve data from BOA
        BOAController controller = new BOAController(URL);
        BOA[] data = controller.GetBOAData();
        List<String> mappedData = new ArrayList<>();

        for (BOA item : data) {
            if (item.getTexto() == null) {
                throw new IllegalArgumentException("Retrieved data is invalid");
            } else if (item.getTexto().contains("informática")) {
                mappedData.add(item.getTexto());
            }
        }
        return mappedData;


    }
}
