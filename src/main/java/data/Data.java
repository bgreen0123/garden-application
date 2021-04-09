package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import model.Plant;
import enums.PlantType;

public class Data {
    private HashSet<Plant> herb;
    private HashSet<Plant> wood;

    public Data(){
        setHerbaciousPlants();
        setWoodyPlants();
    }

    private void parse(String path, HashSet<Plant> plants, PlantType t){
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                Plant p = new Plant(values[0] + " " + values[1], values[2], 0, t);
                plants.add(p);
            }
            br.close();
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    //Setters
    private void setHerbaciousPlants(){
        herb = new HashSet<Plant>();
        String filePath = "C:/Users/AdamK/OneDrive/Documents/Cisc275/Projects/project-team-12-4/src/main/java/data/herbacious.csv";
        parse(filePath, herb, PlantType.HERBACIOUS);
    }
    private void setWoodyPlants(){
        wood = new HashSet<Plant>();
        String filePath = "C:/Users/AdamK/OneDrive/Documents/Cisc275/Projects/project-team-12-4/src/main/java/data/woody.csv";
        parse(filePath, wood, PlantType.WOODY);
    }

    //Getters
    public HashSet<Plant> getHerbacious(){
        return herb;
    }
    public HashSet<Plant> getWoody(){
        return wood;
    }
    public static void main(String [] args){
        Data d = new Data();
        System.out.println(d.getHerbacious());
    }
}
