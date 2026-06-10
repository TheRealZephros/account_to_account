package com.roi.account_api.helper;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Helper {
    public static boolean isValidDate(int year, int month, int day){
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
