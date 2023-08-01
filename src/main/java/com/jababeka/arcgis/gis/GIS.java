package com.jababeka.arcgis.gis;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class GIS {
    private String portalUrl;
    private String username;
    private String password;
    private String appId;

    public GIS(String portalUrl, String username, String password) {
        this.portalUrl = portalUrl;
        this.username = username;
        this.password = password;
    }

    public GIS(String appId) {
        this.appId = appId;
    }

    private JSONObject sendRequest(String url, String requestBody) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject object = new JSONObject(response.body());
        return object;
    }

    private JSONObject basicAuth() throws Exception {
        String url = this.portalUrl + "/sharing/rest/generateToken";
        String requestBody = String.format("username=%s&password=%s&referer=%s&f=json",
                this.username, this.password, this.portalUrl);
        JSONObject response = sendRequest(url, requestBody);
        return response;
    }

    private JSONObject OAuth() throws Exception {
        String url = this.portalUrl + "/sharing/rest/oauth2/authorize";
        String requestBody = String.format("client_id=%s&response_type=%s&redirect_url=%s&f=json",
                this.appId, "token", "http://localhost:3000");
        JSONObject response = sendRequest(url, requestBody);
        return response;
    }

    public String getToken() throws Exception {
        if (this.portalUrl == null) {
            this.portalUrl = "https://www.arcgis.com";
        }

        JSONObject response;

        if (this.appId != null) {
            response = OAuth();
        } else {
            response = basicAuth();
        }

        return response.getString("token");
    }

    @Override
    public String toString() {
        return String.format("GIS(portalUrl=%s, username=%s)", this.portalUrl, this.username);
    }

}
