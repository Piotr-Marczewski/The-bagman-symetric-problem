package main;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
public class Astar extends TheBagmanSolution{

    private List<Node> nodes;
    private List<Node> sugest;

    public Astar(List<City> list, int selectedCityOnRoot) {
        super(list, selectedCityOnRoot);
        nodes = new ArrayList<>();
        findWay();
        stopTime = System.currentTimeMillis();
        executedTimeInMillis = stopTime - startTime;
    }

    private double calculateValueOfHeristic(double cost, double minValueBeetwenVisitedAndUnvisited, int numberOfNodesOnWay) {
        //System.out.println(cost+" "+minValueBeetwenVisitedAndUnvisited+" "+(listOfCities.size() - (numberOfNodesOnWay + 1)+" "+cost +" "+ (minValueBeetwenVisitedAndUnvisited * (listOfCities.size() - (numberOfNodesOnWay + 1)))));
        //return cost + (minValueBeetwenVisitedAndUnvisited);
        return cost + (minValueBeetwenVisitedAndUnvisited * (listOfCities.size()-(numberOfNodesOnWay+1)));
    }


    public void findWay() {
        for (int numberOfNodesOnWay = 0; numberOfNodesOnWay < listOfCities.size() - 1; numberOfNodesOnWay++) {
            if (numberOfNodesOnWay == 0) {
                nodes.add(new Node(null, 0.0, selectedCityOnStart, 0.0));
            }
            Set<Integer> temporarySet = generateSetForAllIndexesOfCities(listOfCities);
            Node parent = nodes.get(0);
            double min = findMinDistanceBeetwenVisitedAndUnvisited(parent);
            deleteVisitedNodes(temporarySet, parent);
            sugest = new ArrayList<>();
            for (Integer item : temporarySet) {
                // System.out.println(parent.getIndexCity()+" "+item+" "+listOfCities.size());
                sugest.add(new Node(parent, getDistance(parent.getIndexCity(), item), item, calculateValueOfHeristic(parent.getCost()+ getDistance(parent.getIndexCity(), item), min, numberOfNodesOnWay)));
                //sugest.add(new Node(parent, getDistance(parent.getIndexCity(), item), item, calculateValueOfHeristic(parent.getCost(), min, numberOfNodesOnWay)));
            }

            sugest.sort(new AstarComparator());
            /*
            System.out.println();
            for(Node item :sugest){
                System.out.print(item.getIndexCity()+" "+item.getValueOfHeuristic()+" ; ");
            }
             */
            Node selected = Collections.min(sugest);
            parent = nodes.get(0);
            Node newNode = new Node(parent,
                    getDistance(parent.getIndexCity(),
                            selected.getIndexCity()),
                    selected.getIndexCity(),
                    selected.getValueOfHeuristic());
            nodes.clear();
            nodes.add(newNode);
        }
        addFinalNode();
    }

    private void addFinalNode() {
        Node parent = nodes.get(0);
        Node finalNode = new Node(parent, getDistance(parent.getIndexCity(), selectedCityOnStart), selectedCityOnStart);
        nodes.clear();
        nodes.add(finalNode);
    }

    private void deleteVisitedNodes(Set<Integer> set, Node parent) {
        //System.out.println(set + " " + parent);
        for (Integer item : parent.getWay()) {
            set.remove(item);
        }
    }

    private double getDistance(int city1, int city2) {
        return matrix.getDistance(city1, city2);
    }

    private double findMinDistanceBeetwenVisitedAndUnvisited(Node current) {
        Set<Integer> temporarySet = generateSetForAllIndexesOfCities(listOfCities);
        Node parent = current;
        deleteVisitedNodes(temporarySet, parent);
        List<Double> list = new ArrayList<>();
        for (Integer item : temporarySet) {
            list.add(getDistance(parent.getIndexCity(), item));
        }
        Collections.sort(list);
        double sum = 0.0;
        int size = listOfCities.size() - current.getWay().size();

        for(int i=0;i<size;i++){
            sum+= list.get(i);
        }
        //System.out.println(list);
        //System.out.println(Collections.min(list));
        //return Collections.min(list);
        return sum;
    }
}
