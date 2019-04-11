package main;


import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<City> list = City.generateCities(11);
        int selectedCity = City.randCityOnStart(list);
        initNearestNeightboursMethod(list, selectedCity);
        //initBrutalForceMethod(list, selectedCity);
        initAstarMethod(list, selectedCity);
    }

    private static void initNearestNeightboursMethod(List<City> list, int selectedCityOnStart) {
        NearestNeightboursMethod solution = new NearestNeightboursMethod(list, selectedCityOnStart);
        //solution.getMatrix().showMatrix();
        System.out.println("NearestNeightbours algorithm way: " + solution.getCheckedList());
        System.out.println("NearestNeightbours algorithm costWay: " + solution.getCostWay());
        System.out.println("Full cost: " + solution.getFullCost());
        System.out.println("Time executed NearestNeightbours algorithm: " + solution.executedTimeInMillis + " ms "+solution.executedTimeInMillis/1000 + "s\n");
    }

    private static void initBrutalForceMethod(List<City> list, int selectedCityOnStart) {
        BrutalForceMethod solution = new BrutalForceMethod(list, selectedCityOnStart);
        Node lastNode = Collections.min(solution.getNodes());
        System.out.println("BrutalForce algorithm way: " + lastNode.getWay());
        System.out.println("Full cost: " + lastNode.getCost());
        System.out.println("Time executed BrutalForce algorithm: " + solution.executedTimeInMillis + " ms\n");
    }

    private static void initAstarMethod(List<City> list, int selectedCityOnStart) {
        Astar solution = new Astar(list, selectedCityOnStart);
        Node lastNode = solution.getLast();
        System.out.println("\nA* way: " + lastNode.getWay());
        System.out.println("Full cost: " + lastNode.getCost());
        System.out.println("Time executed A* algorithm: " + (solution.executedTimeInMillis) + " ms "+solution.executedTimeInMillis/1000 + " s\n");
    }
}
