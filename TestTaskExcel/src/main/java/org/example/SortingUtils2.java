package org.example;

import java.util.Comparator;
import java.util.List;

public class SortingUtils2 {
    public static void sortByDistrictAndCityNameDescending(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName, Comparator.reverseOrder()));
    }
}
