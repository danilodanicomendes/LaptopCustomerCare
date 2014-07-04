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
    private Project myProject;
    private Concept myConcept;
    private CSVImporter myCsvImporter;
    private String concept = "Laptop";
    private String columnSeparator = ";";
    private String multipleValueSperator = ",";
    private String laptopsCaseBase = "LaptopsCaseBase";
    private int countCases;
    private ICaseBase cb;

    public CBREngine(){
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
            while (myCsvImporter.isImporting()){
            Thread.sleep(1000);
            System.out.print(".");
            }
            countCases = myProject.getCurrentNumberOfCases();
 
            this.cb = myProject.getCaseBases().get(laptopsCaseBase);
            
            //Initiate a query
            //create a new retrieval
            Retrieval ret = new Retrieval(myConcept, cb);
            
            //specify the retrieval method
            ret.setRetrievalMethod(Retrieval.RetrievalMethod.RETRIEVE_SORTED);
            
            // create a query instance 
            Instance query = ret.getQueryInstance();
            
            //insert values into the query
            SymbolDesc bluetooth = (SymbolDesc)  myConcept.getAllAttributeDescs().get("Bluetooth");
            
            query.addAttribute(bluetooth, bluetooth.getAttribute("Yes"));
            
           // LinkedList<Attribute> list = new LinkedList<Attribute>();
            
          ret.start();
          List<Pair<Instance, Similarity>> result = ret.getResult();
          // get the case name
            result.get(0).getFirst().getName();
// get the similarity value
            result.get(0).getSecond().getValue();
            
            
            
            
            
            
            
            
            

        } catch (Exception ex) {
           // Logger.getLogger(Negotiation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    