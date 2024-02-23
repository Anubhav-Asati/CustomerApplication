package com.example.test.service;

import com.example.test.dto.CustomerDTO;
import com.example.test.model.Customer;
import com.example.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(PageRequest id) {
        return customerRepository.findAll();
    }


    @Cacheable("customers")
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        System.out.println("Get Customer Id"+customerOptional);
        return customerOptional.orElse(null);
    }

    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setEmail(customerDTO.getEmail());
        customer.setState(customerDTO.getState());
        customer.setStreet(customerDTO.getStreet());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, CustomerDTO updatedCustomerDto) {
        Customer existingCustomer = getCustomerById(id);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(updatedCustomerDto.getFirstName());
            existingCustomer.setLastName(updatedCustomerDto.getLastName());
            existingCustomer.setCity(updatedCustomerDto.getCity());
            existingCustomer.setAddress(updatedCustomerDto.getAddress());
            existingCustomer.setState(updatedCustomerDto.getState());
            existingCustomer.setEmail(updatedCustomerDto.getEmail());
            existingCustomer.setStreet(updatedCustomerDto.getStreet());
            customerRepository.save(existingCustomer);
        }
    }


}
