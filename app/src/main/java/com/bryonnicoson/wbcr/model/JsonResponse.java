package com.bryonnicoson.wbcr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bryon on 8/9/16.
 */

public class JsonResponse {

    //@JsonProperty("petfinder")
    @SerializedName("petfinder")
    public Petfinder petfinder;
    public JsonResponse(){}
}
