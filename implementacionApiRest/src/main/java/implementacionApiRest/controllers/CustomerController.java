package implementacionApiRest.controllers;

import implementacionApiRest.domain.Customer;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/clientes")
    public List<Customer> getClientes(){
        return customers;
    }

    @GetMapping("/clientes/{username}")
    public Customer getCliente(@PathVariable String username){
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return customer;
            }
        }
        return null;
    }

    @PostMapping("/clientes")
    public Customer postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    @PutMapping("clientes")
    public Customer putClientes(@RequestBody Customer customer){
        for(Customer customer1: customers){
            if(customer.getID() == customer1.getID()){
                customer1.setName(customer.getName());
                customer1.setUsername(customer.getUsername());
                customer1.setPassword(customer.getPassword());

                return customer1;
            }
        }
        return null;
    }

    @DeleteMapping("/clientes/{ID}")
    public Customer deleteClientes(@PathVariable int ID){
        for(Customer customer: customers){
            if(customer.getID() == ID){
                customers.remove(customer);

                return customer;
            }
        }
        return null;
    }

    @PatchMapping("/clientes")
    public Customer patchCliente(@RequestBody Customer customer){
        for(Customer c: customers){
            if(c.getID() == customer.getID()){
                if(customer.getName() != null){
                    c.setName(customer.getName());
                }if(customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }if(customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
                return c;
            }
        }
        return null;
    }
}
