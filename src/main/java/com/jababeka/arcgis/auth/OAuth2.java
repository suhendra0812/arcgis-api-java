package com.jababeka.arcgis.auth;

import org.json.JSONObject;

public class OAuth2 extends Auth {
    private String url;
    private String appId;

    public OAuth2(String portalUrl, String appId) {
        this.url = portalUrl + "/sharing/rest/oauth2/authorize";
        this.appId = appId;
    }

    public String getToken() throws Exception {
        String requestBody = String.format("client_id=%s&response_type=%s&redirect_url=%s&f=json",
                this.appId, "token", "http://localhost:3000");
        JSONObject response = Request.sendRequest(url, requestBody);
        return response.getString("token");
    }
}
