/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;


/**
 *
 * @author Alok Dhamanaskar
 * @see    LICENSE (MIT style license file).
 */

public interface WADLinterface
{
    
 public int countParameters(String wsdlPath); 
 
 public void setIOparams(wadl wadl, String wsdlPath);
 
 public void setMethodInfo(wadl wadl, String wsdlPath);
 
 public String generateWADL(String wsdlPath);
    
}
