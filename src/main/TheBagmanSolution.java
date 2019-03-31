package main;

import java.util.List;

public  class TheBagmanSolution {
    long startTime;
    long stopTime;
    Matrix matrix;
    int selectedCityOnStart;
    List<City> listOfCity;

    public TheBagmanSolution(List<City> list, int selectedCityOnRoot){
        listOfCity= list;
        selectedCityOnStart = selectedCityOnRoot;
        matrix = new Matrix(listOfCity, selectedCityOnRoot);
    }
}
