package com.example.test.controller;

import com.example.test.dto.CustomerDTO;
import com.example.test.model.Customer;
import com.example.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class customerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCourses(@RequestParam Optional<Integer> page,@RequestParam Optional<String> sortBy) {
        return customerService.getAllCustomer(PageRequest.of(page.orElse(0),5,Sort.Direction.ASC,sortBy.orElse("id")));
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void createCourse(@RequestBody CustomerDTO customerDto) {
        customerService.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@PathVariable Long id, @RequestBody CustomerDTO updatedCustomerDto) {
        customerService.updateCustomer(id, updatedCustomerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }





}
