package com.virtz.test.expressionEvaluator;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

public class virtzTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		readParseInput(hmap);
		
	}
		private static void readParseInput(HashMap<String, Integer> hmap) {
		// TODO Auto-generated method stub
			Scanner scanner = new Scanner(System.in);
			boolean valueAvailable = false;
			while(scanner.hasNextLine())
			{
			String input = scanner.nextLine();
			HashMap<String,String> tempMap= new HashMap<String,String>();
			if(input != null && !input.trim().isEmpty())
			 {
				String[] mapEntries = input.trim().split("=");
				String mapKey = mapEntries[0];
				String mapValueExpression = mapEntries[1];
				
				if(mapValueExpression.contains("+")){
					Integer sum = 0;
					String[] tokens = mapValueExpression.trim().split("\\+");
				
					for(int i=0; i<tokens.length; i++){
						if(isNumeric(tokens[i])){
							sum = sum + Integer.parseInt(tokens[i]);
						}
						else if(!isNumeric(tokens[i]) && hmap.containsKey(tokens[i])){
							sum = sum + hmap.get(tokens[i]);
						} 
					}
					hmap.put(mapKey,sum);
					valueAvailable = true;
					displayResult(mapKey,hmap);
					
					
				}
				else{
					if(isNumeric(mapValueExpression)){
					hmap.put(mapKey, Integer.parseInt(mapValueExpression));
					valueAvailable = true;
					displayResult(mapKey,hmap);
					}
					else if(!isNumeric(mapValueExpression) && hmap.containsKey(mapValueExpression)){
						hmap.put(mapKey, hmap.get(mapValueExpression));
						valueAvailable = true;
						displayResult(mapKey,hmap);
					}
					else{
					// wait for the value to be update
						hmap.put(mapKey,hmap.get(mapValueExpression));
						tempMap.put(mapKey,mapValueExpression);
						displayResult(mapKey,hmap);
					}
				}
				
				
				
			}
			Iterator it=tempMap.entrySet().iterator();
			 while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
				hmap.computeIfPresent((String) pair.getKey(),(k,v) -> hmap.get(pair.getValue()));
				//it.remove();
				displayResult((String) pair.getKey(),hmap);
				
			}
				
			}
		}
			
	private static void displayResult(String mapKey,HashMap<String, Integer> hmap) {
			// TODO Auto-generated method stub
			if(hmap.get(mapKey)!= null){
				System.out.println("===>" + mapKey + "=" + hmap.get(mapKey));
			}
			else{
			}
		
		}
		
		private static boolean isNumeric(String s) {
			// TODO Auto-generated method stub
			try {
				double d = Double.parseDouble(s);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}
}

