package com.imooc.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class YunYinUtil {

    private static final String serverURL = "http://vop.baidu.com/server_api";
    private static String token = "";
    private static String testFileName = ""; // 百度语音提供技术支持
    //put your own params here
    // 下面3个值要填写自己申请的app对应的值
    private static final String apiKey = "1WYFjEGdOe0SG7RTOtg2v0cZ";
    private static final String secretKey = "u2lsyujzXm9m29nNvsIcp8wTOasl5emI";
    private static final String cuid = "15345241";

    public static String getYuYinString(String path) throws Exception {
        testFileName = path;
        getToken();
        String response = method();
        JsonElement jsonObject8 = new JsonParser().parse(response);
        String str = jsonObject8.getAsJsonObject().get("result").toString();
        return str;
    }

    private static void getToken() throws Exception {
        String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" +
                "&client_id=" + apiKey + "&client_secret=" + secretKey;
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        token = new JSONObject(printResponse(conn)).getString("access_token");
    }


    private static String method() throws Exception {
        File pcmFile = new File(testFileName);
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
                + "?cuid=" + cuid + "&token=" + token).openConnection();

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "audio/pcm; rate=8000");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(loadFile(pcmFile));
        wr.flush();
        wr.close();
        //return getUtf8String(printResponse(conn));
        return printResponse(conn);

    }

    private static String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            // request error
            System.out.println("conn.getResponseCode() = " + conn.getResponseCode());
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    // GBK编码转为UTF-8
    private static String getUtf8String(String s) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        String xmlString = "";
        String xmlUtf8 = "";
        xmlString = new String(sb.toString().getBytes("GBK"));
        xmlUtf8 = URLEncoder.encode(xmlString, "GBK");
        return URLDecoder.decode(xmlUtf8, "UTF-8");
    }
}
