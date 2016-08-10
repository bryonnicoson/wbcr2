package com.bryonnicoson.wbcr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bryon on 8/9/16.
 */
public class Petfinder {

    //@JsonProperty("xmlns:xsi")
    @SerializedName("xmlns:xsi")
    private String xmlnsXsi;

    //@JsonProperty("xsi:noNamespaceSchemaLocation")
    @SerializedName("xsi:noNamespaceSchemaLocation")
    private String xsiNoNamespaceSchemaLocation;

    private Header header;

    private String lastOffset;

    public Pets pets;

    public Petfinder() {}
}
