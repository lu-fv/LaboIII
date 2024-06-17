package Service.impl;

import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;
import Service.ProductForSaleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductForSaleServiceImpl implements ProductForSaleService {
    private final File file = new File("product.json");

    public void addProductForSale(Supermarket s) throws IOException {
        Integer opcId;
        Scanner sc = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Product> mapProducts = new HashMap<>();

        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, Product.class);

        mapProducts = mapper.readValue(file, mapType);

        for (Map.Entry<Integer, Product> entry : mapProducts.entrySet()) {
            System.out.println(entry);
        }
        System.out.println("ELija el id del producto que desea agregar:");
        do {
            opcId = sc.nextInt();
            if (!validationId(opcId, mapProducts)) {
                System.out.println("Ingrese por favor un Id correcto!!!");
            }
        } while (!validationId(opcId, mapProducts));

        System.out.println("Ingrese el precio del producto nuevo : ");
        Double newPrice = sc.nextDouble();

        ProductForSale newProductForSale = new ProductForSale(mapProducts.get(opcId), newPrice, false);
        s.getProductListHashSet().add(newProductForSale);

        modifySupermarketListProduct(s);
    }

    public Boolean validationId(Integer id, Map<Integer, Product> map) {
        Boolean flag = false;
        for (Map.Entry<Integer, Product> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {
                flag = true;
            }
        }
        return flag;
    }
}
