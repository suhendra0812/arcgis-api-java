package com.jababeka.arcgis;

import com.jababeka.arcgis.features.FeatureLayer;
import com.jababeka.arcgis.features.FeatureSet;
import com.jababeka.arcgis.gis.GIS;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * App
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String portalUrl = "https://tmaps.jababeka.com/arcgis";
        String username = dotenv.get("ARCGIS_USERNAME");
        String password = dotenv.get("ARCGIS_PASSWORD");

        String serviceUrl = "https://tmaps.jababeka.com/arcgis/rest/services/Hosted/JSmart_Report/FeatureServer/0";

        GIS gis = new GIS(portalUrl, username, password);

        FeatureLayer layer = new FeatureLayer(serviceUrl, gis);
        FeatureSet fset = layer.query("1=1", "*", "waktu_akhir+DESC", 1);

        System.out.println(fset.features.get(0));
    }
}
