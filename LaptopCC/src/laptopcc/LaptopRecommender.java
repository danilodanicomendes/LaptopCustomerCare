package laptopcc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.FloatDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.StringDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.Retrieval.RetrievalMethod;
import de.dfki.mycbr.core.similarity.AmalgamationFct;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.core.similarity.config.AmalgamationConfig;
import de.dfki.mycbr.util.Pair;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaptopRecommender {

    private CBREngine engine;
    private Project rec;
    private DefaultCaseBase cb;
    public Concept myConcept;

    public void loadengine() {
        engine = new CBREngine();
        rec = engine.createCBRProject();
        cb = (DefaultCaseBase) rec.getCaseBases().get(engine.getLaptopsCaseBase());
        myConcept = rec.getConceptByID(engine.getConcept());
    }

    public String solveQuery(
            String bluetooth,
            String brand,
            String cpuBrand,
            Float cpuSpeed,
            String cpuType,
            Integer cacheSize,
            String dvd,
            String graphicCard,
            Integer hdSize,
            String hdType,
            Float lcdInches,
            Integer laptopId,
            String model,
            String os,
            Float price,
            Integer ramSize,
            String segment,
            String webcam,
            Float weight,
            String wireless,
            Integer numberOfCases,
            AmalgamationFct amalgamationFcn
    ) {
        String answer = "";
        Retrieval ret = new Retrieval(myConcept, cb);

        myConcept.setActiveAmalgamFct(amalgamationFcn);

        //ret.setRetrievalMethod(RetrievalMethod.RETRIEVE_K_SORTED);
        ret.setRetrievalMethod(RetrievalMethod.RETRIEVE_K);
        Instance query = ret.getQueryInstance();
        SymbolDesc bluetoothDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("Bluetooth");
        query.addAttribute(bluetoothDesc, bluetoothDesc.getAttribute(bluetooth));

        SymbolDesc brandDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("Brand");
        query.addAttribute(brandDesc, brandDesc.getAttribute(brand));

        SymbolDesc cpuBrandDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("CPU Brand");
        query.addAttribute(cpuBrandDesc, cpuBrandDesc.getAttribute(brand));

        FloatDesc cpuSpeedDesc = (FloatDesc) myConcept.getAllAttributeDescs().get("CPU Speed");
        try {
            query.addAttribute(cpuSpeedDesc, cpuSpeedDesc.getAttribute(cpuSpeed));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringDesc cpuTypeDesc = (StringDesc) myConcept.getAllAttributeDescs().get("CPU Type");
        try {
            query.addAttribute(cpuTypeDesc, cpuTypeDesc.getAttribute(cpuType));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        IntegerDesc cacheSizeDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("Cache Size");
        try {
            query.addAttribute(cacheSizeDesc, cacheSizeDesc.getAttribute(cacheSize));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        SymbolDesc dvdDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("DVD");
        query.addAttribute(dvdDesc, dvdDesc.getAttribute(dvd));

        StringDesc graphicCardDesc = (StringDesc) myConcept.getAllAttributeDescs().get("Graphic Card");
        try {
            query.addAttribute(graphicCardDesc, graphicCardDesc.getAttribute(graphicCard));
        } catch (ParseException ex) {
            ex.printStackTrace();

        }

        IntegerDesc hdSizeDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("HD Size");
        try {
            query.addAttribute(hdSizeDesc, hdSizeDesc.getAttribute(hdSize));
        } catch (ParseException ex) {
            ex.printStackTrace();

        }

        SymbolDesc hdTypeDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("HD Type");
        query.addAttribute(hdTypeDesc, hdTypeDesc.getAttribute(hdType));

        FloatDesc lcdInchesDesc = (FloatDesc) myConcept.getAllAttributeDescs().get("LCD Inches");
        try {
            query.addAttribute(lcdInchesDesc, lcdInchesDesc.getAttribute(lcdInches));
        } catch (ParseException ex) {
            ex.printStackTrace();

        }

        IntegerDesc laptopIdDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("Laptop ID");
        try {
            query.addAttribute(laptopIdDesc, laptopIdDesc.getAttribute(laptopId));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        StringDesc modelDesc = (StringDesc) myConcept.getAllAttributeDescs().get("Model");
        try {
            query.addAttribute(modelDesc, modelDesc.getAttribute(model));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        SymbolDesc osDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("OS");
        query.addAttribute(osDesc, osDesc.getAttribute(os));

        FloatDesc priceDesc = (FloatDesc) myConcept.getAllAttributeDescs().get("Price");
        try {
            query.addAttribute(priceDesc, priceDesc.getAttribute(price));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        IntegerDesc ramSizeDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("RAM size");
        try {
            query.addAttribute(ramSizeDesc, ramSizeDesc.getAttribute(ramSize));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        StringDesc segmentDesc = (StringDesc) myConcept.getAllAttributeDescs().get("Segment");
        try {
            query.addAttribute(segmentDesc, segmentDesc.getAttribute(segment));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        SymbolDesc webcamDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("Webcam");
        query.addAttribute(webcamDesc, webcamDesc.getAttribute(webcam));

        FloatDesc weightDesc = (FloatDesc) myConcept.getAllAttributeDescs().get("Weight");
        try {
            query.addAttribute(weightDesc, weightDesc.getAttribute(weight));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        SymbolDesc wirelessDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("Wireless");
        query.addAttribute(wirelessDesc, wirelessDesc.getAttribute(wireless));
        ret.start();
        List<Pair<Instance, Similarity>> result = ret.getResult();
        
        if (result.size() > 0) {
            // get the best case's name
            String casename = result.get(0).getFirst().getName();
            // get the similarity value
            Double sim = result.get(0).getSecond().getValue();
            answer = "\n-----\nBest Match: " + casename + "\nSimilarity: " + sim;

            ArrayList<Hashtable<String, String>> liste = new ArrayList<Hashtable<String, String>>();
            // if more case results are requested than we have in our case base at all:
            if (numberOfCases >= cb.getCases().size()) {
                numberOfCases = cb.getCases().size();
            } else
                answer += "\nOther matches:\n";

            for (int i = 1; i < numberOfCases; i++) {
                if (i < 5)
                    answer += result.get(i).toString() + "\n";
                else
                    break;
            }
            answer += "\n-----";
        } else {
            answer = "Empty result.";
        }

        //answer = Arrays.toString(result.toArray());

        return answer;
    }

    /**
     * This method delivers a Hashtable which contains the Attributs names
     * (Attributes of the case) combined with their respective values.
     *
     * @author weber,koehler,namuth
     * @param r = An Instance.
     * @param concept = A Concept
     * @return List = List containing the Attributes of a case with their
     * values.
     */
    public Hashtable<String, String> getAttributes(Pair<Instance, Similarity> r, Concept concept) {

        Hashtable<String, String> table = new Hashtable<String, String>();
        ArrayList<String> cats = getCategories(r);
        // Add the similarity of the case
        table.put("Sim", String.valueOf(r.getSecond().getValue()));
        for (String cat : cats) {
            // Add the Attribute name and its value into the Hashtable
            table.put(cat, r.getFirst().getAttForDesc(concept.getAllAttributeDescs().get(cat)).getValueAsString());
        }
        return table;
    }

    /**
     * This Method generates an ArrayList, which contains all Categories of aa
     * Concept.
     *
     * @author weber,koehler,namuth
     * @param r = An Instance.
     * @return List = List containing the Attributes names.
     */
    public ArrayList<String> getCategories(Pair<Instance, Similarity> r) {

        ArrayList<String> cats = new ArrayList<String>();

        // Read all Attributes of a Concept
        Set<AttributeDesc> catlist = r.getFirst().getAttributes().keySet();

        for (AttributeDesc cat : catlist) {
            if (cat != null) {
                // Add the String literals for each Attribute into the ArrayList
                cats.add(cat.getName());
            }
        }
        return cats;
    }
}
