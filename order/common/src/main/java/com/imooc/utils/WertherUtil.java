package com.imooc.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉活动\渠道接口
 *
 * @author fengyamin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class WertherUtil {
    private Logger logger = LoggerFactory.getLogger(WertherUtil.class);


    /**
     * 根据经纬度获取当前天气
     * @param log
     * @param lat
     * @return
     */
    public String getNowWeather(String log, String lat) {
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            logger.error("SaicExecuteBatchActivityServiceImpl getNowWeather() 根据经纬度获得城市失败 纬度：" + lat + " 经度 " + log + "详细信息：" + e);
        }
        JsonElement jsonObject1 = new JsonParser().parse(res);
        JsonElement jsonObject2 = jsonObject1.getAsJsonObject().get("addrList");
        JsonArray jsonArray1 = new JsonParser().parse(jsonObject2.toString()).getAsJsonArray();
        JsonElement jsonObject3 = new JsonParser().parse(jsonArray1.get(0).toString());
        String allAdd = jsonObject3.getAsJsonObject().get("admName").toString();
        String arr[] = allAdd.split(",");
        //System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);
        String city = arr[2];
        String baiduUrl = null;
        StringBuffer strBuf;
        try {
            //百度ak申请地址：http://lbsyun.baidu.com/apiconsole/key  目前用的上汽的key
            //要访问的地址URL，通过URLEncoder.encode()函数对于中文进行转码
            baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location=" + URLEncoder.encode(city, "utf-8") + "&output=json&ak=CIwgoj3Fn8B32Gd6lPsDIbEKLKrHEvwb";
        } catch (UnsupportedEncodingException e1) {
            logger.error("SaicExecuteBatchActivityServiceImpl getNowWeather() URLEncoderError 城市：" + city + e1);
        }
        strBuf = new StringBuffer();
        try {
            URL url = new URL(baiduUrl);
            URLConnection conn = url.openConnection();
            //转码
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line + " ");
            }
            reader.close();
        } catch (Exception e) {
            logger.error("SaicExecuteBatchActivityServiceImpl getNowWeather() getWeather url:" + baiduUrl + "异常信息：" + e);
        }
        String weather;
        JsonElement jsonObject4 = new JsonParser().parse(strBuf.toString());
        JsonElement jsonObject5 = jsonObject4.getAsJsonObject().get("results");
        JsonArray jsonArray2 = new JsonParser().parse(jsonObject5.toString()).getAsJsonArray();
        JsonElement jsonObject6 = new JsonParser().parse(jsonArray2.get(0).toString());
        JsonElement jsonObject7 = jsonObject6.getAsJsonObject().get("weather_data");
        JsonArray jsonArray3 = new JsonParser().parse(jsonObject7.toString()).getAsJsonArray();
        JsonElement jsonObject8 = new JsonParser().parse(jsonArray3.get(0).toString());
        weather = jsonObject8.getAsJsonObject().get("weather").toString();
        return weather;
    }


    /**
     * 根据经纬度获取历史天气
     * @param log
     * @param lat
     * @param date
     * @return
     * @throws Exception
     */
    public static String getWeatherHistory(String log, String lat, String date) throws Exception {
        String weather = "";
        String data = getCity(log, lat);
        Document document = getDocument("https://www.tianqi.com/" + data + "/" + date + ".html");
        Elements elements = document.getElementsByClass("cDRed");
        if (elements != null && elements.size() > 0) {
            weather = elements.get(0).text();
        }
        return weather;
    }

    public static String getCity(String log, String lat) throws Exception {
        // lat 小 log 大
        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
        String res = "";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            res += line + "\n";
        }
        in.close();
        // 根据经纬度获取到城市的信息res
        JsonElement jsonObject1 = new JsonParser().parse(res);
        JsonElement jsonObject2 = jsonObject1.getAsJsonObject().get("addrList");
        JsonArray jsonArray1 = new JsonParser().parse(jsonObject2.toString()).getAsJsonArray();
        JsonElement jsonObject3 = new JsonParser().parse(jsonArray1.get(0).toString());
        String allAdd = jsonObject3.getAsJsonObject().get("admName").toString();
        String arr[] = allAdd.split(",");
        // System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);
        String city = arr[1];
        String resultCity = getPingYin(city);
        return resultCity;
    }

    /**
     * @param url
     * @return
     * @introduction: 通过Jsoup直接获取页面
     */
    public static Document getDocumentWithJsoup(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(35000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * @param urlStr
     * @return
     * @introduction: 获取页面
     */
    public static Document getDocument(String urlStr) throws Exception {
        HttpURLConnection connection = null;
        URL url = new URL(urlStr);
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(7000);
        connection.setReadTimeout(7000);
        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
            return null;
        }
        Document document = deCodingConnection(connection);
        connection.disconnect();
        return document;
    }

    /**
     * @param connection
     * @return
     * @introduction: 正确解码获取页面
     */
    private static Document deCodingConnection(HttpURLConnection connection) throws Exception {
        connection.connect();
        // 避免乱码的处理
        String charset = connection.getHeaderField("Content-Type");
        charset = detectCharset(charset);
        InputStream input = getInputStream(connection);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int count;
        byte[] buffers = new byte[4096];
        while ((count = input.read(buffers, 0, buffers.length)) > 0) {
            output.write(buffers, 0, count);
        }
        input.close();
        // 若已通过请求头得到charset，则不需要去html里面继续查找
        if (charset == null || "".equals(charset)) {
            charset = detectCharset(output.toString());
            // 若在html里面还是未找到charset，则设置默认编码为utf-8
            if (charset == null || "".equals(charset)) {
                charset = "utf-8";
            }
        }
        String result = output.toString(charset);
        output.close();
        return Jsoup.parse(result);
    }

    private static String detectCharset(String input) throws Exception {
        Pattern pattern = Pattern.compile("charset=\"?([\\w\\d-]+)\"?;?", Pattern.CASE_INSENSITIVE);
        if (input != null && !"".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    private static InputStream getInputStream(HttpURLConnection conn) throws Exception {
        String contentEncoding = conn.getHeaderField("Content-Encoding");
        if (contentEncoding != null) {
            contentEncoding = contentEncoding.toLowerCase();
            if (contentEncoding.indexOf("gzip") != -1) {
                return new GZIPInputStream(conn.getInputStream());
            } else if (contentEncoding.indexOf("deflate") != -1) {
                return new DeflaterInputStream(conn.getInputStream());
            }
        }
        return conn.getInputStream();
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变 上海市==shanghai
     *
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString) throws Exception {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();
        String output = "";
        for (int i = 0; i < input.length - 1; i++) {
            if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                output += temp[0];
            } else
                output += Character.toString(input[i]);
        }
        return output;
    }
}
