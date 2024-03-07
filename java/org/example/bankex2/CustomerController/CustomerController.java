package org.example.bankex2.CustomerController;

import org.example.bankex2.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();
    private long customerId = 1;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(customerId++);
        customers.add(customer);
        return customer;
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.setUsername(updatedCustomer.getUsername());
                customer.setBalance(updatedCustomer.getBalance());
                return customer;
            }
        }
        throw new NoSuchElementException("Customer not found");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable long id) {
        boolean removed = customers.removeIf(customer -> customer.getId() == id);
        if (removed) {
            return "Customer deleted successfully";
        } else {
            throw new NoSuchElementException("Customer not found");
        }
    }

    @PutMapping("/deposit/{id}")
    public Customer depositMoney(@PathVariable long id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                double updatedBalance = customer.getBalance() + amount;
                customer.setBalance(updatedBalance);
                return customer;
            }
        }
        throw new NoSuchElementException("Customer not found");
    }

    @PutMapping("/withdraw/{id}")
    public Customer withdrawMoney(@PathVariable long id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                double updatedBalance = customer.getBalance() - amount;
                if (updatedBalance >= 0) {
                    customer.setBalance(updatedBalance);
                    return customer;
                } else {
                    throw new IllegalArgumentException("Insufficient balance");
                }
            }
        }
        throw new NoSuchElementException("Customer not found");
    }
}
