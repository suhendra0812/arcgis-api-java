package com.jababeka.arcgis.auth;

public abstract class Auth {
    String url;

    public abstract String getToken() throws Exception;
}
