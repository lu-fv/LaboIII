package Service;

import Models.Beverage;

import java.io.IOException;
import java.util.List;

public interface BeverageService {
    public Beverage add(Beverage beverage) throws IOException;
    public void addAll(List<Beverage> beverageList);
    public Beverage create() throws IOException;
    public Beverage modify(Integer id) throws IOException;
    public Beverage delete(Integer id);
    public void showAll();
}
