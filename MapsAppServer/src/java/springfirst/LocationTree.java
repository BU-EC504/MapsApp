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
        String file = "/Users/haydarkarrar/Desktop/NationalFile.txt";
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
    
    public static ArrayList<String> findArea(float[] coords, float[] dimensions)
    {
        //the data arraylist has the whole line which needs to be parsed again
        List<String> indexes = tree.search(coords, dimensions);
        ArrayList<String> temp= new ArrayList<String>();
        temp.addAll(indexes);
        return temp;
    }
}
