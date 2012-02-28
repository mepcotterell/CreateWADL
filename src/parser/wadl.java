/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alok Dhamanaskar
 * @see    LICENSE (MIT style license file).
 * 
 */
public class wadl
{

    private String methodName;
    private String methodId;
    private String description;
    final String header = "<?xml version='1.0'?>\n"
            + "<application xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n"
            + "xsi:schemaLocation='http://wadl.dev.java.net/2009/02 wadl.xsd'\n"
            + "xmlns:xsd='http://www.w3.org/2001/XMLSchema'\n"
            + "xmlns='http://wadl.dev.java.net/2009/02'>\n";
    final String footer = "</application>";
    List<parameter> inputs = new ArrayList<parameter>();
    List<parameter> outputs = new ArrayList<parameter>();

    //Constructor with no Parameters
    wadl()
    {
        this.methodName = "";
        this.methodId = "";
        this.description = "";
        this.inputs = null;
        this.outputs = null;
    }

    //Constructor with all the Parameters
    wadl(String methodName, String methodId, String description,
            List<parameter> inputs, List<parameter> outputs)
    {
        this.methodName = methodName;
        this.methodId = methodId;
        this.description = description;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    //Constructor without all the Parameters except description   
    wadl(String methodName, String methodId, List<parameter> inputs,
            List<parameter> outputs)
    {
        this.methodName = methodName;
        this.methodId = methodId;
        this.description = "";
        this.inputs = inputs;
        this.outputs = outputs;
    }

    // getters
    public String getmethodName()
    {
        return this.methodName;
    }

    public String getmethodId()
    {
        return this.methodId;
    }

    public String getDescription()
    {
        return this.description;
    }

    public List<parameter> getInputs()
    {
        return this.inputs;
    }

    public List<parameter> getOutputs()
    {
        return this.outputs;
    }

    public String getHeader()
    {
        return this.header;
    }

    public String getFooter()
    {
        return this.footer;
    }

    //setters
    /**
     * This method sets the list of objects of class parser.parameter to the list of input 
     * parameters for the the object of class WADL, for which the function is called.
     * 
     * @param Accepts a List of Object of class parser.parameter
     * @return Does not return Anything
     */
    public void setInputs(List<parameter> inputList)
    {
        this.inputs = inputList;
    }

    /**
     * This method sets the list of objects of class parser.parameter passed to it, to the list of output 
     * parameters for the the object of class WADL, for which the function is called.
     * 
     * @param Accepts a List of Object of class parser.parameter
     * @return Does not return Anything
     */
    public void setOutputs(List<parameter> outputList)
    {
        this.outputs = outputList;
    }

    /**
     * The method adds the object of class parser.parameter to the list of input 
     * parameters for the the object of class WADL, for which the function is called.
     * 
     * @param Object of class parser.parameter
     * @return Does not return Anything
     */
    public void addInputParam(parameter p)
    {
        this.inputs.add(p);
    }

    /**
     * The method adds the object of class parser.parameter to the list of output 
     * parameters for the the object of class WADL, for which the function is called.
     * 
     * @param Object of class parser.parameter
     * @return Does not return Anything
     */
    public void addOutputParam(parameter p)
    {
        this.outputs.add(p);
    }
    
   /**
     * The method sets the methodName for the the object of class WADL, 
     * for which the function is called.
     * 
     * @param methodName as String
     * @return Does not return Anything
     */
    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }
    
   /**
     * The method sets the methodId for the the object of class WADL, 
     * for which the function is called.
     * 
     * @param methodId as String
     * @return Does not return Anything
     */
    public void setMethodId(String methodId)
    {
        this.methodId = methodId;
    }
    
   /**
     * The method sets the description for the the object of class WADL, 
     * for which the function is called.
     * 
     * @param description as String
     * @return Does not return Anything
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

}
