/* **************************************************** **
 * FileList.java
 * -  Keeps an associative array 
 * -  Searches an associative array for keywords
 * **************************************************** **/
package org.mac.common.filescan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FileList {
	private ConfigFile conf = new ConfigFile();
	private File[]  files;
	private ArrayList<String> keywords   = new ArrayList<String>();
	private Map<File,Boolean> foundFiles = new HashMap<File,Boolean>();
	
	String directoryName = "/dnload";
	
	public FileList( String directoryName) {
		this.directoryName = directoryName;
	}

	public FileList() {
	}
	
	public void Search(String directoryName, ArrayList<String> keywords) {
		this.directoryName = directoryName;
		this.keywords = keywords;
		
		process();
		
	}

	public void Search(String directoryName, String keyword ) {
		this.directoryName = directoryName;
		
		keywords.add(keyword);

		process();

	}
	
	public void ClearSearch() {
		keywords.clear();
	}
	
	public void SearchFor(String directoryName, String keyword ) {
		this.directoryName = directoryName;

		keywords.clear();
		keywords.add(keyword);
	    
		process();
	}
	
	public void DebugFiles(String directoryName) {
		this.directoryName = directoryName;
		try {
		    UpdateFileKeywords();
			process();
			DumpList(false);
		}
		catch(IOException e) {
			System.out.println("exception thrown: " + e.getMessage());
		}
		
	}
	
	public void process() {
		BuildList();
	}


	public void UpdateFileKeywords() throws IOException {
		
        try {
            keywords = conf.getArrKeywords();
        }
        catch (IOException e ) {
        	System.out.println( "No Keywords! -- ");
        }
	}
	
	public void BuildList() {
        File dir = new File(directoryName);
        
        files =  dir.listFiles();
        
        
        for (File file : files ) {
        	boolean is_keyword_match=false;
        	for (String keyword: keywords) {
        		if ( file.getName().toLowerCase().contains(keyword) ) {
        	        is_keyword_match=true;
        	        foundFiles.put(file,true);
        	        break;
        		}
        	}
        	
        	if ( is_keyword_match == false ) {
//        		System.out.println("File : " + file.getName() + " does not match keywords");
        		foundFiles.put(file, false);
        	}
        }
	}
	
	public void DumpList( boolean isfound) {
		for(Map.Entry<File,Boolean> entry : foundFiles.entrySet()){
			if (entry.getValue() == isfound ) {
		        System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			}
		}

	}
}

