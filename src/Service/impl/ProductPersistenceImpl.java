package Service.impl;

import Models.Food;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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
}
