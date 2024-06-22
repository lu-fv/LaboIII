
import Service.impl.MenusServiceImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MenusServiceImpl menu = new MenusServiceImpl();
        menu.initialMenu();
    }
}

