package Service;

import java.io.IOException;

public interface MenusService  {
    void initialMenu() throws IOException;
    void clientMenu() throws IOException;
    void shoppingListMenu() throws IOException;
    void shoppingListMenuBySupermarket() throws IOException;
    void privateAccessMenu() throws IOException;
    void privateAccessProductMenu();
    void privateAccessCreateProductMenu();
    void privateAccessSupermarketMenu() throws IOException;

}
