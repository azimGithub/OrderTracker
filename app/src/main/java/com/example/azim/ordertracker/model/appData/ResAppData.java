
package com.example.azim.ordertracker.model.appData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResAppData {

    @SerializedName("amast")
    @Expose
    public List<Amast> amast = null;
    @SerializedName("areamas")
    @Expose
    public List<Areama> areamas = null;
    @SerializedName("brandmas")
    @Expose
    public List<Brandma> brandmas = null;
    @SerializedName("catmas")
    @Expose
    public List<Catma> catmas = null;
    @SerializedName("comas")
    @Expose
    public List<Coma> comas = null;
    @SerializedName("itmast")
    @Expose
    public List<Itmast> itmast = null;
    @SerializedName("prefitemlist")
    @Expose
    public List<Prefitemlist> prefitemlist = null;
    @SerializedName("salesman")
    @Expose
    public List<Salesman> salesman = null;
    @SerializedName("totalpage")
    @Expose
    public Integer totalpage;

}
