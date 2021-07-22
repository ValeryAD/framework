package com.github.valeryad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String PRICE_REGEX = "(\\d+,)*\\d+(\\.\\d{1,2})?";


    public static Double getPriceFromEstimatedCostString(String estimatedCost) {
        System.out.println(estimatedCost);
        Pattern pattern = Pattern.compile(PRICE_REGEX);
        Matcher matcher = pattern.matcher(estimatedCost);
        matcher.find();
        return Double.parseDouble(matcher.group().replace(",", ""));
    }
}
