package com.mush.bumblebee.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {
    public static int calculateAge(String dob){
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dobDate, currentDate).getYears();
    }
}
