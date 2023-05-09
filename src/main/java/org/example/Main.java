package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.StatusLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet("https://repo.maven.apache.org/maven2/org/apache/httpcomponents/client5/httpclient5/5.2.1/httpclient5-5.2.1.jar");

            System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getUri());

            final String result = httpclient.execute(httpget, response -> {
                System.out.println("------------- HEADERS --------------");
                for (Header header : response.getHeaders()) {
                    System.out.println(header.getName()+": "+header.getValue());
                }
                System.out.println("------------- END --------------");
                System.out.println(httpget + "->" + new StatusLine(response));

                return response.getReasonPhrase();
                // Process response message and convert it into a value object
//                return new Result(response.getCode(), EntityUtils.toString(response.getEntity()));
            });
            System.out.println(result);

            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println("My IP: "+ ip);
        }
    }
}