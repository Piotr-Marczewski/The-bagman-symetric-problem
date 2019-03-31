package java;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class CityTest {

    List<City> listOFCity;

    @Test
    public void generateCities() {
        listOFCity = City.generateCities(10);
        assertEquals(listOFCity.size(),10);
    }

    @Test
    public void randCityOnStart() {
        int selected = City.randCityOnStart(listOFCity);
        assertTrue(selected >=0 && selected < listOFCity.size());
    }

    @Test
    public void dist() {
        City city1 = new City(3,4);
        City city2 = new City( 7,8);
        assertEquals(City.dist(city1,city2),5.6568542494924 );
    }

    @Test
    public void equals() {
        City city1 = new City(3,4);
        City city2 = new City( 7,8);
        City city3 = new City( 3,4);
        assertFalse(city1.equals(city2));
        assertTrue(city1.equals(city3));
    }
}
