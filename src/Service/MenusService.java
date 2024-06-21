package Service;

import java.io.IOException;

public interface MenusService  {
    public void initialMenu() throws IOException;
    public void clientMenu() throws IOException;
    public void shoppingListMenu() throws IOException;
    public void privateAccessMenu() throws IOException;
    public void privateAccessProductMenu();
    public void privateAccessCreateProductMenu();
    public void privateAccessSupermarketMenu() throws IOException;

}
