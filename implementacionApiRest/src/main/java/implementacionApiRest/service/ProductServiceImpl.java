package implementacionApiRest.service;

import implementacionApiRest.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {

//EN ESTA CLASE QUES ES LA IMPLEMENTACIÓN ESTA LA LÓGICA EN LOS MÉTODOS

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1,"Creatina", 36.0 ,30),
            new Product(2,"BCCA", 18.0,132),
            new Product(3,"Whey Gold Standard", 70.0,9),
            new Product(4,"Citrulina", 28.0,7)
    ));


    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProduct(String name) {
        for(Product product: products){
            if(product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product postProduct(Product product) {
        if(product != null){
            products.add(product);
            return product;
        }
        return null;
    }

    @Override
    public Product putProduct(Product product) {
        for(Product product1: products){
            if(product1.getId() == product.getId()){
                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                product1.setAmount(product.getAmount());

                return product;
            }
        }
        return null;

    }

    @Override
    public Product deleteProduct(int id) {
        for(Product product : products){
            if(product.getId() == id){
                products.remove(product);
                return product;
            }
        }
        return null;
    }
}
