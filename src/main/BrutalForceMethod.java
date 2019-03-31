package main;

import lombok.Getter;

import java.util.*;

@Getter
public class BrutalForceMethod extends TheBagmanSolution{

    private List<Node> nodes;
    private List<Integer> listOfHowManyNodesIsOnEveryFloorOfTree;

    public BrutalForceMethod(List<City> list, int selectedCityOnRoot) {
        super(list, selectedCityOnRoot);
        nodes = new ArrayList<>();
        generateListOfHowManyNodesIsOnEveryFloorOfTree(listOfCities.size());
        createTree();
        addFinalNodes();
        stopTime = System.currentTimeMillis();
        executedTimeInMillis = stopTime - startTime;
    }

    private void generateListOfHowManyNodesIsOnEveryFloorOfTree(int numberCities) {
        listOfHowManyNodesIsOnEveryFloorOfTree = new ArrayList<>();
        listOfHowManyNodesIsOnEveryFloorOfTree.add(1);
        listOfHowManyNodesIsOnEveryFloorOfTree.add(numberCities - 1);
        int multiplicationResult = numberCities - 1;
        for (int substring = 2; substring < numberCities; substring++) {
            multiplicationResult *= (numberCities - substring);
            listOfHowManyNodesIsOnEveryFloorOfTree.add(multiplicationResult);
        }
    }


    void createTree() {
        // przechodzenie po poziomach drzewa
        for (int levelOfTree = 0; levelOfTree < listOfHowManyNodesIsOnEveryFloorOfTree.size(); levelOfTree++) {
            //przechodzenie po grupach węzłów w poziomie
            for (int numberOfGroup = 0; numberOfGroup < getNumberOfGroups(levelOfTree); numberOfGroup++) {
                //   System.out.println("getNumberOfGroups graniczna " + getNumberOfGroups(levelOfTree));
                // INICAJLIZACJA DRZEWA
                if (nodes.isEmpty()) {
                    nodes.add(new Node(null, 0.0, selectedCityOnStart));
                } else {
                    Set<Integer> temporarySet = generateSetForAllIndexesOfCities(listOfCities);
                    // przechodzenie po węzłach w grupie
                    for (int nodesInOneGroup = 0; nodesInOneGroup < getNumberOfNodesInGroup(numberOfGroup, levelOfTree); nodesInOneGroup++) {
                        //    System.out.println("levelOfTree " + levelOfTree + " numberOfGroup " + numberOfGroup + " nodesInOneGroup " + nodesInOneGroup);
                        addNodeToTree(temporarySet, numberOfGroup);
                    }
                }
            }
            System.out.println("Na poziomie " + levelOfTree + " istnieje " + nodes.size() + " węzłów");
            deleteUnnecessaryNodes(nodes, listOfHowManyNodesIsOnEveryFloorOfTree.get(levelOfTree));
            //System.out.println("Na poziomie " + levelOfTree + " istnieje " + nodes.size() + " węzłów");
            // showNodes();
        }
    }

    private void addNodeToTree(Set<Integer> temporarySet, int numberOfGroup) {
        deleteVisitedNodes(temporarySet, nodes.get(numberOfGroup));
        int selectedCity = getUniqueIndexOfCity(temporarySet);
        if (selectedCity != -1) {
            Node parent = null;
            if (!nodes.isEmpty()) {
                parent = nodes.get(numberOfGroup);
            }
            Node newNode = new Node(parent, getDistance(parent.getIndexCity(), selectedCity), selectedCity);
            nodes.add(newNode);
        }
    }

    private void deleteUnnecessaryNodes(List<Node> list, int howMany) {
        // System.out.println("SizeOfList before delete " + list.size());
        while (list.size() > howMany) {
            list.remove(0);
        }
        //  System.out.println("SizeOfList after delete " + list.size());
    }

    void addFinalNodes() {
        // showNodes();
        //System.out.println("Po dodaniu końcowych węzłów");
        List<Node> newNodes = new ArrayList<>();
        for (Node item : nodes) {
            Node endNode = new Node(item, getDistance(item.getIndexCity(), selectedCityOnStart), selectedCityOnStart);
            newNodes.add(endNode);
        }
        nodes = newNodes;
        //   showNodes();
        //  System.out.println("Po posortowaniu");
        Collections.sort(nodes);
        //showNodes();
    }

    private double getDistance(int city1, int city2) {
        return matrix.getDistance(city1, city2);
    }


    private int getUniqueIndexOfCity(Set<Integer> set) {
        if (!set.isEmpty()) {
            int sizeOfSet = set.size();
            Random rand = new Random();
            int selected = rand.nextInt(sizeOfSet);
            int i = 0;
            for (Integer item : set) {
                if (i == selected) {
                    set.remove(item);
                    return item;
                }
                i++;
            }
        }
        return -1;
    }



    private void deleteVisitedNodes(Set<Integer> set, Node parent) {
        //System.out.println(set + " " + parent);
        for (Integer item : parent.getWay()) {
            set.remove(item);
        }
    }

    public void showNodes() {
        for (Node item : nodes) {
            System.out.println(item);
        }
    }

    private long getNumberOfGroups(Integer levelOfTree) {
        return calculateNumberOfGroup(listOfHowManyNodesIsOnEveryFloorOfTree.size(), levelOfTree);
    }

    private long getNumberOfNodesInGroup(int numberOfGroup, int levelOfTree) {
        return listOfHowManyNodesIsOnEveryFloorOfTree.get(levelOfTree) / getNumberOfGroups(levelOfTree);
    }

    //TODO REKURENCJA -> ITERACJA
    private long calculateNumberOfGroup(int numberOfAllCities, int levelOfTree) {
        if (levelOfTree < 2) return 1;
        return (numberOfAllCities - (levelOfTree - 1)) * calculateNumberOfGroup(numberOfAllCities, (levelOfTree - 1));
    }

}
