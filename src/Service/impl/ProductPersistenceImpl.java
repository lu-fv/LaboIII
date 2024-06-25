package Service.impl;

import Models.Beverage;
import Models.Food;
import Models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class ProductPersistenceImpl {
    public static void saveFoods(FoodServiceImpl foodService) throws IOException {
        File file = new File("foods.json");
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(file, foodService);
    }

    public static FoodServiceImpl startFoodService() throws IOException {
        File file = new File("foods.json");
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists()) {
            file.createNewFile();
        }

        try {
            return mapper.readValue(file, FoodServiceImpl.class);
        } catch (IOException e) {
            return new FoodServiceImpl();
        }

    }

    public static void saveBeverages(BeverageServiceImpl beverageService) throws IOException {
        File file = new File("beverages.json");
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(file, beverageService);
    }

    public static BeverageServiceImpl startBeverageService() throws IOException {
        File file = new File("beverages.json");
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists()) {
            file.createNewFile();
        }

        try {
            return mapper.readValue(file, BeverageServiceImpl.class);
        } catch (IOException e) {
            return new BeverageServiceImpl();
        }
    }

    /*public static void saveProducts(Map<Integer, Food> foodMap, Map<Integer, Beverage> beverageMap) throws IOException {
        File file = new File("product.json");
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, Product> map = new HashMap<>();

        //TypeFactory typeFactory = mapper.getTypeFactory();
        //MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class,Product.class);
        //map = mapper.readValue(file, mapType);

        if (foodMap != null) {
            map.putAll(foodMap);
        }
        if (beverageMap != null) {
            map.putAll(beverageMap);
        }

        mapper.writeValue(file, map);
    }

    public static Map<Integer, Product> deserializeProductMap() throws IOException {
        File file = new File("product.json");
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists()) {
            file.createNewFile();
        }

        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class,Product.class);
            return mapper.readValue(file, mapType);
        } catch (IOException e) {
            return null;
        }
    }*/


}
