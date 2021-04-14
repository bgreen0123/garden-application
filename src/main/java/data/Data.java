package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import model.Plant;
import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Sun;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Data {
    private ArrayList<Plant> herb;
    private ArrayList<Plant> wood;

    public Data(){
        herb = new ArrayList<Plant>();
        wood = new ArrayList<Plant>();
        setData();
    }

    private void setData(){
        JSONParser parser = new JSONParser();
        try {
        	Object obj = parser.parse(new FileReader(getClass().getResource("/data/plant_data.json").getFile()));
        	JSONObject jsonObject = (JSONObject) obj;
        	JSONArray plantsArray = (JSONArray) jsonObject.get("plants");
        	Iterator iterator = plantsArray.iterator();
        	Iterator<Map.Entry> itr1;
        	while(iterator.hasNext()) {
        		itr1 = ((Map) iterator.next()).entrySet().iterator();
        		String common, scientific, l, image, s, so, m, pType;
        		common = scientific = image = " ";
        		int leps = 0;
    			Sun sun = Sun.SHADE;
    			Moisture moist = Moisture.WET;
    			Soil soil = Soil.CLAY;
    			PlantType plantType = PlantType.HERBACIOUS;
        		while(itr1.hasNext()) {
        			Map.Entry pair = itr1.next();
        			String key = (String)pair.getKey();
        			switch(key) {
        			case "common":
        				common = (String)pair.getValue();
        				break;
        			case "scientific":
        				scientific = (String)pair.getValue();
        				break;
        			case "leps":
        				l = (String)pair.getValue();
        				leps = Integer.parseInt(l);
        				break;
        			case "sun":
        				s = (String)pair.getValue();
        				switch(s) {
                		case "f":
                			sun = Sun.FULLSUN;
                			break;
                		case "p":
                			sun = Sun.PARTSUN;
                			break;
                		case "s":
                			sun = Sun.SHADE;
        				break;
        				}
        			case "soil":
        				so = (String)pair.getValue();
        				switch(so) {
                		case "c":
                			soil = Soil.CLAY;
                			break;
                		case "d":
                			soil = Soil.DIRT;
                			break;
                		case "r":
                			soil = Soil.ROCK;
        				break;
        				}
        			case "moist":
        				m = (String)pair.getValue();
        				switch(m) {
                		case "w":
                			moist = Moisture.WET;
                			break;
                		case "d":
                			moist = Moisture.DRY;
                			break;
        				}
        			case "type":
        				pType = (String)pair.getValue();
        				switch(pType) {
                		case "w":
                			plantType = PlantType.WOODY;
                			break;
                		case "h":
                			plantType = PlantType.HERBACIOUS;
                			break;
        				}
        			case "image":
        				image = (String)pair.getValue();
        				break;
        			}
        			
        		}
        		Plant newPlant = new Plant(scientific, common, leps, plantType, soil, sun, moist, image);
        		if(plantType == PlantType.WOODY) {
        			wood.add(newPlant);
        		}else {
        			herb.add(newPlant);
        		}
  
        	}
        	
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }catch(IOException e) {
        	e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

    //Getters
    public ArrayList<Plant> getHerbacious(){
        return herb;
    }
    public ArrayList<Plant> getWoody(){
        return wood;
    }
    public static void main(String [] args){
        Data d = new Data();
        for(Plant p : d.getHerbacious()) {
        	System.out.println(p);
        }
        System.out.println("------------------------------------------------------");
        for(Plant p : d.getWoody()) {
        	System.out.println(p);
        }
    }
}
