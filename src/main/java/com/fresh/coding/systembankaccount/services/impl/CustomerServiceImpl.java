package com.fresh.coding.systembankaccount.services.impl;

import com.fresh.coding.systembankaccount.dtos.requests.NewCustomer;
import com.fresh.coding.systembankaccount.dtos.responses.SummaryOfCustomer;
import com.fresh.coding.systembankaccount.services.CustomerService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public @NonNull List<SummaryOfCustomer> createAllCustomers(@NonNull List<NewCustomer> newCustomers) {
        return List.of();
    }
}
