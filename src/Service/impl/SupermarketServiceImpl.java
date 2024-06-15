package Service.impl;

import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import Service.SupermarketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketServiceImpl implements SupermarketService {
    public List<ProductForSale> searchSalesProducts() {
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<ProductForSale> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                ProductForSale pv = objectMapper.readValue(fv, ProductForSale.class);
                if (pv.getOnSale()) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                ProductForSale pc = objectMapper.readValue(fc, ProductForSale.class);
                if (pc.getOnSale()) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                ProductForSale pd = objectMapper.readValue(fd, ProductForSale.class);
                if (pd.getOnSale()) {
                    listProduct.add(pd);
                }
            }
            return listProduct;
        }catch(IOException e){
            return null;
        }
    }

    public List<ProductForSale> searchSpecialProductsByName(String name){
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<ProductForSale> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                ProductForSale pv = objectMapper.readValue(fv, ProductForSale.class);
                if (pv.getProduct().getProductName().contains(name)) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                ProductForSale pc = objectMapper.readValue(fc, ProductForSale.class);
                if (pc.getProduct().getProductName().contains(name)) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                ProductForSale pd = objectMapper.readValue(fd, ProductForSale.class);
                if (pd.getProduct().getProductName().contains(name)) {
                    listProduct.add(pd);
                }
            }
            return listProduct;

        }catch (IOException e){
            return null;
        }

    }

    public List<ProductForSale> searchProductsByCategory(Category c){
        try {
            File fv = new File("vea.json");
            File fc = new File("carrefour.json");
            File fd = new File("disco.json");

            List<ProductForSale> listProduct = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            while (fv.canRead()) {
                ProductForSale pv = objectMapper.readValue(fv, ProductForSale.class);
                if (pv.getProduct().getCategory() == c) {
                    listProduct.add(pv);
                }
            }

            while (fc.canRead()) {
                ProductForSale pc = objectMapper.readValue(fc, ProductForSale.class);
                if (pc.getProduct().getCategory() == c) {
                    listProduct.add(pc);
                }
            }

            while (fd.canRead()) {
                ProductForSale pd = objectMapper.readValue(fd, ProductForSale.class);
                if (pd.getProduct().getCategory()== c) {
                    listProduct.add(pd);
                }
            }
            return listProduct;
        }catch(IOException e){
            return null;
        }
    }

}
