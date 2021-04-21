package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Plant;
import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Sun;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element; 

import javax.xml.parsers.DocumentBuilder;

public class Data {
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
        		String common, scientific, l, image, s, so, m, pType;
        		int leps;
    			Sun sun;
    			Moisture moist;
    			Soil soil;
    			PlantType plantType;
        		if (node.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement = (Element) node; 
        			common = eElement.getElementsByTagName("common").item(0).getTextContent();
        			scientific = eElement.getElementsByTagName("scientific").item(0).getTextContent();
        			leps = Integer.parseInt(eElement.getElementsByTagName("leps").item(0).getTextContent());
        			s = eElement.getElementsByTagName("sun").item(0).getTextContent();
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
            		default:
            			sun = Sun.FULLSUN;
            			break;
        			}
        			
        			so = eElement.getElementsByTagName("soil").item(0).getTextContent();
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
            		default:
            			soil = Soil.ROCK;
            			break;
    				}
        			
        			m = eElement.getElementsByTagName("moist").item(0).getTextContent();
        			switch(m) {
            		case "w":
            			moist = Moisture.WET;
            			break;
            		case "d":
            			moist = Moisture.DRY;
            			break;
            		default:
            			moist = Moisture.DRY;
            			break;
    				}
        			
        			pType = eElement.getElementsByTagName("type").item(0).getTextContent();
    				switch(pType) {
            		case "w":
            			plantType = PlantType.WOODY;
            			break;
            		case "h":
            			plantType = PlantType.HERBACIOUS;
            			break;
            		default:
            			plantType = PlantType.HERBACIOUS;
            			break;
    				}
    				
        			image = eElement.getElementsByTagName("image").item(0).getTextContent();
        			
        			Plant newPlant = new Plant(scientific, common, leps, plantType, soil, sun, moist, image);
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
