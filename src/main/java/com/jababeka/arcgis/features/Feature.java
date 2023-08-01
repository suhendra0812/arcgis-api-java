package com.jababeka.arcgis.features;

import org.json.JSONObject;

public class Feature {
    public JSONObject attributes;
    public JSONObject geometry;

    public Feature(JSONObject attributes, JSONObject geometry) {
        this.attributes = attributes;
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        JSONObject feature = new JSONObject();
        feature.put("attributes", attributes);
        feature.put("geometry", geometry);

        return feature.toString();
    }
}
