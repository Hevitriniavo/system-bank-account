package com.fresh.coding.systembankaccount.handlers;

import java.time.LocalDate;

public record AppError<T>(
        T messages,
        LocalDate date,
        Integer status
) {
}
