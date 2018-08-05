package read_fucking_excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel {

	    private static final String FILE_NAME = "G:\\github\\Licenta\\orar\\modified\\Book2.xlsx";

	    public static void main(String[] args) {

	        try {

	            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = datatypeSheet.rowIterator();
	            
	         // Create a DataFormatter to format and get each cell's value as String
	            DataFormatter dataFormatter = new DataFormatter();
	            
	            System.out.println("{");
	            
	            int serieSet = 1;
	            int newSeries = 1;
	            int newCourse = 1;
	            
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
                        if (serieSet == 1) {
                        	System.out.println("\t\"" + cellValue.charAt(0) + "\" : {");
                            System.out.println("\t\t\"" + cellValue.substring(1,3) + "\" : {");
                            serieSet = 0;
                            break;
                        }
                        
                        /* zi saptamana */
                        if (cellValue.equalsIgnoreCase("Luni")) {
                        	System.out.println("\t\t\t\t\"" + cellValue + "\" : {");
                        	newCourse = 1;
                        } else if (cellValue.equalsIgnoreCase("Marti") || cellValue.equalsIgnoreCase("Miercuri") ||
                        		   cellValue.equalsIgnoreCase("Joi") || cellValue.equalsIgnoreCase("Vineri")) {
                        	System.out.println("\t\t\t\t},\n\t\t\t\t\"" + cellValue + "\" : {");
                        	newCourse = 1;
                        } 
                        /* grupa */
                        	else if (cellValue.equals("313s1") || cellValue.equals("313s2")) {
                        		if (newSeries == 1) {
                        			System.out.println("\t\t\t\"" + cellValue + "\" : {");
                        			newSeries = 0;
                        		} else {
                        			if (cellValue.equals("313s1")) {
                        				System.out.println("},\n\t\t\t\"" + cellValue + "\" : {");
                        			} else {
                        				System.out.println("\t\t\t\t}\n\t\t\t},\n\t\t\t\"" + cellValue + "\" : {");
                        			}
                        		}
                        	} else {
                        		if (numColls == 1) {
                            	ora = cellValue;
                        	} else if (numColls == 2) {
                        		numeMaterie = cellValue.split("-")[0];
                        		if (newCourse == 1) {
                            		System.out.println("\t\t\t\t\t\"" + cellValue + "\" : {");
                            		newCourse = 0;
                        		} else { /* virgula dinainte de materie */
                        			System.out.println(",\n\t\t\t\t\t\"" + cellValue + "\" : {");
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
                        		System.out.println("\t\t\t\t\t\t\"type\" : \"" + cellValue + "\"\n\t\t\t\t\t}");
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
