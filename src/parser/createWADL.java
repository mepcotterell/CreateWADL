package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Alok Dhamanaskar
 * @see Uses http://stax.codehaus.org/ The Streaming API for XML (StAX) Library for parsing XML.
 *      Examples for use available at : http://www.xml.com/pub/a/2003/09/17/stax.html
 */
public class createWADL implements WADLinterface
{

    @Override
    public int countParameters(String wsdlPath)
    {
        int count = 0;
        try
        {
            FileInputStream fstream = new FileInputStream(wsdlPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(br);

            while (true)
            {
                int event = parser.next();
                if (event == XMLStreamConstants.END_DOCUMENT)
                {
                    parser.close();
                    break;
                }
                if (event == XMLStreamConstants.START_ELEMENT)
                {
                    if (parser.getLocalName().equalsIgnoreCase("tests"))
                    {
                        break;
                    }
                    if (parser.getLocalName().equalsIgnoreCase("outputs")
                            || parser.getLocalName().equalsIgnoreCase("param"))
                    {
                        count++;
                    }
                }
            } // while ends


        } catch (java.io.FileNotFoundException ex1)
        {
            System.out.println("The tool config file does not exist\n" + ex1);
        } catch (javax.xml.stream.XMLStreamException ex2)
        {
            System.out.println("The tool config file is not a valid xml Document\n" + ex2);
        } catch (Exception e)
        {
            System.out.println("Unexpected error occured\n" + e);
        } finally
        {
            return count;
        }

    }

    @Override
    public void setMethodInfo(wadl wadl, String wsdlPath)
    {

        try
        {
            FileInputStream fstream = new FileInputStream(wsdlPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(br);

            while (true)
            {
                int event = parser.next();
                if (event == XMLStreamConstants.END_DOCUMENT)
                {
                    parser.close();
                    break;
                }

                if (event == XMLStreamConstants.START_ELEMENT)
                {
                    if (parser.getLocalName().equalsIgnoreCase("tool"))
                    {
                        wadl.setMethodId(parser.getAttributeValue(0));
                        wadl.setMethodName(parser.getAttributeValue(1));
                    }

                    if (parser.getLocalName().equalsIgnoreCase("description"))
                    {
                        wadl.setDescription(parser.getElementText());
                    }
                }
            } // while ends

        } catch (java.io.FileNotFoundException ex1)
        {
            System.out.println("The tool config file does not exist\n" + ex1);
        } catch (javax.xml.stream.XMLStreamException ex2)
        {
            System.out.println("The tool config file is not a valid xml Document\n" + ex2);
        } catch (Exception e)
        {
            System.out.println("Unexpected error occured\n" + e);
        } finally
        {
        }


    }

    @Override
    public void setIOparams(wadl wadl, String wsdlPath)
    {
        String required = "true";
        List<parameter> inputList = new ArrayList<parameter>();
        List<parameter> outputList = new ArrayList<parameter>();

        try
        {
            FileInputStream fstream = new FileInputStream(wsdlPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(br);

            while (true)
            {
                int event = parser.next();
                if (event == XMLStreamConstants.END_DOCUMENT)
                {
                    parser.close();
                    break;
                } else if (event == XMLStreamConstants.END_ELEMENT)
                {
                    if (parser.getLocalName().equalsIgnoreCase("conditional"))
                    {
                        required = "true";
                    }
                } else if (event == XMLStreamConstants.START_ELEMENT)
                {
                    if (parser.getLocalName().equalsIgnoreCase("tests"))
                    {
                        break;
                    }

                    if (parser.getLocalName().equalsIgnoreCase("conditional"))
                    {
                        required = "false";
                    }

                    if (parser.getLocalName().equalsIgnoreCase("data"))
                    {
                        parameter po = new parameter();
                        po.setName(parser.getAttributeValue("", "name"));
                        po.setFormat(parser.getAttributeValue("", "format"));
                        po.setType(parser.getAttributeValue("", "type"));
                        po.setparamDoc(parser.getAttributeValue("", "label"));
                        po.setRequestResponse("response");
                        outputList.add(po);
                    }// if ends

                    if (parser.getLocalName().equalsIgnoreCase("param"))
                    {
                        parameter pi = new parameter();
                        pi.setName(parser.getAttributeValue("", "name"));
                        String paramDoc = parser.getAttributeValue("", "label");
                        if (parser.getAttributeValue("", "help") != null)
                            paramDoc += ", "+ parser.getAttributeValue("", "help");
                        pi.setparamDoc(paramDoc);
                        pi.setType(parser.getAttributeValue("", "type"));
                        pi.setFormat(parser.getAttributeValue("", "format"));
                        pi.setRequestResponse("request");
                        pi.setRequired(required);
                        pi.setValue(parser.getAttributeValue("", "value"));
                        inputList.add(pi);

                        if (parser.getAttributeValue("", "type").equalsIgnoreCase("select"))
                        {
                            parser.nextTag();
                            while (parser.getLocalName().equalsIgnoreCase("option"))
                            {
                                pi.setOptValues(parser.getAttributeValue("", "value")
                                        + "" + parser.getElementText());
                                parser.nextTag();
                            } // While ends
                        }// if ends 
                    }//if ends (input param)

                }//if ends
            } // while ends

            wadl.setInputs(inputList);
            wadl.setOutputs(outputList);
        } catch (java.io.FileNotFoundException ex1)
        {
            System.out.println("The tool config file does not exist\n" + ex1);
        } catch (javax.xml.stream.XMLStreamException ex2)
        {
            System.out.println("The tool config file is not a valid xml Document\n" + ex2);
        } catch (Exception e)
        {
            System.out.println("Unexpected error occured\n" + e);
        } finally
        {
        }


    }

    @Override
    public String generateWADL(String wsdlPath)
    {
        String fileName = "";
        try
        {

            wadl wadlFile = new wadl();
            setMethodInfo(wadlFile, wsdlPath);
            setIOparams(wadlFile, wsdlPath);

            String wadl = wadlFile.header;
            wadl += "    <resources base='WADLForGalaxyTools/'> \n"
                    + "    <resource path='galaxyTool" + wadlFile.getmethodId() + "'>\n";

            List<parameter> ipParams = wadlFile.getInputs();
            List<parameter> opParams = wadlFile.getOutputs();

            String request = "\t\t<request>\n";
            for (parameter pp : ipParams)
            {
                request += "\t\t\t<param name='" + pp.p_name + "' "
                        + "type='" + pp.p_type + "' "
                        + "required='" + pp.p_required + "' ";
                if (!pp.p_value.equals(""))
                {
                    request += "default='" + pp.p_value + "' ";
                }
                request += ">";
                if (!pp.paramDoc.equals(""))
                {
                    request += "\n\t\t\t\t<doc title='description'>" + pp.paramDoc + "</doc>\n";
                }
                if (!pp.optValues.isEmpty())
                {
                    for (String option : pp.optValues)
                    {
                        request += "\t\t\t\t<option value='" + option + "' />\n";
                    }
                }
                request += "\t\t\t</param>\n";
            }

            request += "\t\t</request>\n";

            String response = "\t\t<response>\n";
            for (parameter op : opParams)
            {
                response += "\t\t\t<param name='" + op.p_name + "' type='" + op.p_type + "' > \n";
                if (!op.paramDoc.equals(""))
                {
                    response += "\t\t\t\t<doc title='description'>" + op.paramDoc + "</doc>\n";
                }
                response += "\t\t\t</param>\n";
            }
            response += "\t\t</response>\n";

            String method = "\t<method name='" + wadlFile.getmethodName() + "' id='" + wadlFile.getmethodId() + "'> \n"
                          + "\t\t<doc title='description'>" + wadlFile.getDescription() + "</doc>\n";
            method += request;
            method += response;
            method += "\t</method>\n";
            wadl += method;
            wadl += "    </resource>\n"
                    + "    </resources>\n</application>";
            System.out.println(wadl);

            fileName = wsdlPath.replace(".xml", "WADL.wadl");
            FileWriter file;
            file = new FileWriter(fileName);
            BufferedWriter br = new BufferedWriter(file);
            br.write(wadl);
            br.close();
            file.close();

        } catch (java.io.IOException ex)
        {
            fileName = "WADL File could not be created due to exception" + ex.getMessage();
        } 
        catch (Exception e)
        {
            fileName = "Unexpected error occured" + e;
        }
        finally
        {
            return fileName;
        }
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        String fileName = "";
        createWADL wadl = new createWADL();

        while (true)
        {
            System.out.print("\nEnter q to quit.\n\n"
                    + "Enter path of the Galaxy tool Config file(.xml) to be concerted to WADL \n e.g. /home/alok/Desktop/tmp/blast.xml : ");
            fileName = s.next();
            if (fileName.equalsIgnoreCase("q"))
            {
                break;
            } else
            {
                System.out.println("The WADL file is generated at " + wadl.generateWADL(fileName.trim()));
            }
        }

    }// main ends
}
