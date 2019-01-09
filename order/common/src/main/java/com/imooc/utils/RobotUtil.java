package com.imooc.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


//图灵智能机器人工具类
public class RobotUtil {

    private static String APIKEY = "9d87f4383b0f4394ad939822437379ff";//官网注册后，换成你自己的

    public static String call(String question) throws IOException {
        // 这是上传给云机器人的问题
        String INFO = URLEncoder.encode(question, "utf-8");
        String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY
                + "&info=" + INFO;
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        connection.connect();

        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        JsonElement jsonObject8 = new JsonParser().parse(sb.toString());
        String str = jsonObject8.getAsJsonObject().get("text").toString();
        return str;
    }
}
