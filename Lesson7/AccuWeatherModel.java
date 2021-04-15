package Lesson7;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AccuWeatherModel {

    private static final String PROTOKOL = "http";
    private static final String API_KEY = "72MRSvxERlKD73bGIjehNUHiAASg98q0";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String API_V1 = "v1";
    private static final String DAILY = "daily";
    private static final String LOCATIONS_ENDPOINT = "locations";
    private static final String FORECASTS = "forecasts";
    private static final String CITIES_ENDPOINT = "cities";
    private static final String AUTOCOMPLETE_ENDPOINT = "autocomplete";
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static String changeTown;

    public static String detectCityKey(String selectedCity) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS_ENDPOINT)
                .addPathSegment(API_V1)
                .addPathSegment(CITIES_ENDPOINT)
                .addPathSegments(AUTOCOMPLETE_ENDPOINT)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        changeTown = objectMapper.readTree(responseString).get(0).at("/LocalizedName").asText();
        return cityKey;
    }


    public static void getWeather(String selectedCity, int numberDay) throws IOException {
        String[] minimum = new String[numberDay];
        String[] maximum = new String[numberDay];
        String cityKey = detectCityKey(selectedCity);
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(API_V1)
                .addPathSegments(DAILY)
                .addPathSegments(numberDay + "day")
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();
        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        for (int i = 0; i < numberDay; i++) {

            try {
                minimum[i] = objectMapper.readTree(responseString)
                        .at("/DailyForecasts").get(i)
                        .at("/Temperature")
                        .at("/Minimum")
                        .at("/Value").asText();
                maximum[i] = objectMapper.readTree(responseString)
                        .at("/DailyForecasts").get(i)
                        .at("/Temperature")
                        .at("/Maximum")
                        .at("/Value").asText();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        OutputResult.outputResult(minimum, maximum, numberDay, changeTown);
    }
}


