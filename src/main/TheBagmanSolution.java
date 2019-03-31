package main;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public  abstract class TheBagmanSolution{
    protected long startTime;
    protected long stopTime;
    protected long executedTimeInMillis;
    protected Matrix matrix;
    protected int selectedCityOnStart;
    protected List<City> listOfCities;

    public TheBagmanSolution(List<City> list, int selectedCityOnRoot){
        listOfCities = list;
        selectedCityOnStart = selectedCityOnRoot;
        startTime = System.currentTimeMillis();
        matrix = new Matrix(listOfCities, selectedCityOnRoot);
    }

    protected static Set<Integer> generateSetForAllIndexesOfCities(List<City> listOfCities) {
        Set<Integer> temporarySet = new HashSet<>();
        for (int i = 0; i < listOfCities.size(); i++) {
            temporarySet.add(i);
        }
        return temporarySet;
    }
}
