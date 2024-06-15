package Service;

import Enums.Category;
import Models.Supermarket;

import java.io.IOException;

public interface SupermarketService {
     Supermarket Search(String name) throws IOException;
    void ShowListSupermarket(Supermarket supermarket);
     Supermarket SearchByCategory(Category category);

}
