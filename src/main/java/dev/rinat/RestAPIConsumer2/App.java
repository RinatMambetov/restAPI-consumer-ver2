package dev.rinat.RestAPIConsumer2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

//https://rapidapi.com
//https://github.com/15Dkatz/official_joke_api?tab=readme-ov-file

public class App {
    public static void main(String[] args) throws JsonProcessingException {

        String apiKey = "04ca15f80emsh3ff68c2a20dc6f1p159d11jsn24fcafd49585";
        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner = new Scanner(System.in);

//         get weather response
        System.out.println("Enter city for get temperature:");
        String city = scanner.nextLine();

        String weatherUrl = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + city + "&days=1";
        HttpHeaders weatherHttpHeaders = new HttpHeaders();
        weatherHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
        weatherHttpHeaders.set("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");
        weatherHttpHeaders.set("X-RapidAPI-Key", apiKey);

        HttpEntity<String> weatherEntity = new HttpEntity<>(weatherHttpHeaders);
        String weatherResponse =
                restTemplate.exchange(weatherUrl, HttpMethod.GET, weatherEntity, String.class).getBody();
//        System.out.println(response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(weatherResponse);
        System.out.println(jsonNode.get("current").get("temp_c"));

        System.out.println();

//        get joke response using Jackson
        System.out.println("Random joke about programming:");
        String jokeUrl = "https://official-joke-api.appspot.com/jokes/programming/random";

        Joke[] response = restTemplate.getForObject(jokeUrl, Joke[].class);
        if (response != null) {
            Joke joke = response[0];
            System.out.println(joke.getSetup());
            System.out.println(joke.getPunchline());
        }
    }
}
