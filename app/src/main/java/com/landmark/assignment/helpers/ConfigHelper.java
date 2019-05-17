package com.landmark.assignment.helpers;

import android.content.Context;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.landmark.assignment.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ConfigHelper {

    private static JSONObject config = null;
    private static final String CONFIG_FILE_NAME = "config.json";

    public static JSONObject getConfig() {
        return config;
    }

    public static JSONObject getProductConfig() throws JSONException {
        return config.getJSONObject("products");
    }

    private static void setConfig(String json) throws JSONException {
        config = new JSONObject(json);
    }

    public static void downloadConfig(Context context, Messenger messenger) {
        System.out.println("downloading config");
        URL url = null;
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            url = new URL(Constants.SERVER_URL);
            URLConnection conection = url.openConnection();
            conection.connect();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = null;
            output = new FileOutputStream(context.getFilesDir() + "/" + CONFIG_FILE_NAME);

            byte data[] = new byte[1024];

            long total = 0;
            int count = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);

            }
            output.flush();
            output.close();
            input.close();
            try {
                updateLocalConfig(context, messenger);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }

    public static void updateLocalConfig(Context context, Messenger messenger) throws
            IOException, JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = new FileInputStream(context.getFilesDir() + "/" + CONFIG_FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        String config = stringBuilder.toString();
        setConfig(config);

        Message msg = Message.obtain();
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            Log.i("error", "error");
        }
    }

}
