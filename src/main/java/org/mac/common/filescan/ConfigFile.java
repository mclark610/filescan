/* **************************************************** **
 * FileConfig.java
 * -  Loads a list of keywords from the file and puts into
 *    array
 * -  Searches an associative array for keywords
 * **************************************************** **/
package org.mac.common.filescan;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;;

public class ConfigFile {
	
    private ArrayList<String> arrKeywords = new ArrayList<String>();
    private String fname = "/src/filescan/keywords.txt";
	private boolean is_initialized = false;
	

    
    public void initKeywords() throws IOException {
    	
    	if (!is_initialized) {
	    	BufferedReader br = null;
	    	FileReader     fr = null;
	    	arrKeywords = new ArrayList<String>();
	    	
	    	try {
				fr = new FileReader(fname);
				br = new BufferedReader(fr);
				
				String buf;
		        
				while((buf = br.readLine()) != null) {
					System.out.println(buf);
					arrKeywords.add(buf);
				}
				
				is_initialized = true;
	
	    	} 
	    	catch (IOException e) {
				System.out.println("Exception occurred in ConfigFile: " + e.getMessage());
			} 
	    	finally {
				if (br != null ) {
					br.close();
				}
			}
    	}
    }

	public ArrayList<String> getArrKeywords() throws IOException {
		if (!is_initialized) {
			initKeywords();
		}
		return arrKeywords;
	}
}
