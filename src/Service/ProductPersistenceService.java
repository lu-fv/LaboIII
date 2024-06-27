package Service;

import Service.impl.BeverageServiceImpl;
import Service.impl.FoodServiceImpl;

import java.io.IOException;

public interface ProductPersistenceService {
    static void saveFoods(FoodServiceImpl foodService) throws IOException {

    }

    static FoodServiceImpl startFoodService() throws IOException {
        return null;
    }

    static void saveBeverages(BeverageServiceImpl beverageService) throws IOException{

    }

    static BeverageServiceImpl startBeverageService() throws IOException {
        return null;
    }
}
