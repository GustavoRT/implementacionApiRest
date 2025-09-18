package implementacionApiRest.controllers;

import implementacionApiRest.domain.Customer;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1,"Gustavo Rios", "Gart0","123"),
            new Customer(2,"Elena Garcia", "elen","123"),
            new Customer(3,"Pedro Lopez", "plopez","123"),
            new Customer(4,"Marcos Bermejo", "marcosb","123")
    ));

    @GetMapping("clientes")
    public List<Customer> getCustomers(){
        return customers;
    }

    @GetMapping("clientes/{username}")
    public Customer getCustomer(@PathVariable String username){
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return customer;
            }
        }
        return null;
    }
}
