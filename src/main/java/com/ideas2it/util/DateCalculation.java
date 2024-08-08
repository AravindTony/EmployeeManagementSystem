package com.ideas2it.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class handles the method like Calculate the
 * Difference between the Two Dates
 */
public class DateCalculation {
    /**
     * <p>
     * This method Calculates difference of year and Month between two Dates
     * </p>
     * @param date - Input Date
     * @return String of Difference between the Dates
     */
    public static String calculateDifferenceBetweenDates(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears() + "y"
                + Period.between(date, LocalDate.now()).getMonths() + "m";
    }
}
