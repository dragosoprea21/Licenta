package read_fucking_excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.lang3.StringUtils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ReadExcel {

	    private static final String FILE_NAME = "G:\\github\\Licenta\\orar\\modified\\orar.xlsx";
	    private static final String[] week = {"Luni", "Marti", "Miercuri", "Joi", "Vineri"};
	    
	    public static boolean firstDayOfWeek(String day) {
	    	if (day.equalsIgnoreCase("Luni"))
	    		return true;
	    	return false;
	    }
	    
	    public static boolean otherDayOfWeek(String day, String[] week) {
	    	if (firstDayOfWeek(day))
	    		return false;
	    	
	    	return Arrays.stream(week).parallel().anyMatch(day::contains);
	    }
	    
	    public static boolean isGroup(String group) {
	    	String nrGroup = group.substring(0, 3);
	    	String seriesGroup = group.substring(3, 5);
	    	String subGroup = group.substring(5, 6);
	    	
	    	if (StringUtils.isNumeric(nrGroup) &&
    			!StringUtils.isNumeric(seriesGroup) &&
    			StringUtils.isNumeric(subGroup))
	    		return true;
	    	
	    	return false;
	    }

	    public static void main(String[] args) {

	        try {

	            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = datatypeSheet.rowIterator();
	            
	         // Create a DataFormatter to format and get each cell's value as String
	            DataFormatter dataFormatter = new DataFormatter();
	            
	            System.out.println("{");
	            
	            int newSeries = 1;
	            int newGroup = 1;
	            int newCourse = 1;
	            int numMaterie = 0;
	            
	            
	            
                while (rowIterator.hasNext()) {

                    String ora = null;
                    String numeMaterie = null;
                    Row row = rowIterator.next();
                    
                    
                    
                    // Now let's iterate over the columns of the current row
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int numColls = 0;
                    
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = dataFormatter.formatCellValue(cell);
        	            
                        numColls++;
                        
                        if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
                            continue;
                        }
                        
                        /* setare serie */
                        if (newSeries == 1) {
                            String year = null;
            	            String series = null;
                            year = cellValue.substring(0, 1);
                            series = cellValue.substring(1, 3);
                            newSeries = 0;
                            System.out.println("\t\"" + cellValue.substring(0,3) + "\" : {");                           
                            
                            continue;
                        }
                        
                        /* setare grupa */
                    	if (cellValue.length() == 6 && isGroup(cellValue)) {
                            
                            if (cellValue.charAt(5) == '1') { //cellValue.charAt(2) == '1' && 
                                newGroup = 1;
                            }
                            
                    		if (newGroup == 1) {
                    			
                    			if (cellValue.charAt(2) != '1' && cellValue.charAt(5) == '1') {
                    				System.out.println("\t\t\t\t}");
                    				System.out.println("\t\t\t}");
                    				System.out.println("\t\t},");
                    			}
                    			System.out.println("\t\t\"" + cellValue.substring(0, 5) + "\" : {");
                    			System.out.println("\t\t\t\"" + cellValue + "\" : {");
                    		} else
                    			System.out.println("\t\t\t\t}\n\t\t\t},\n\t\t\t\"" + cellValue + "\" : {");
                    	}
                        
                        
                        /* zi saptamana */
                        if (firstDayOfWeek(cellValue)) {
                        	System.out.println("\t\t\t\t\"" + cellValue + "\" : {");
                        	newCourse = 1;
                        	numMaterie = 0;
                        } else if (otherDayOfWeek(cellValue, week)) {

                   		 	//System.out.println("\t\t\t\t\t}");
                        	System.out.println("\t\t\t\t},\n\t\t\t\t\"" + cellValue + "\" : {");
                        	newCourse = 1;
                        	
                        	/*counter for itemX */
                        	numMaterie = 0;
                        } else {
                        		if (numColls == 1) {
                            	ora = cellValue;
                        	} else if (numColls == 2) {
                        		numMaterie++;
                        		numeMaterie = cellValue; //.split("-")[0];
                        		if (newCourse == 1) {
                            		System.out.println("\t\t\t\t\t\"item" + numMaterie + "\" : {");
                            		newCourse = 0;
                        		} else { /* virgula dinainte de materie */
                        			System.out.println(",\n\t\t\t\t\t\"item" + numMaterie + "\" : {");
                        		}
                        	} else if (numColls == 3) {
                        		String oraInceput = ora.substring(0, 2);
                        		String oraSfarsit = ora.substring(5, 7);
                        		
                        		/* start time */
                        		System.out.println("\t\t\t\t\t\t\"startTime\" : \"" + oraInceput + ":00\",");
                        		
                        		/* end time */
                        		System.out.println("\t\t\t\t\t\t\"endTime\" : \"" + oraSfarsit + ":00\",");
                        		
                        		/* materie */
                        		System.out.println("\t\t\t\t\t\t\"item\" : \"" + numeMaterie + "\",");
                        		
                        		/* professor */
                        		System.out.println("\t\t\t\t\t\t\"professor\" : \"" + cellValue + "\",");
                        	} else if (numColls == 4) {
                        		/* sala curs */
                        		System.out.println("\t\t\t\t\t\t\"place\" : \"" + cellValue + "\",");
                        	} else if (numColls == 5) {
                        		/* tip */
                        		 if (newGroup == 1) {

                         			newGroup = 0;
                         			System.out.println("\t\t\t\t\t\t\"type\" : \"" + cellValue + "\"\n\t\t\t\t\t}");
                    		 	} else {
                    		 		System.out.println("\t\t\t\t\t\t\"type\" : \"" + cellValue + "\"\n\t\t\t\t\t}");
                        		 }
                        	}
                        }
                    }
                }
                
                System.out.println("\t\t\t\t}\n\t\t\t}\n\t\t}\n\t}\n}");
                
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
}
