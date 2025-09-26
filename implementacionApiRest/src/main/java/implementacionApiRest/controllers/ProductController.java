package implementacionApiRest.controllers;

import implementacionApiRest.domain.Product;
import implementacionApiRest.service.ProductService;
import implementacionApiRest.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

     ProductService productsService = new ProductServiceImpl();

     @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> productos = productsService.getAllProducts();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name){
         Product product = productsService.getProduct(name);
         if(product == null){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
         }
         return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Product product){
        productsService.postProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping
    public ResponseEntity<?> putProduct(@RequestBody Product product){
        Product product1 = productsService.putProduct(product);
        if(product1 != null){

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{name}")
                    .buildAndExpand(product.getId())
                    .toUri();
            return ResponseEntity.created(location).body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Product product = productsService.deleteProduct(id);
        if(product != null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
}

