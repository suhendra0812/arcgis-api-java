package com.jababeka.arcgis.features;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.jababeka.arcgis.gis.GIS;

public class FeatureLayer {
    public String url;
    private String token;

    public FeatureLayer(String url, GIS gis) throws Exception {
        this.url = url;
        this.token = gis.getToken();
    }

    public FeatureSet query(String where, String outFields, String orderByFields, int resultRecordCount)
            throws Exception {

        String requestParams = String.format(
                "where=%s&outFields=%s&orderByFields=%s&resultRecordCount=%s&token=%s&f=json",
                where,
                outFields, orderByFields, resultRecordCount, this.token);

        URI queryUrl = new URI(this.url + "/query?" + requestParams);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(queryUrl)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return FeatureSet.fromJson(response.body());
    }

    @Override
    public String toString() {
        return String.format("FeatureLayer(url=%s)", this.url);
    }
}