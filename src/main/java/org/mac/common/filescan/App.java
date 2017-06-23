package org.mac.common.filescan;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
                
        FileList fileList = new FileList();
        
        fileList.Search("/dnload", "clojure ");
        //fileList.DebugFiles("/dnload");
        fileList.DumpList(true);
               
    }
}
