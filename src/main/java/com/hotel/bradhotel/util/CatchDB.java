//package com.hotel.bradhotel.util;
//
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.net.ssl.HttpsURLConnection;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
//public class CatchDB {
//
//    public static void main(String[] args) {
//        try {
//            URL url = new URL("https://media.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
//            HttpsURLConnection coon = (HttpsURLConnection) url.openConnection();
//
//            coon.connect();
//
//            StringBuffer sb = new StringBuffer();
//            BufferedReader reader =
//                    new BufferedReader(
//                            new InputStreamReader(coon.getInputStream()));
//            String line;
//            while ( ( line = reader.readLine()) != null){
//                sb.append(line);
//            }
//            reader.close();
//
//            parseJSON(sb.toString());
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
//
//    static void parseJSON(String json) {
//        try {
//            Properties prop = new Properties();
//            prop.put("user", "root");
//            prop.put("password", "root");
//
//            Connection coon =
//                    DriverManager.getConnection(
//                            "jdbc:mysql://localhost:3306/hotel?serverTimezone=Asia/Taipei&characterEncoding=utf-8", prop);
//
//            System.out.println("-----------------------------");
//
//            int ia = json.indexOf("{");
//            json = json.substring(ia);
//
//            JSONObject j = new JSONObject(json);
//            JSONObject obj = j.getJSONObject("XML_Head");
//            JSONObject info = obj.getJSONObject("Infos");
//            JSONArray jsonArray = info.getJSONArray("Info");
//
//            System.out.println("size"+info);
//
//
//            String sql ="INSERT INTO tour (tour_name,city,address,tel,image_url,introduce,created_date,last_modified_date)"
//                    + "VALUES (?,?,?,?,?,?,?,?)";
//
//            PreparedStatement pstm = coon.prepareStatement(sql);
//
//
//
//            System.out.println("-----------------------------");
//
//            Date now = new Date();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//            for (int i = 0; i < 100; i++){
//                JSONObject rw = jsonArray.getJSONObject(i);
//
//                pstm.setString(1, rw.getString("Name"));
//                pstm.setString(2, rw.getString("Region"));
//                pstm.setString(3, rw.getString("Add"));
//                pstm.setString(4,rw.getString("Tel"));
//                pstm.setString(5, rw.getString("Picture1"));
//                pstm.setString(6,rw.getString("Toldescribe"));
//                pstm.setString(7, f.format(now));
//                pstm.setString(8, f.format(now));
//
//                pstm.executeUpdate();
//            }
//            System.out.println("OK");
//            pstm.close();
//            coon.close();
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
//}
