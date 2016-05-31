package org.news.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by virgil on 2016/5/31.
 */
public class HttpUtils {
    private static final int TIMEOUT_IN_MILLIONS = 5000;

    public static String doGet(String urlString) throws Exception {
        if (urlString == "") {
            return null;
        }
        URL url;
        HttpURLConnection conn = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "keep-Alive");
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                out = new ByteArrayOutputStream();
                int len = 1;
                byte[] buf = new byte[128];
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                return out.toString();
            } else {
                throw new RuntimeException("responseCode is not 200");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {

            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {

            }
            conn.disconnect();
        }
        return null;
    }

    public static String doPost(String urlString, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String res = "";
        if (urlString == "") {
            return null;
        }
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            if (params != null && !params.trim().equals("")) {
                out = new PrintWriter(conn.getOutputStream());
                out.print(params);
                out.flush();
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static boolean IsNetAvaliable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
