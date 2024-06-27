package Service;

import Models.Food;

import java.io.IOException;
import java.util.List;

public interface FoodService {
    public Food add(Food food) throws IOException;

    public void addAll(List<Food> foodList);

    public Food create() throws IOException;

    private Boolean createPerishable() {
        return null;
    }

    public Food modify(Integer id) throws IOException;

    public Food delete(Integer id) throws IOException;

    public Food remove(Integer id) throws IOException;

    public void showAll();

    public void startID();
}
