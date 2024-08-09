package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {
    public static void sortByCityNameDescending(List<City> cities) {
        cities.sort(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER).reversed());
    }
}
