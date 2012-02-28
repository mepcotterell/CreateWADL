
package parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alok Dhamanaskar
 * @see    LICENSE (MIT style license file).
 */
public class parameter
{
    String p_name;
    String p_type; 
    String p_value; 
    String p_format; 
    String paramDoc;
    String p_required;
    String p_reqRes;
    List<String> optValues = new ArrayList<String>();
    
    parameter()
    {
        p_name = "";
        p_type = "";
        p_format = "";
        paramDoc = "";
        p_required = "";
        p_reqRes ="";
        p_value = "";
        optValues.clear();
    }
    
    void setName(String name)
    {
        this.p_name = name;
    }
    
    void setType(String t)
    { 
        /*
         * Following are the dataTpes that Galaxy has defines by default
         * text, integer, float, boolean, genomebuild, select, data_column, hidden, baseurl, file, data, drill_down 
         **/
        if (t == null)
            t="";
        if (t.equalsIgnoreCase("integer"))
            this.p_type = "xsd:integer";
        else if (t.equalsIgnoreCase("float"))        
            this.p_type = "xsd:float";        
        else if (t.equalsIgnoreCase("boolean"))        
            this.p_type = "xsd:boolean";
        else
            this.p_type = "xsd:string";
    }
    
    
    void setFormat(String t)
    {
        if(t == null) t= "";
        this.p_format = t;
    }
     
    void setparamDoc(String t)
    {
        if(t == null) t= "";        
        this.paramDoc = t;
    }
     
    void setRequired(String t)
    {
        if(t == null) t= "";        
        this.p_required = t;
    }
        
    void setRequestResponse(String t)
    {
        if(t == null) t= "";
        this.p_reqRes = t;
    }
    
    void setOptValues(List a)
    {
        this.optValues = a;
    }
        
    void setOptValues(String a)
    {
        this.optValues.add(a);
    }
    
    void setValue(String t)
    {
        if(t == null) t= "";
        this.p_value = t;
    }
    
    //---------Getters--------------
    
    String getName()
    {
        return this.p_name;
    }
    
    String getType()
    {
        return this.p_type;
    }
    
    String getFormat()
    {
        return this.p_format;
    }
     
    String getLabel()
    {
        return this.paramDoc;
    }
     
    String getRequired()
    {
        return this.p_required;
    }
        
    String getRequestResponse()
    {
        return this.p_reqRes;
    }

    public String getParamDoc()
    {
        return this.paramDoc;
    }
        
    List<String> getOptValues()
    {
        return this.optValues;
    }

}