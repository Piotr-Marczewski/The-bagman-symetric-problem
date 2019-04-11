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
    protected  int selectedCityOnStart;
    protected static List<City> listOfCities;

    public TheBagmanSolution(List<City> list, int selectedCityOnRoot){
        listOfCities = list;
        selectedCityOnStart = selectedCityOnRoot;
        startTime = System.currentTimeMillis();
        matrix = new Matrix(listOfCities, selectedCityOnRoot);
    }

    protected static Set<Integer> generateSetForAllIndexesOfCities() {
        Set<Integer> temporarySet = new HashSet<>();
        for (int i = 0; i < listOfCities.size(); i++) {
            temporarySet.add(i);
        }
        return temporarySet;
    }

    protected static Set<Integer> generatedSetForAllUnvisitedIndexesOfCities(Node parent){
        Set<Integer> temporarySet = new HashSet<>();
        for (int i = 0; i < listOfCities.size(); i++) {
            temporarySet.add(i);
        }
        for (Integer item : parent.getWay()) {
            temporarySet.remove(item);
        }
        return temporarySet;
    }
}
