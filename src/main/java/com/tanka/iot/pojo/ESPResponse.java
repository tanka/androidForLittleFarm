package com.tanka.iot.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

// {"air_temp":2"air_hum":2"soil_mois":2, "soil_N":2"soil_K":2"soil_P":2}
public class ESPResponse {
    @SerializedName("air_temp")
    int air_temp = 0;

    @SerializedName("air_hum")
    int air_hum = 0;

    @SerializedName("soil_mois")
    int soil_mois = 0;

    @SerializedName("soil_N")
    int soil_N = 0;

    @SerializedName("soil_K")
    int soil_K = 0;

    @SerializedName("soil_P")
    int soil_P = 0;


    public ESPResponse() {
    }

    public int getAir_temp() {
        return air_temp;
    }

    public void setAir_temp(int air_temp) {
        this.air_temp = air_temp;
    }

    public int getAir_hum() {
        return air_hum;
    }

    public void setAir_hum(int air_hum) {
        this.air_hum = air_hum;
    }

    public int getSoil_mois() {
        return soil_mois;
    }

    public void setSoil_mois(int soil_mois) {
        this.soil_mois = soil_mois;
    }

    public int getSoil_N() {
        return soil_N;
    }

    public void setSoil_N(int soil_N) {
        this.soil_N = soil_N;
    }

    public int getSoil_K() {
        return soil_K;
    }

    public void setSoil_K(int soil_K) {
        this.soil_K = soil_K;
    }

    public int getSoil_P() {
        return soil_P;
    }

    public void setSoil_P(int soil_P) {
        this.soil_P = soil_P;
    }
}
