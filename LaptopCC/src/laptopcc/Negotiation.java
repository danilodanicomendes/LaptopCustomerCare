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
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.FloatDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.Retrieval.RetrievalMethod;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.io.CSVImporter;
import de.dfki.mycbr.core.*;
import de.dfki.mycbr.core.model.*;
import de.dfki.mycbr.util.Pair;
import de.dfki.mycbr.io.CSVImporter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mario
 */
public class Negotiation {
    Project myProject;
    Concept myConcept;
    CSVImporter myCsvImporter;
    String concept = "Laptop";
    String columnSeparator = ";";
    String multipleValueSperator = ",";
    public Negotiation(){
        try {
            //System.out.println(System.getProperty("user.dir"));
             
//this.myProject = new Project(System.getProperty("user.dir")+"");
            this.myProject = new Project(System.getProperty("user.dir") + "/CBR/LaptopCustomerCare.prj"); //Project Loading
            myConcept = myProject.getConceptByID(concept); //Concept setup
            
            myCsvImporter = new CSVImporter(System.getProperty("user.dir") + "/CBR/LaptopsWithoutBooleanHDDConc.csv", myConcept); // import csv
            myCsvImporter.readData(); // read csv data
            myCsvImporter.checkData(); //check formal validity of the data
            myCsvImporter.addMissingValues(); // add missing values with default values
            myCsvImporter.addMissingDescriptions(); //add default descriptions
            
            myCsvImporter.doImport();
            
            
            //
            
            

        } catch (Exception ex) {
            //Logger.getLogger(Negotiation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(System.getProperty("user.dir"));
        }
    }
}
