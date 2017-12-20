package org.ea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public abstract class Endpoint {
    private int responseCode;
    private String errorMessage;

    public abstract Object handleGET(Object request);
    public abstract Object handlePOST(Object request);
    public abstract Object handlePATCH(Object request);
    public abstract Object handlePUT(Object request);
    public abstract Object handleDELETE(Object request);

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String handleRequest(String requestMethod, String requestBody) {
        Object reqObj = JSONValue.parse(requestBody);

        Object respObj = null;
        if(requestMethod.equals("GET")) {
            respObj = handleGET(reqObj);
        }
        if(requestMethod.equals("POST")) {
            respObj = handlePOST(reqObj);
        }
        if(requestMethod.equals("PUT")) {
            respObj = handlePUT(reqObj);
        }
        if(requestMethod.equals("PATCH")) {
            respObj = handlePATCH(reqObj);
        }
        if(requestMethod.equals("DELETE")) {
            respObj = handleDELETE(reqObj);
        }

        if(respObj == null) {
            return "";
        }
        if(respObj instanceof JSONObject) {
            return ((JSONObject)respObj).toString();
        }
        if(respObj instanceof JSONArray) {
            return ((JSONArray)respObj).toString();
        }
        return "";
    }
}
