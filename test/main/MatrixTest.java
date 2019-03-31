package main;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;


public class MatrixTest {
    private List<City> listOfCity;
    private Matrix matrix;

    @Before
    public void initMatrix() {
        listOfCity = new ArrayList<>();
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                listOfCity.add(new City(row, col));
            }
        }
        matrix = new Matrix(listOfCity, 0);
    }

    @Test
    public void getMapColumn() {
        SortedMap<Double, Integer> column = matrix.getMapColumn(2);
        Integer numberCityWithMinValue = column.get(column.firstKey());
        assertEquals(numberCityWithMinValue, 3, 0.0);
    }
    @Test
    public void getDistance(){
       assertEquals( matrix.getDistance(1,1),0.0,0.0);
       assertEquals(matrix.getDistance(1,3),1.0,0.0);
    }


}
