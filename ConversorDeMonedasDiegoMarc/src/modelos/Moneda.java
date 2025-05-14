package modelos;

import com.google.gson.annotations.SerializedName;

public class Moneda {
    @SerializedName("conversion_rate")
    private double tasaConversion;

    public double getTasaConversion() {
        return tasaConversion;
    }
}