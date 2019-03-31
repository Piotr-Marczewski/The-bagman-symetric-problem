package main;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
 class NearestNeightboursMethod extends TheBagmanSolution{
    private Set<Integer> checked;
    private List<Integer> checkedList;
    private List<Double> costWay;
    private double fullCost = 0.0;


    public NearestNeightboursMethod(List<City> list, int selectedCityOnRoot) {
        super(list, selectedCityOnRoot);
        checked = new HashSet<>();
        checkedList = new ArrayList<>();
        costWay = new ArrayList<>();
        checked.add(selectedCityOnRoot);
        checkedList.add(selectedCityOnRoot);
        costWay.add(0.0);
        solveProblem();
        stopTime = System.currentTimeMillis();
        executedTimeInMillis = stopTime - startTime;
    }

    private Integer findNextNode() {

        double value = 0.0;
        int index = checkedList.get(checkedList.size()-1);
        SortedMap<Double, Integer> tmpMap = matrix.getMapColumn(index);
        value = tmpMap.firstKey();
        int nrCity = tmpMap.get(value);
        while (checked.contains(nrCity)) {
            tmpMap.remove(value, nrCity);
            value = tmpMap.firstKey();
            nrCity = tmpMap.get(value);
        }
        fullCost += value;
        costWay.add(value);
        checkedList.add(nrCity);
        checked.add(nrCity);
        return nrCity;
    }

    private void solveProblem() {
        for (int i = 0; i < matrix.getSize() - 1; i++) {
            this.findNextNode();
        }
        double costReturn = matrix.getTab()[checkedList.get(0)][checkedList.get(checkedList.size() - 1)];
        fullCost += costReturn;
        checkedList.add(checkedList.get(0));

    }
}

