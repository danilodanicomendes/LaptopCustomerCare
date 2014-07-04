/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptopcc;
import java.text.ParseException;
import java.util.List;
import de.dfki.mycbr.core.Project;    
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.casebase.Attribute;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.FloatDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.io.CSVImporter;
import de.dfki.mycbr.core.*;
import de.dfki.mycbr.core.model.*;
import de.dfki.mycbr.util.Pair;
import de.dfki.mycbr.io.CSVImporter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mario
 */
public class CBREngine {
    private static String datapath = System.getProperty("user.dir") + "/CBR/";
    private static String projectName = "LaptopCustomerCare.prj";
    private static Concept myConcept;
    private static CSVImporter myCsvImporter;
    private static String concept = "Laptop";
    private static String columnSeparator = ";";
    private static String multipleValueSperator = ",";
    private static String laptopsCaseBase = "LaptopsCaseBase";

    public static Concept getMyConcept() {
        return myConcept;
    }

    public static void setMyConcept(Concept myConcept) {
        CBREngine.myConcept = myConcept;
    }

    public static CSVImporter getMyCsvImporter() {
        return myCsvImporter;
    }

    public static void setMyCsvImporter(CSVImporter myCsvImporter) {
        CBREngine.myCsvImporter = myCsvImporter;
    }

    public static String getConcept() {
        return concept;
    }

    public static void setConcept(String concept) {
        CBREngine.concept = concept;
    }

    public static String getColumnSeparator() {
        return columnSeparator;
    }

    public static void setColumnSeparator(String columnSeparator) {
        CBREngine.columnSeparator = columnSeparator;
    }

    public static String getMultipleValueSperator() {
        return multipleValueSperator;
    }

    public static void setMultipleValueSperator(String multipleValueSperator) {
        CBREngine.multipleValueSperator = multipleValueSperator;
    }

    public static String getLaptopsCaseBase() {
        return laptopsCaseBase;
    }

    public static void setLaptopsCaseBase(String laptopsCaseBase) {
        CBREngine.laptopsCaseBase = laptopsCaseBase;
    }
   
    
    public Project createCBRProject(){
        Project myProject = null;
        try {
            //System.out.println(System.getProperty("user.dir"));
             
//this.myProject = new Project(System.getProperty("user.dir")+"");
            myProject = new Project(datapath +projectName); //Project Loading
            myConcept = myProject.getConceptByID(concept); //Concept setup
            
            myCsvImporter = new CSVImporter(System.getProperty("user.dir") + "/CBR/LaptopsWithoutBooleanHDDConc.csv", myConcept); // import csv
            myCsvImporter.readData(); // read csv data
            myCsvImporter.checkData(); //check formal validity of the data
            myCsvImporter.addMissingValues(); // add missing values with default values
            myCsvImporter.addMissingDescriptions(); //add default descriptions
            
            myCsvImporter.doImport();
            while (myCsvImporter.isImporting()){
            Thread.sleep(1000);
            System.out.print(".");
            }
          
             
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        return myProject;
    }
}
    