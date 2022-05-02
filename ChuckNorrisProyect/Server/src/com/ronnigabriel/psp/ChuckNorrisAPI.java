package com.ronnigabriel.psp;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class ChuckNorrisAPI {

    public URL url;
    public String randomJoke;
    public String queryJoke;


    public String  random() throws IOException {

        URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", " com.juanagui.psp");

        try (InputStream urlStream = url.openStream()) {
            try (InputStreamReader streamReader = new InputStreamReader(urlStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
                    hc.getInputStream();
                    try (FileWriter fileWriter = new FileWriter(hc.toString(), false)) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                bufferedWriter.write(line);
                            }
                        }
                    }
                }
            }
        }

        return randomJoke;

    }

    public String jokeFor(String query) throws IOException {
        URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", " com.juanagui.psp");

        /*
        *
        * Aqui debemos codificar la ruta para cuando introduzcamos el comando query y se peuda llegvar a cabo la ruta
        *
        * */

        try (InputStream urlStream = url.openStream()) {
            try (InputStreamReader streamReader = new InputStreamReader(urlStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
                    hc.getInputStream( /* Ruta de la query */ );
                    try (FileWriter fileWriter = new FileWriter(hc.toString(), false)) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                bufferedWriter.write(line);
                            }
                        }
                    }
                }
            }
        }


        return queryJoke;
    }


}
