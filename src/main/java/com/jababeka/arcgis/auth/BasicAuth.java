package com.jababeka.arcgis.auth;

import org.json.JSONObject;

public class BasicAuth extends Auth {
    private String url;
    private String username;
    private String password;

    public BasicAuth(String portalUrl, String username, String password) {
        this.url = portalUrl + "/sharing/rest/generateToken";
        this.username = username;
        this.password = password;
    }

    public String getToken() throws Exception {
        String requestBody = String.format("username=%s&password=%s&referer=%s&f=json",
                this.username, this.password, this.url);
        JSONObject response = Request.sendRequest(url, requestBody);
        return response.getString("token");
    }
}
