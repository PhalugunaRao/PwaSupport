package com.example.pwatestdemo.utils;

import com.razorpay.PaymentData;

import org.json.JSONException;
import org.json.JSONObject;

public class JavaScriptCodeBuilder {
    // this file will communicate webview to java script and java script to webview

    public static JSONObject createJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        jsonDataObject.put("key1", "value1");
        jsonDataObject.put("key2", "value2");
        return jsonDataObject;
    }

    public static JSONObject callFitData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        jsonDataObject.put("action", "AppResumed");
        return jsonDataObject;
    }

    public static JSONObject createHealthConnectJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        jsonDataObject.put("action", "connectHealthConnectWearable");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }

    public static JSONObject disconnectHealthConnectJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        jsonDataObject.put("action", "disconnectHealthConnectWearable");
        return jsonDataObject;
    }

    public static JSONObject createGFConnectJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        jsonDataObject.put("action", "connectNativeWearable");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }
    public static JSONObject disconnectGFConnectJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        jsonDataObject.put("action", "disconnectNativeWearable");
        return jsonDataObject;
    }

    public static JSONObject MfaConnectedJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        jsonDataObject.put("action", "enableMFA");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }

    public static JSONObject permissionJsonData(String  permissionName,String permissionValue) throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        payloadObj.put(permissionName,permissionValue);
        jsonDataObject.put("action", "receivePermissionsData");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }

    public static JSONObject MfaDisableJsonData() throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        jsonDataObject.put("action", "disableMFA");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }
    public static JSONObject PaymentSucessJsonData( String razorpayPaymentID, PaymentData paymentData) throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        // Add more properties as needed
        payloadObj.put("razorpay_payment_id", razorpayPaymentID);
        payloadObj.put("razorpay_order_id", paymentData.getOrderId());
        payloadObj.put("razorpay_signature", paymentData.getSignature());
        jsonDataObject.put("action", "paymentSuccess");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }


    public static JSONObject PaymentFailedJsonData(int code) throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        payloadObj.put("code",code);
        jsonDataObject.put("action", "paymentFailure");
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }

    public static JSONObject LocationGrantJsonData(String locationStatus,Double lat,Double lon) throws JSONException {
        JSONObject jsonDataObject = new JSONObject();
        JSONObject payloadObj = new JSONObject();
        JSONObject coords = new JSONObject();
        coords.put("latitude",lat);
        coords.put("longitude",lon);
        payloadObj.put("status",locationStatus);
        jsonDataObject.put("action", "receiveLocationData");
        if(locationStatus.equalsIgnoreCase(PwaKeys.GRANTED)) {
            payloadObj.put("coords", coords);
        }
        jsonDataObject.put("payload", payloadObj);
        return jsonDataObject;
    }



    public static String buildCode(String data) {
        String javascriptCode = "handleStepsSync('" + data + "');";
        return javascriptCode;
    }

    public static String buildCode2(String createJsonData) {
        String javascriptCode = "handleNativeMessage('" + createJsonData + "');";
        return javascriptCode;
    }
}

