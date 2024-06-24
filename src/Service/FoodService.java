package Service;

import Models.Food;

import java.io.IOException;
import java.util.List;

public interface FoodService {
    //add
    public Food add(Food food) throws IOException;

    //addAll
    public void addAll(List<Food> foodList);

    //alta
    public Food create() throws IOException;

    //baja
    public Food delete(Integer id);

    //modificación
    public Food modify(Integer id) throws IOException;

    //listado
    public void showAll();
}
