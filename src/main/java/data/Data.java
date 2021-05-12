package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.Plant;
import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Spread;
import enums.Sun;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;      

import javax.xml.parsers.DocumentBuilder;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Plant> herb;
    private ArrayList<Plant> wood;

    public Data(){
        herb = new ArrayList<Plant>();
        wood = new ArrayList<Plant>();
        setData();
    }

    private void setData(){
        try {
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        	DocumentBuilder db = dbf.newDocumentBuilder();
        	InputStream is = getClass().getResourceAsStream("plant_data.xml");
        	Document doc = db.parse(is);
        	doc.getDocumentElement().normalize();  
        	System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
        	NodeList nodeList = doc.getElementsByTagName("plant"); 
        	System.out.println(nodeList.getLength());
      
        	for(int i = 0; i < nodeList.getLength(); i++) {
        		Node node = nodeList.item(i);
        		String common, scientific, image, pType, edible, details;
        		int leps, s, so, m, sp, avg_height;
    			Sun sun;
    			Moisture moist;
    			Soil soil;
    			Spread spread;
    			PlantType plantType;
        		if (node.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement = (Element) node; 
        			common = eElement.getElementsByTagName("common").item(0).getTextContent();
        			scientific = eElement.getElementsByTagName("scientific").item(0).getTextContent();
        			leps = Integer.parseInt(eElement.getElementsByTagName("leps").item(0).getTextContent());
        			
        			//SUN
        			s = Integer.parseInt(eElement.getElementsByTagName("sun").item(0).getTextContent());
        			if(s < 4) {
        				sun = Sun.SHADE;
        			}else if(s > 7) {
        				sun = Sun.FULLSUN;
        			}else {
        				sun = Sun.PARTSUN;
        			}
        			
        			//SOIL
        			so = Integer.parseInt(eElement.getElementsByTagName("soil").item(0).getTextContent());
        			if(so < 4) {
        				soil = Soil.ROCK;
        			}else if(so > 7) {
        				soil = Soil.CLAY;
        			}else {
        				soil = Soil.DIRT;
        			}
        			
        			//MOISTURE
        			m = Integer.parseInt(eElement.getElementsByTagName("moist").item(0).getTextContent());
        			if(m < 4) {
        				moist = Moisture.DRY;
        			}else if(m > 7) {
        				moist = Moisture.WET;
        			}else {
        				moist = Moisture.MEDIUM;
        			}
        			
        			//PLANT TYPE
        			pType = eElement.getElementsByTagName("type").item(0).getTextContent();
    				switch(pType) {
            		case "WOOD":
            			plantType = PlantType.WOODY;
            			break;
            		case "HERB":
            			plantType = PlantType.HERBACIOUS;
            			break;
            		default:
            			plantType = PlantType.HERBACIOUS;
            			break;
    				}
    				
    				//IMAGE
        			image = eElement.getElementsByTagName("image").item(0).getTextContent();
        			
        			//SPREAD
        			sp = Integer.parseInt(eElement.getElementsByTagName("spread").item(0).getTextContent());
        			if(sp <= 40) {
        				spread = Spread.SMALL;
        			}else if(sp <= 100) {
        				spread = Spread.MEDIUM;
        			}else if(sp <= 200) {
        				spread = Spread.LARGE;
        			}else {
        				spread = Spread.XL;
        			}
        			
        			//AVG HEIGHT
        			if(eElement.getElementsByTagName("average_height").item(0).getTextContent() == "") {
        				avg_height = -1;
        			}else {
        				avg_height = Integer.parseInt(eElement.getElementsByTagName("average_height").item(0).getTextContent());
        			}
        			
        			//EDIBLE
        			edible = eElement.getElementsByTagName("edible").item(0).getTextContent();
        			
        			//DETAILS
        			if(avg_height == -1) {
            			details = "Type: " + pType + "\nIs Edible: " + edible;
        			}
        			else {
        				details = "Type: " + pType + "\nAvg Height: " + avg_height + "\nIs Edible: " + edible;
        			}
        			
        			//CREATE NEW PLANT OBJECT
        			Plant newPlant = new Plant(scientific, common, leps, plantType, soil, sun, moist, spread, details, sp);
            		if(plantType == PlantType.WOODY) {
            			wood.add(newPlant);
            		}else {
            			herb.add(newPlant);
            		}
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
