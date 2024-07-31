package com.fresh.coding.systembankaccount.dtos.responses;

import com.fasterxml.jackson.annotation.JsonView;
import com.fresh.coding.systembankaccount.groups.Groups;

import java.time.LocalDate;

public record SummaryOfCustomer (
    @JsonView(Groups.Public.class) Long id,
    @JsonView(Groups.Public.class) String firstName,
    @JsonView(Groups.Public.class) String lastName,
    @JsonView(Groups.Public.class) LocalDate birthdate,
    @JsonView(Groups.Public.class) String cin,
    @JsonView(Groups.Public.class) String email,
    @JsonView(Groups.Internal.class) String password
){}
