package Service.impl;

import Enums.Category;
import Models.Supermarket;
import Service.SupermarketService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class SupermarketServiceImpl implements SupermarketService {
    private final File supermarketFile = new File("supermarket.json");

    public Supermarket Search(String name) {

        Supermarket supermarket = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
           // List<Supermarket> supermarkets = mapper.readValue(supermarketFile, List.class); // levanto la lista de supermercado de Json
            List<Supermarket> supermarkets = mapper.readValue(supermarketFile, new TypeReference<List<Supermarket>>(){}); // levanto la lista por referencia de supermercado de Json

            for (Supermarket superM : supermarkets) {
                if (superM.getName().equals(name)) {
                    supermarket = superM;
                }

            }
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supermarket;
    }

    public void ShowListSupermarket(Supermarket supermarket) {
        if (supermarket != null) {
            System.out.println(supermarket);

        }
    }
}
