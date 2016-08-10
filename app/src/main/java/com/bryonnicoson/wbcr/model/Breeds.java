package com.bryonnicoson.wbcr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by bryon on 8/9/16.
 */
public class Breeds {
    //@JsonProperty("breed")
    @SerializedName("breed")
    //public List<String> breed = new ArrayList<String>();
    public Object breed;
    public Breeds(){}

    // need conditional handling of breed - sometimes string, sometimes array
}
