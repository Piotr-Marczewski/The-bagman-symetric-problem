package javaTest;

import lombok.Getter;

import java.util.*;

import static java.lang.Double.NaN;

@Getter
public class Matrix {

    private int size;
    private double[][] tab;

    public Matrix(List<City> list, int startIdCity) {
        size = list.size();
        tab = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == col) {
                    tab[row][col] = NaN;
                } else {
                    tab[row][col] = City.dist(list.get(row), list.get(col));
                }

            }
        }

    }

    public SortedMap<Double, Integer> getMapColumn(int indexCity) {
        SortedMap<Double, Integer> column = new TreeMap<>();
        for (int row = 0; row < size; row++) {
            if (row != indexCity) {
                column.put(tab[row][indexCity], row);
            }
        }
        return column;
    }


    public Double getDistance(int city1, int city2) {
        if (city1 == city2) return 0.0;
        return tab[city1][city2];
    }


    public void showMatrix() {
        System.out.print("   |");
        for (int i = 0; i < size; i++) {
            System.out.print("      " + i + "     |");
        }
        System.out.println();
        for (int j = 0; j < size; j++) {
            System.out.print(String.format("%2d ", j));

            for (int z = 0; z < size; z++) {

                if (tab[j][z] == NaN) {
                    System.out.print(String.format("| %6.5f  ", tab[j][z]));
                } else {
                    System.out.print(String.format("| %9.5f", tab[j][z]) + "  ");
                }
            }
            System.out.println("|");
        }
    }
}
