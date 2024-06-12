package Service;

import Models.Food;

import java.util.HashMap;
import java.util.List;

public interface FoodService {
    //add
    public Food add(Food food);

    //addAll
    public void addAll(List<Food> foodList);

    //alta
    public Food create();

    //baja
    public Food delete();

    //modificación
    public Food modify();

    //listado
    public void showAll();
}
