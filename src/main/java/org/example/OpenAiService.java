package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenAiService {
    private static final String API_KEY = "KEY"; // hier key eingeben!!!
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String makeCall(String pageContent) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        // Prompt erstellen
        String prompt = pageContent;

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        // Request Body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Map[]{message});
        requestBody.put("max_tokens", 100);

        RequestBody body = RequestBody.create(
                mapper.writeValueAsString(requestBody),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        if (response.body() != null) {
            String responseBody = response.body().string();
            System.out.println("Response: " + responseBody);

            // Angenommen, responseBody enthält das JSON von OpenAI
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode root = jsonMapper.readTree(responseBody);

            // Zugriff auf die Antwort
            String gptAntwort = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            return gptAntwort;

        }

        return null;


    }
}