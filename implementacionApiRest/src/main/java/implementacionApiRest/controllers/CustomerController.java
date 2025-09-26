package implementacionApiRest.controllers;

import implementacionApiRest.domain.Customer;
import jakarta.servlet.Servlet;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1,"Gustavo Rios", "Gart0","123"),
            new Customer(2,"Elena Garcia", "elen","123"),
            new Customer(3,"Pedro Lopez", "plopez","123"),
            new Customer(4,"Marcos Bermejo", "marcosb","123")
    ));

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getClientes(){
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{username}")
    //@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getCliente(@PathVariable String username){
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: "+username);
    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);
       // return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado con éxito: "+customer.getUsername());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        //opción 1 mostraremos la respuesta 201 sin cuerpo de del mensaje, pero con la URI del recurso
        //return ResponseEntity.created(location).build();
        //Opción 2 mostramos la respuesta 201 con contenido en el cuerpo del mensaje y la URI del recurso
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping
    //@RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putClientes(@RequestBody Customer customer){
        for(Customer customer1: customers){
            if(customer.getID() == customer1.getID()){
                customer1.setName(customer.getName());
                customer1.setUsername(customer.getUsername());
                customer1.setPassword(customer.getPassword());

                //Devolvemos un código de respuesta 204 que es envio exitoso pero sin respuesta
                return ResponseEntity.noContent().build();
            }
        }
        //Devolvemos un código de respuesta 404 recurso no encontrado
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{ID}")
    //@RequestMapping(value = "/{ID}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClientes(@PathVariable int ID){
        for(Customer customer: customers){
            if(customer.getID() == ID){
                customers.remove(customer);

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    //@RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
        for(Customer c: customers){
            if(c.getID() == customer.getID()){
                if(customer.getName() != null){
                    c.setName(customer.getName());
                }if(customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }if(customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
                return ResponseEntity.status(HttpStatus.CREATED).body("Cliente modificado con éxito");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente "+customer.getID()+" no encontrado");
    }
}

