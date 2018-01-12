package com.gg.matei.avnutrition;

/**
 * Created by matei.oltean on 12/01/2018.
 */

public class Alimente {

    String alimentId;
    String alimentNume;
    String alimentFel;

    public Alimente(){

    }

    public Alimente(String alimentId, String alimentNume, String alimentFel){
        this.alimentId = alimentId;
        this.alimentNume = alimentNume;
        this.alimentFel = alimentFel;

    }

    public String getAlimentId() {
        return alimentId;
    }

    public String getAlimentNume() {
        return alimentNume;
    }

    public String getAlimentFel() {
        return alimentFel;
    }
}
