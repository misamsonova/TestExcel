package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        List<City> cities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Info.csv"), "Windows-1251"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 5) {
                    String name = data[1];
                    String region = data[2];
                    String district = data[3];
                    int population = Integer.parseInt(data[4]);
                    String foundation = data.length > 5 ? data[5] : "";

                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Ошибка в формате строки: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        //Вывод неотсортированного списка всех городов
        printList(cities);

        // Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра
        // Вывод отсортированного списка
        List<City> sorted_cities = new ArrayList<>(cities);
        SortingUtils.sortByCityNameDescending(sorted_cities);
        printList(sorted_cities);

        // Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра
        // Вывод отсортированного списка
        List<City> sorted_cities2 = new ArrayList<>(cities);
        SortingUtils2.sortByDistrictAndCityNameDescending(sorted_cities2);
        printList(sorted_cities2);

        findCityWithMaxPopulation(cities);

        Map<String, Integer> regionCounts = new HashMap<>();
        for (City city : cities) {
            String region = city.getRegion();
            regionCounts.put(region, regionCounts.getOrDefault(region, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : regionCounts.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

    public static void printList(List<City> cities){
        for (City city : cities) {
            System.out.println(city);
        }
        System.out.println(" ");
    }

    public static void findCityWithMaxPopulation(List<City> cities){
        // Находим город с наибольшим количеством жителей
        int maxPopulation = 0;
        int index = -1;
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            int population = city.getPopulation();
            if (population > maxPopulation) {
                index = i+1;
                maxPopulation = population;
            }
        }

        // Выводим информацию о городе с наибольшим количеством жителей
        if (index != -1) {
            System.out.println("[" + index + "] = " + maxPopulation);
        } else {
            System.out.println("Список городов пуст или не содержит достаточно данных для поиска.");
        }
        System.out.println();
    }

}
