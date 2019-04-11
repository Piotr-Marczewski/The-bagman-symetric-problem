package main;

import lombok.Getter;

import java.util.*;

@Getter
public class Astar extends TheBagmanSolution {

    private Node last;
    private SortedMap<Double, Node> prioritetMap;
    private Random rand = new Random();


    public Astar(List<City> list, int selectedCityOnRoot) {
        super(list, selectedCityOnRoot);
        findWay();
        stopTime = System.currentTimeMillis();
        executedTimeInMillis = stopTime - startTime;
    }


    public void findWay() {
        prioritetMap = new TreeMap<>();
        boolean first = true;
        last = new Node(null, 0.0, selectedCityOnStart);
        int i = 0;
        while (last.getWay().size() < listOfCities.size()) {
            Set<Integer> setUnvisitedNodes = generatedSetForAllUnvisitedIndexesOfCities(last);


            for (Integer item : setUnvisitedNodes) {
                double aStarOfValue = last.getCost() + getDistance(last.getIndexCity(), item) + (findMinDistanceInUnvisitedNodes() * (listOfCities.size() - last.getWay().size() + 1));
                Node sugestNode = new Node(last, getDistance(last.getIndexCity(), item), item);
                prioritetMap.put(aStarOfValue, sugestNode);
            }
            //System.out.println(setUnvisitedNodes.size());
            //System.out.println("START: " + selectedCityOnStart);f
            //System.out.println("*************** ***************");
            int index = 0;

            for (Double key : prioritetMap.keySet()) {
                //System.out.println(index++ +" Key: " + key + " Value: " + prioritetMap.get(key));
            }

            double selectedValue = prioritetMap.firstKey();
            Node selectedNode = prioritetMap.get(selectedValue);
            //System.out.println("\nSelectedNode: " + selectedNode);
            prioritetMap.remove(selectedValue);
            last = selectedNode;
            //prioritetMap.clear();

        }
        Node finalNode = new Node(last, getDistance(last.getIndexCity(), selectedCityOnStart), selectedCityOnStart);
        last = finalNode;
    }

    private double getSumLowestDistance(int howMany) {
        double sum = 0.0;
        SortedMap<Double, Integer> distanceFromStartMap = matrix.getMapColumn(selectedCityOnStart);
        if (!distanceFromStartMap.isEmpty()) {
            for (int i = 1; i < howMany; i++) {
                sum += distanceFromStartMap.firstKey();
                distanceFromStartMap.remove(distanceFromStartMap.firstKey());
            }
        }
        return sum;
    }

    private double findMinDistanceInUnvisitedNodes() {
        Set<Integer> setUnvisitedNodes = generatedSetForAllUnvisitedIndexesOfCities(last);
        double min = 0.0;
        for (Integer item : setUnvisitedNodes) {
            if (min > getDistance(last.getIndexCity(), item)) {
                min = getDistance(last.getIndexCity(), item);
            }
        }
        return min;
    }

    private double getDistance(int city1, int city2) {
        return matrix.getDistance(city1, city2);
    }


}
