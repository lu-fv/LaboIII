package Service.impl;

import Enums.Category;
import Models.Product;
import Service.SupermarketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketServiceImpl implements SupermarketService {
    public List<Product> searchSalesProducts() {
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<Product> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                Product pv = objectMapper.readValue(fv, Product.class);
                if (pv.getSale()) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                Product pc = objectMapper.readValue(fc, Product.class);
                if (pc.getSale()) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                Product pd = objectMapper.readValue(fd, Product.class);
                if (pd.getSale()) {
                    listProduct.add(pd);
                }
            }

            return listProduct;
        }catch(IOException e){
            return null;
        }
    }

    public List<Product> searchSpecialProductsByName(String name){
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<Product> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                Product pv = objectMapper.readValue(fv, Product.class);
                if (pv.getProductName().contains(name)) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                Product pc = objectMapper.readValue(fc, Product.class);
                if (pc.getProductName().contains(name)) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                Product pd = objectMapper.readValue(fd, Product.class);
                if (pd.getProductName().contains(name)) {
                    listProduct.add(pd);
                }
            }
            return listProduct;

        }catch (IOException e){
            return null;
        }

    }

    public List<Product> searchProductsByCategory(Category c){
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<Product> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                Product pv = objectMapper.readValue(fv, Product.class);
                if (pv.getCategory() == c) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                Product pc = objectMapper.readValue(fc, Product.class);
                if (pc.getCategory() == c) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                Product pd = objectMapper.readValue(fd, Product.class);
                if (pd.getCategory() == c) {
                    listProduct.add(pd);
                }
            }
            return listProduct;
        }catch(IOException e){
            return null;
        }
    }

}
