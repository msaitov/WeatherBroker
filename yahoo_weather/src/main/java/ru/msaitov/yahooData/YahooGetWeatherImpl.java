package ru.msaitov.yahooData;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.yahooClasses.WeatherData;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class YahooGetWeatherImpl implements YahooGetWeather {

    public YahooGetWeatherImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDataTransfer getCity(String cityJSP) throws IOException {
        final String appId = "6pY4TR7i";
        final String consumerKey = "dj0yJmk9dWs0d1F6UVR3dXJZJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTcz";
        final String consumerSecret = "848868ef0262739c6c3bc147936ba8aa94fab604";
        String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        parameters.add("location=" + URLEncoder.encode(cityJSP, "UTF-8"));
        parameters.add("format=json");
        parameters.add("u=c");

        Collections.sort(parameters);
        //-----

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString = "GET&" +
                URLEncoder.encode(url, "UTF-8") + "&" +
                URLEncoder.encode(parametersList.toString(), "UTF-8");

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";

        HttpClient client = HttpClientBuilder.create().build();
        url = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location=" + cityJSP + "&format=json&u=c";
        HttpGet request = new HttpGet(url);
        request.addHeader("Authorization", authorizationLine);
        request.addHeader("Yahoo-App-Id", appId);
        request.addHeader("Content-Type", "application/json");


        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        WeatherData weatherData = mapper.readValue(entity.getContent(), WeatherData.class);

        WeatherDataTransfer weatherDataTransfer = new WeatherDataTransfer();
        weatherDataTransfer.setCity(weatherData.location.city.trim());
        weatherDataTransfer.setTemperature(weatherData.currentObservation.condition.temperature);

        return weatherDataTransfer;

    }
}