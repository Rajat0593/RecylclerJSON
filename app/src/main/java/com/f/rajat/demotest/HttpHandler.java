package com.f.rajat.demotest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler{

    HttpHandler(){

    }

    public String makeServiceCall(String reqUrl){

        String response = null;
        try {
            URL url = new URL(reqUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            InputStream is = new BufferedInputStream(connection.getInputStream());

            response = convertStreamToString(is);
        }
        catch (MalformedURLException | ProtocolException mu){
            mu.getMessage();
        } catch (IOException ie){
            ie.getMessage();
        }
        return response;
    }

    private String convertStreamToString(InputStream in) {
        //To read the inputStream
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        //To append the Data
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                //appending onto the Empty Line
                sb.append(line).append('\n');
            }
        }
        catch (IOException ie){
            ie.getMessage();
        }
        finally {
            try {
                //closing the Stream after read
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
