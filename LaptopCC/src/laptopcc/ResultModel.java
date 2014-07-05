/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptopcc;

import de.dfki.mycbr.core.similarity.AmalgamationFct;
import de.dfki.mycbr.core.similarity.ISimFct;
import de.dfki.mycbr.core.similarity.config.AmalgamationConfig;
import java.util.Observable;

/**
 *
 * @author danilo
 */
public class ResultModel extends Observable {

    private boolean isCustom;

    public boolean isIsCustom() {
        return isCustom;
    }

    public void setIsCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }
    
    LaptopRecommender recomender;

    public String bluetooth = "";
    public String brand = "";
    public String cpuBrand = "";
    public Float cpuSpeed = 0f;
    public String cpuType = "";
    public Integer cacheSize = 0;
    public String dvd = "";
    public String graphicCard = "";
    public Integer hdSize = 0;
    public String hdType = "";
    public Float lcdInches = 0f;
    public Integer laptopId = 0;
    public String model = "";
    public String os = "";
    public Float price = 0f;
    public Integer ramSize = 0;
    public String segment = "";
    public String webcam = "";
    public Float weight = 0f;
    public String wireless = "";
    public Integer numberOfCases = 0;

    public AmalgamationFct globalProfile;
    
    public ISimFct localCpuSpeed;
    public ISimFct localHdSize;
    public ISimFct localPrice;
    public ISimFct localRamSize;
    public ISimFct localWeight;
    

    public ResultModel(LaptopRecommender recomender) {
        this.recomender = recomender;
    }

    public void sendNotification() {
        setChanged();
        notifyObservers();
    }

    public LaptopRecommender getRecomender() {
        return recomender;
    }

    String getResult() {
        if (isCustom) {
            return recomender.solveQuery(
                    bluetooth,
                    brand,
                    cpuBrand,
                    cpuSpeed,
                    cpuType,
                    cacheSize,
                    dvd,
                    graphicCard,
                    hdSize,
                    hdType,
                    lcdInches,
                    laptopId,
                    model,
                    os,
                    price,
                    ramSize,
                    segment,
                    webcam,
                    weight,
                    wireless,
                    numberOfCases,
                    globalProfile);
        } else {
            return recomender.solveQuery(
                    bluetooth,
                    brand,
                    cpuBrand,
                    cpuSpeed,
                    cpuType,
                    cacheSize,
                    dvd,
                    graphicCard,
                    hdSize,
                    hdType,
                    lcdInches,
                    laptopId,
                    model,
                    os,
                    price,
                    ramSize,
                    segment,
                    webcam,
                    weight,
                    wireless,
                    numberOfCases,
                    new AmalgamationFct(AmalgamationConfig.EUCLIDEAN, recomender.myConcept, "Custom"));
            
        }
    }
}
