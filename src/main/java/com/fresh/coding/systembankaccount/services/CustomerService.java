package com.fresh.coding.systembankaccount.services;

import com.fresh.coding.systembankaccount.dtos.requests.NewCustomer;
import com.fresh.coding.systembankaccount.dtos.responses.SummaryOfCustomer;
import lombok.NonNull;

import java.util.List;

public interface CustomerService {
    @NonNull
    List<SummaryOfCustomer> createAllCustomers(@NonNull List<NewCustomer> newCustomers);
}
