package com.jababeka.arcgis.features;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class FeatureSet {

    public ArrayList<Feature> features;

    public FeatureSet(ArrayList<Feature> features) {
        this.features = features;
    }

    public static FeatureSet fromJson(String json) throws ParseException {
        JSONObject object = new JSONObject(json);

        JSONArray jsonFeatures = (JSONArray) object.get("features");

        ArrayList<Feature> features = new ArrayList<Feature>();
        for (int i = 0; i < jsonFeatures.length(); i++) {
            JSONObject jsonFeature = (JSONObject) jsonFeatures.get(i);
            JSONObject jsonAttributes = (JSONObject) jsonFeature.get("attributes");
            JSONObject jsonGeometry = (JSONObject) jsonFeature.get("geometry");
            Feature feature = new Feature(jsonAttributes, jsonGeometry);
            features.add(i, feature);
        }

        return new FeatureSet(features);

    }

    @Override
    public String toString() {
        ArrayList<String> features = new ArrayList<String>();
        for (int i = 0; i < features.toArray().length; i++) {
            features.add(i, features.get(i).toString());
        }
        return features.toString();
    }
}
