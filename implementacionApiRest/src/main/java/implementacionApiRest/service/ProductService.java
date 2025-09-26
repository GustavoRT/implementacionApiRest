package implementacionApiRest.service;

import implementacionApiRest.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProduct(String name);

    public Product postProduct(Product product);

    public Product putProduct(Product product);

    public Product deleteProduct(int id);

}
