package com.jababeka.arcgis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jababeka.arcgis.features.FeatureLayer;
import com.jababeka.arcgis.features.FeatureSet;
import com.jababeka.arcgis.gis.GIS;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws Exception
     */
    Dotenv dotenv = Dotenv.load();
    String portalUrl = "https://tmaps.jababeka.com/arcgis";
    String username = dotenv.get("ARCGIS_USERNAME");
    String password = dotenv.get("ARCGIS_PASSWORD");
    String serviceUrl = "https://tmaps.jababeka.com/arcgis/rest/services/Hosted/JSmart_Report/FeatureServer/0";

    @Test
    public void basicAuth() throws Exception {
        GIS gis = new GIS(portalUrl, username, password);
        String token = gis.getToken();
        assertNotNull(token);
    }

    @Test
    public void getFeatures() throws Exception {
        GIS gis = new GIS(portalUrl, username, password);
        FeatureLayer layer = new FeatureLayer(serviceUrl, gis);
        FeatureSet fset = layer.query("1=1", "*", "waktu_akhir+DESC", 1);
        assertNotNull(fset.features);
    }

    @Test
    public void checkQueryResult() throws Exception {
        GIS gis = new GIS(portalUrl, username, password);
        FeatureLayer layer = new FeatureLayer(serviceUrl, gis);
        FeatureSet fset = layer.query("kategori='Distribusi+Air'", "*", "waktu_akhir+DESC", 1);
        String category = fset.features.get(0).attributes.getString("kategori");
        assertEquals(category, "Distribusi Air");
    }
}
