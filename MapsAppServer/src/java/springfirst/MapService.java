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
import java.util.HashMap;
import java.util.List;
import springfirst.RTree.SeedPicker;

/**
 *
 * @author Dana
 */
public class MapService {
    
    public static String FILENAME = "C://Users/Dana/Downloads/NationalFile2";
    public static RTree<Integer> tree;
    public static HashMap<String, Integer> proviceHash;
    public static HashMap<Integer, String> reverseProviceHash;
    //constructor
    public MapService()
    {
         proviceHash = new HashMap<String, Integer>();
         reverseProviceHash = new HashMap<Integer, String>();
         
         tree = new RTree<Integer>(100,50,2, SeedPicker.LINEAR);
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
        
        //Create object of FileReader
        FileReader inputFile = new FileReader(FILENAME);
        //Instantiate the BufferedReader Class
        BufferedReader bufferReader = new BufferedReader(inputFile);
        //Variable to hold the one line data
        String line = bufferReader.readLine();
        int count = 0;
        
        long start = System.currentTimeMillis();
        while ((line = bufferReader.readLine()) != null)
        {
            //create an item for this line
            String[] str = line.split("\\s+");
            if(str.length>1)
            {
                float[] coords = new float[]{Float.parseFloat(str[str.length-2]),Math.abs(Float.parseFloat(str[str.length-1]))};
                //there are issues with the file that we can deal with later
                String refName = "";
                for(int i=0;i<str.length-2;i++)
                {
                    refName=refName+" "+str[i];
                }
                //str[0]+" "+str[1];
                //tree = tree.add(Entry.entry(item, Geometries.point(Double.parseDouble(str[2]),Double.parseDouble(str[3]))));
                try
                {
                    if(proviceHash.containsKey(refName)){
                        tree.insert(coords, proviceHash.get(refName));
                    }
                    else{
                        proviceHash.put(refName, count);
                        reverseProviceHash.put(count, refName);
                        tree.insert(coords, proviceHash.get(refName));
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println(count);
                count++;
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Time spend:" + elapsed/1000);
        System.out.println(count + " final items inserted to the tree");
        //Close the buffer reader
        bufferReader.close();
        
    }
    
    public static ArrayList<Location> search(float[] coords, float[] dimensions)
    {   
        return tree.search(coords, dimensions);
    }
    
    
}
