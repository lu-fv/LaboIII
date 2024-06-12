package Service;

import Models.Supermarket;

import java.io.IOException;

public interface SupermarketService {
    public Supermarket addSupermarket();
    public void deleteSupermarket(Supermarket s);
    public void modifySupermarket(Supermarket s);
    public void supermarketList(Supermarket s);
    public void saveSupermarketInJsonFile(Supermarket s) throws IOException;


}
