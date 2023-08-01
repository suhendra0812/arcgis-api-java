package com.jababeka.arcgis.gis;

import com.jababeka.arcgis.auth.Auth;
import com.jababeka.arcgis.auth.BasicAuth;
import com.jababeka.arcgis.auth.OAuth2;

public class GIS {
    private String portalUrl;
    private String username;
    private String password;
    private String appId;
    private Auth auth;

    public GIS(String portalUrl, String username, String password) {
        this.portalUrl = portalUrl;
        this.username = username;
        this.password = password;

        if (this.portalUrl == null) {
            this.portalUrl = "https://www.arcgis.com";
        }
    }

    public GIS(String appId) {
        this.appId = appId;
    }

    public String getToken() throws Exception {
        if (this.appId != null) {
            this.auth = new OAuth2(this.portalUrl, this.appId);
        } else {
            this.auth = new BasicAuth(this.portalUrl, this.username, this.password);
        }

        return this.auth.getToken();
    }

    @Override
    public String toString() {
        return String.format("GIS @ %s", this.portalUrl);
    }

}
