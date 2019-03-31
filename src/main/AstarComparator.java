package main;

import java.util.Comparator;

public class AstarComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if(o1.getValueOfHeuristic() < o2.getValueOfHeuristic()) return -1;
        else if(o1.getValueOfHeuristic() > o2.getValueOfHeuristic()) return 1;
        return 0;
    }
}