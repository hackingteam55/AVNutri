package com.gg.matei.avnutrition;

/**
 * Created by matei.oltean on 12/01/2018.
 */

public class Alimente {

    String alimentId;
    String alimentId2;
    String alimentId4;
    String alimentId3;

    public Alimente(){

    }

    public Alimente(String alimentId, String alimentNume, String alimentFel, String alimentCant){
        this.alimentId = alimentId;
        this.alimentId2 = alimentNume;
        this.alimentId4 = alimentFel;
        this.alimentId3 = alimentCant;


    }

    public String getAlimentId() {
        return alimentId;
    }

    public String getAlimentId2() {
        return alimentId2;
    }

    public String getAlimentId3() {
        return alimentId3;
    }

    public String getAlimentId4() {return alimentId4; }
}
