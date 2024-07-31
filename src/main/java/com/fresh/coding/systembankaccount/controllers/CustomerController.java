package com.fresh.coding.systembankaccount.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fresh.coding.systembankaccount.dtos.requests.NewCustomer;
import com.fresh.coding.systembankaccount.dtos.responses.SummaryOfCustomer;
import com.fresh.coding.systembankaccount.groups.Groups;
import com.fresh.coding.systembankaccount.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @JsonView(Groups.Internal.class)
    public List<SummaryOfCustomer> createAllCustomers(
            @RequestBody List<@Valid NewCustomer> customers
    ) {
        return customerService.createAllCustomers(customers);
    }
}
