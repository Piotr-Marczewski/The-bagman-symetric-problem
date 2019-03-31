package main;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@EqualsAndHashCode(of = {"x", "y"})
public class City {
    private int x;
    private int y;
    private static Random rand = new Random();

    public City() {
        x = rand.nextInt(100) + 1;
        y = rand.nextInt(100) + 1;
    }

    public City( int x, int y){
        this.x = x;
        this.y = y;
    }

    public static List<City> generateCities(int howMatch){
        List<City> list = new ArrayList<>();
        for(int i =0;i<howMatch;i++){
            City newCity = new City();
            while(list.contains(newCity)){
                newCity = new City();
            }
            list.add(new City());
        }
        return list;
    }



    public static int randCityOnStart(List<City> cities){
        return rand.nextInt(cities.size());
    }

    public static double dist(City c1, City c2){
        return Math.sqrt(Math.pow(c1.getX() - c2.getX(),2) + Math.pow(c1.getY() - c2.getY(),2));
    }
    @Override
    public String toString(){
        return "x: " + this.x + " y: " + this.y;
    }
}
