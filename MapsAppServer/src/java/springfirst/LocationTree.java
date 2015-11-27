/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dana
 */
public class LocationTree {
    //public static RTree<String, Geometry> tree= RTree.star().maxChildren(6).create();
    public static RTree<String> tree;
    public static ArrayList<String> data; 
    //constructor
    public LocationTree()
    {
        
         tree = new RTree<String>(50,25,2);
         data = new ArrayList<String>();
         System.out.println("location tree initialised");
        //tree = RTree.star().maxChildren(6).create();
         try
         {
             loadTree();
         }
         catch(Exception e)
         {
             System.out.println("something happened: "+e.getMessage()+" "+e.toString());
         }         
         
    }
    
    public static void clearTree()
    {
        tree.clear();
    }
    
    public static void loadTree() throws FileNotFoundException, IOException
    {
        //first read file contents and insert to tree element by element
        String file = "C://Users/Dana/Downloads/NationalFile";
        //Create object of FileReader
        FileReader inputFile = new FileReader(file);
        //Instantiate the BufferedReader Class
        BufferedReader bufferReader = new BufferedReader(inputFile);
        //Variable to hold the one line data
        String line;
        int count = 0;
        
        while ((line = bufferReader.readLine()) != null)
        {
            //create an item for this line
            String[] str = line.split("\\s+");//saved as tabbed variables in the text file
            //0 is state, 1 is province, 2 is lat and 3 is long
            if(count!=0)//first item is the table heading
            {
               //Item item = new Item(str[0],str[1]);
                if(str.length>1)
                {
                    float[] coords = new float[]{Float.parseFloat(str[str.length-2]),Math.abs(Float.parseFloat(str[str.length-1]))};
                    //there are issues with the file that we can deal with later
                    String refName = str[0]+" "+str[1];
                    //tree = tree.add(Entry.entry(item, Geometries.point(Double.parseDouble(str[2]),Double.parseDouble(str[3]))));
                    try
                    {
                        tree.insert(coords, line);  
                        //data.add(line);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                
                
            }
            System.out.println(count+" items");
            count++;
            
        }
        
        System.out.println(count+" final items inserted to the tree");
        //Close the buffer reader
        bufferReader.close();
        
    }
    
    public static ArrayList<Location> findArea(float[] coords, float[] dimensions)
    {
        //the data arraylist has the whole line which needs to be parsed again
        List<String> indexes = tree.search(coords, dimensions);
        
        ArrayList<Location> temp= new ArrayList<Location>();
        
        for(String str : indexes){
            Location loc = parseLocation(str);
            temp.add(loc);
        }
        
        return temp;
    }
    
    private static Location parseLocation(String str){
        
        String[] strArr = str.split("\\s+");
        
        double latitude = Double.parseDouble(strArr[strArr.length-2]);
        double longitude = Double.parseDouble(strArr[strArr.length-1]);
        
        String state,province;
        state=strArr[0];
        province="";
        for(int i=1;i<strArr.length-2;i++)
        {
            province=province+" "+strArr[i];
        }
        
        return new Location(state,province, latitude, longitude);
    }
    
}
