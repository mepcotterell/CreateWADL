
package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alok Dhamanaskar
 * @see    LICENSE (MIT style license file).
 * 
 */
public class replaceMultiple
{
    /**
     * Replace is the method that, given a file and a HashMap where key is String to search for,
     * and value is the String to replace with, will replace all the occurrences of keys with the 
     * corresponding value.
     * 
     *@param string2replace It is a HashMap where key is String to search for and value is the String to replace with
     *@param filePath It is the path of the file in which you want to do the find and replace 
     *
     *@return String that lists all the replaces made, number of replaces made and path of the output file 
     */
    
    public static String replace(HashMap<String,String> string2replace, String filePath)
    {
        String outcome = "";
        String newFile = "";
        int count = 0;
        try
        {
            if (string2replace.isEmpty() || filePath.equals(""))
            {
                outcome = "The list for replacing or the file path cannot be null";
            }
            else
            {
                String newFilePath = filePath + "OP" ;
                //read file
                FileReader r = new FileReader(filePath);
                Scanner sRead = new Scanner(r);
                String temp="";
                Collection<String> check = string2replace.keySet();
                    
                while (sRead.hasNext())
                {
                    temp = sRead.nextLine();
                    for (String s : check)
                        if (temp.contains(s))
                        {
                            outcome+= "'"+ s + "' REPLACED WITH '" + string2replace.get(s) + "'\n";
                            temp = temp.replace(s, string2replace.get(s));                        
                            count++;
                        }
                    newFile+=temp + "\n";
                }
            
                File f = new File(newFilePath);
                FileWriter w = new FileWriter(f);
                w.write(newFile);    
                w.close();
                outcome +="\n--------------------------------------------------------------\n";
                outcome += "Replacements done in "+ count +" cases\n";
                outcome += "New File written at: " + newFilePath;
            } // else ends
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(replaceMultiple.class.getName()).log(Level.SEVERE, null, ex);
            outcome = "Error reading or Writing File at " + filePath + "\nMake Sure you have Read-Write premissions to the directory you are reading from !";
        }
        catch(Exception ex)
        {
            outcome = "Unexpected error occured: " + ex;        
        }
        finally
        {
            return outcome;
        }
        
    }// replace ends
    
    public static void main(String[] args)
    {
        
        //Test run
    
        HashMap<String, String> list = new HashMap<String, String>();
        list.put("\"http://purl.obolibrary.org/obo/webService.owl#Class_0030\"", "\"http://purl.obolibrary.org/obo/OBIws_0000116\"");
        list.put("\"http://purl.obolibrary.org/obo/webService.owl#Class_0031\"", "\"http://purl.obolibrary.org/obo/OBIws_0000117\"");
        list.put("\"http://purl.obolibrary.org/obo/webService.owl#lower_triangular_matrix\"", "\"http://purl.obolibrary.org/obo/OBIws_0000118\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_50\"", "\"http://purl.obolibrary.org/obo/OBIws_0000119\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_52\"", "\"http://purl.obolibrary.org/obo/OBIws_0000120\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_51\"", "\"http://purl.obolibrary.org/obo/OBIws_0000121\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_54\"", "\"http://purl.obolibrary.org/obo/OBIws_0000122\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_53\"", "\"http://purl.obolibrary.org/obo/OBIws_0000123\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_56\"", "\"http://purl.obolibrary.org/obo/OBIws_0000124\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_55\"", "\"http://purl.obolibrary.org/obo/OBIws_0000125\"");
        list.put("\"http://purl.obolibrary.org/obo/webService.owl#Class_0023\"", "\"http://purl.obolibrary.org/obo/OBIws_0000126\"");
        list.put("\"http://purl.obolibrary.org/obo/obi.owl#Class_57\"", "\"http://purl.obolibrary.org/obo/OBIws_0000127\"");

    
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/muscle.sawsdl"));
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/ncbiblast.sawsdl"));
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/phylip.sawsdl"));
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/psiblast.sawsdl"));        
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/tcoffee.sawsdl"));        
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/WSConverter.sawsdl"));
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/WSDbfetch.sawsdl"));        
        System.out.println(replace(list, "/home/alok/Desktop/tmp/test/wublast.sawsdl"));
        
    }
}
