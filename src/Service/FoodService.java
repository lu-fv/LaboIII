package Service;

import Models.Food;

import java.util.HashMap;
import java.util.List;

public interface FoodService {
    //add
    Food add(Food food);

    //addAll
    void addAll(List<Food> foodList);

    //alta
    Food create();

    //baja
    Food delete();

    //modificación
    Food modify();

    //listado
    void showAll();
}
