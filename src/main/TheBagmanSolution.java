package main;

import lombok.Getter;
import java.util.List;

@Getter
public  abstract class TheBagmanSolution {
    protected long startTime;
    protected long stopTime;
    protected long executedTimeInMillis;
    protected Matrix matrix;
    protected int selectedCityOnStart;
    protected List<City> listOfCity;

    public TheBagmanSolution(List<City> list, int selectedCityOnRoot){
        listOfCity= list;
        selectedCityOnStart = selectedCityOnRoot;
        startTime = System.currentTimeMillis();
        matrix = new Matrix(listOfCity, selectedCityOnRoot);
    }
}
