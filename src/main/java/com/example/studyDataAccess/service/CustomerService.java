package com.example.studyDataAccess.service;

import com.example.studyDataAccess.entity.Customer;
import com.example.studyDataAccess.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer register(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Long id){
        return customerRepository.findById(id);
    }

    public void cusUpdate(Long id, Customer customer){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer dbCustomer;
        if(customerOptional.isPresent()){
            dbCustomer = customerOptional.get();
        }else{
            throw new RuntimeException("Customer not found");
        }
        dbCustomer.setAge(customer.getAge());
        dbCustomer.setCustomerName(customer.getCustomerName());
        customerRepository.save(dbCustomer);
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    public Customer login(String username, String password){
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameAndPassword(username, password);
        Customer customer;
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        }else{
            throw new RuntimeException("Customer not found");
        }
        return customer;
    }

    public List<Customer> getAge(int age){
        List<Customer> customers = customerRepository.findByAgeGreaterThanEqual(age);
        return customers;
    }
}
