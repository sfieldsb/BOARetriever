package com.boaretriever;

import com.boaretriever.util.Utils;
import com.boaretriever.view.BOAView;

/**
 * BOARetriever main class
 */
public class Main {
    public static void main(String[] args) {
            
            System.out.println("BOA Retriever from: " + Utils.BOA_URL_PROPERTY);
            BOAView view = new BOAView();
            view.display();
        
        

    }
}

