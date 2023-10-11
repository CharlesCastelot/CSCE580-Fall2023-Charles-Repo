package com.demo.RestaurantAIProject;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException;

public class RestaurantChatBox {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);  // Create a Scanner object
		
		Object[][] dataRestaurants = readExcelFile("Restaurants.xlsx");
		
		
		System.out.println("Hello, input your name:");
		String name = keyboard.nextLine();
		int layer = 0;
		
		String userInput = "";
		while( !(userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) ) {
			if(userInput.equalsIgnoreCase("")) {
				System.out.println("How can I help you today "+name+"?");
			}
			else if(userInput.equalsIgnoreCase("hi")) {
				System.out.println("Hello ;)");
			}
			else if(userInput.equalsIgnoreCase("good")) {
				System.out.println("happy for you :).");
			}
			else if(userInput.equalsIgnoreCase("bad")) {
				System.out.println("deserved lol >:)");
			}
			else if(userInput.equalsIgnoreCase("Give me a list of all the restaurants in Columbia")) {
				for(int i=0; i < dataRestaurants.length; i++) {
					System.out.println(dataRestaurants[i][1]);
				}
				System.out.println("Can I help you with anything else?");
			}
			//Search by category of food
			else if(userInput.toLowerCase().contains("which restaraunts serve")) {
				String foodType = userInput.substring(24, userInput.length()-1);
				
				int indexToExplore = 0;
				for(int i=0; i < dataRestaurants[0].length; i++) {
					if(dataRestaurants[0][i].toString().equalsIgnoreCase(foodType)) {
						indexToExplore = i;
						break;
					}
				}
				if(indexToExplore == 0) {
					System.out.println("Sorry, I do not know any restaurants for "+foodType);
				}
				else {
					System.out.println("Here is a list of the restaurants that servers "+foodType+":");
					for(int i=0; i < dataRestaurants.length; i++) {
						if(dataRestaurants[i][indexToExplore].toString().equalsIgnoreCase("1.0")) {
							System.out.println(dataRestaurants[i][1]);
						}
					}
				}
				System.out.println("Can I help you with anything else?");
			}
			
			
			//Search by category of food
			else if(userInput.toLowerCase().contains("what type of food is served at ")) {
				String restaurantType = userInput.substring(31, userInput.length()-1);
				
				int indexToExplore = 0;
				for(int i=0; i < dataRestaurants.length; i++) {
					if(dataRestaurants[i][1].toString().equalsIgnoreCase(restaurantType)) {
						indexToExplore = i;
						break;
					}
				}
				if(indexToExplore == 0) {
					System.out.println("Sorry, I do not know the existance of "+restaurantType);
				}
				else {
					for(int i=0; i < dataRestaurants[1].length; i++) {
						if(dataRestaurants[indexToExplore][i].toString().equalsIgnoreCase("1.0")) {
							System.out.println(restaurantType+" is a restaurant that categories in "+dataRestaurants[0][i]);
						}
					}
				}
				System.out.println("Can I help you with anything else?");
			}
			else {
				System.out.println(userInput+" - I do not know this information");
			}
			
			userInput = keyboard.nextLine();
		}
		
		System.out.println("goodbye!");
	}
	
	public static Object[][] readExcelFile(String fileName){
		Object[][] restaurants = new Object[267][31];
		
		// Try block to check for exceptions 
        try { 
  
            // Reading file from local directory 
            FileInputStream file = new FileInputStream(new File(fileName)); 
  
            // Create Workbook instance holding reference to 
            // .xlsx file 
            XSSFWorkbook workbook = new XSSFWorkbook(file); 
  
            // Get first/desired sheet from the workbook 
            XSSFSheet sheet = workbook.getSheetAt(0); 
  
            // Iterate through each rows one by one 
            Iterator<Row> rowIterator = sheet.iterator(); 
            
            int i = 0;
            int j = 0;
            // Till there is an element condition holds true 
            while (rowIterator.hasNext()) { 
  
                Row row = rowIterator.next(); 
  
                // For each row, iterate through all the 
                // columns 
                Iterator<Cell> cellIterator 
                    = row.cellIterator(); 
  
                j = 0;
                while (cellIterator.hasNext()) { 
  
                    Cell cell = cellIterator.next();
  
                    // Checking the cell type and format 
                    // accordingly 
                    
                    //System.out.print(cell+ "	");                   
                    restaurants[i][j] = cell;
                    //System.out.print(restaurants[i][j]+"	");  
                    j++;
                    /*
                    switch (cell.getCellType()) {
  
                    // Case 1 
                    case Cell.CELL_TYPE_NUMERIC: 
                        System.out.print( 
                            cell.getNumericCellValue() 
                            + "t"); 
                        break; 
  
                    // Case 2 
                    case Cell.CELL_TYPE_STRING: 
                        System.out.print( 
                            cell.getStringCellValue() 
                            + "t"); 
                        break; 
                    } 
                    */
                }
                
                i++;
            } 
  
            // Closing file output streams 
            file.close(); 
        } 
  
        // Catch block to handle exceptions 
        catch (Exception e) { 
  
            // Display the exception along with line number 
            // using printStackTrace() method 
            e.printStackTrace(); 
        }
		
		return restaurants;
	}

}
