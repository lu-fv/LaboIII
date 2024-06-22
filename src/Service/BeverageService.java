package Service;

import Models.Beverage;

import java.util.List;

public interface BeverageService {
    Beverage add(Beverage beverage);
    void addAll(List<Beverage> beverageList);
    Beverage create();
    Beverage modify();
    Beverage delete();
    void showAll();
}
