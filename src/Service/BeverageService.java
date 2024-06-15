package Service;

import Models.Beverage;

import java.util.List;

public interface BeverageService {
    public Beverage add(Beverage beverage);
    public void addAll(List<Beverage> beverageList);
    public Beverage create();
    public Beverage modify();
    public Beverage delete();
    public void showAll();
}
