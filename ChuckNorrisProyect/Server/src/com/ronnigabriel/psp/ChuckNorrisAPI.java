package com.ronnigabriel.psp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;


public class ChuckNorrisAPI {

    public static String  random() throws IOException {
        URL url = new URL("https://api.chucknorris.io/jokes/random");
        URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", "com.juanagui.psp");
        try (InputStream urlStream = hc.getInputStream()) {
            try (InputStreamReader streamReader = new InputStreamReader(urlStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
                    String jokeJson = bufferedReader.readLine();
                    JSONObject jsonObject = new JSONObject(jokeJson); // Le pasamos la cadena que nos devuelve la api
                   return jsonObject.getString("value");
                }
            }
        }
    }

    public String jokeFor(String query) throws IOException {
        if(query.length()<4)
            return "Query must be longer than 3";
        query = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        URL url = new URL(String.format("https://api.chucknorris.io/jokes/search?query=",query));
        URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", "com.juanagui.psp");
        try (InputStream urlStream = hc.getInputStream()) {
            try (InputStreamReader streamReader = new InputStreamReader(urlStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
                    String jokeJson = bufferedReader.readLine();
                    JSONObject jsonObject = new JSONObject(jokeJson); // Le pasamos la cadena que nos devuelve la api
                    int total =  jsonObject.getInt("total");
                    if (total>=0) {
                        JSONArray result = jsonObject.getJSONArray("result");
                        int index = new Random().nextInt(total);
                        jsonObject = result.getJSONObject(index);
                       return  jsonObject.getString("value");
                    }else{
                        return String.format("no Joke found fod %s",query);
                    }
                }
            }
        }
    }
}
