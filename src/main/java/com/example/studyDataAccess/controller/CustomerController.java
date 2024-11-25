package com.example.studyDataAccess.controller;

import com.example.studyDataAccess.entity.Customer;
import com.example.studyDataAccess.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/register")
    public String register(Customer customer) {
        Customer c = new Customer();
        c.setUsername("ms1114");
        c.setPassword("123456");
        c.setAge(30);
        c.setCustomerName("안민성");
        c.setRating("VIP");
        c.setOccupation("개발자");
        Customer cus = customerService.register(c);
        return "redirect:/";
    }

    @GetMapping("/lists")
    public String lists(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println(customers);
        model.addAttribute("customers", customers);
        return "lists";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Optional<Customer> customerOptional = customerService.getById(id);
        Customer customer;
        if (customerOptional.isPresent()){
            customer = customerOptional.get();
        }else{
            throw new IllegalArgumentException("error");
        }
        model.addAttribute("customer", customer);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id){
        Customer customer = new Customer();
        customer.setCustomerName("안민성1");
        customer.setAge(31);
        customerService.cusUpdate(id, customer);
        return "redirect:/customer/lists";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        customerService.delete(id);
        return "redirect:/customer/lists";
    }

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password, Model model){
        Customer customer = customerService.login(username, password);
        model.addAttribute("customer", customer);
        return "result";
    }

    @GetMapping("/ageList/{age}")
    public String ageList(@PathVariable int age, Model model){
        List<Customer> customers = customerService.getAge(age);
        model.addAttribute("customer", customers);
        return "ageList";
    }
}
