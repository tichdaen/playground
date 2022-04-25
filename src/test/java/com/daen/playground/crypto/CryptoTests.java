package com.daen.playground.crypto;

import com.google.gson.*;
import com.google.gson.stream.MalformedJsonException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.GsonTester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonTest
@Slf4j
public class CryptoTests {

    private GsonTester<String> json;

    @BeforeEach
    void beforeAllTest() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GsonTester.initFields(this, gson);
    }

    @Test
    void firstTest() throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("a", "1");
        jsonObject.addProperty("b", "2");

        log.info("JsonObject: {}", jsonObject);

        String tostring = jsonObject.toString();

        Assertions.assertThat(tostring).contains("a");
    }

    @Test
    void secondTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("a", "thisIsA");
        jsonObject.addProperty("b", "thisIsB");
        jsonObject.addProperty("c", "thisIsC");

        JsonObject jsonNull = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);

        log.info("get JsonElement : {}", jsonObject.get("a"));
        // log.info("get As Json Object : {}", jsonObject.getAsJsonObject("b")); JsonPrimitive ("String 인듯") cannot be cast to class com.google.gson.JsonObject
        log.info("get As Json Object without args : {}", jsonObject.getAsJsonObject());
        // log.info("get As Json Array : {}", jsonObject.getAsJsonArray("c")); JsonPrimitive (`String 즉 "ThisIsC" 인듯 보임`) cannot be cast to class com.google.gson.JsonArray
        // log.info("get As Json Array without args : {}", jsonObject.getAsJsonArray()); Not a Json Array
        log.info("is JsonObject? : {}", jsonObject.isJsonObject());
        log.info("is JsonArray? : {}", jsonObject.isJsonArray());
        log.info("is JsonArray! : {}", jsonArray.isJsonArray());
        log.info("is JsonNull? : {}", jsonObject.isJsonNull());
        log.info("is JsonNull! : {}", jsonNull.isJsonNull());
        log.info("SIZE PROPERTY {}, {}", jsonObject.size(), jsonNull.size());
        log.info("is JsonPrimitive : {}", jsonObject.isJsonPrimitive());

        Gson gson = new Gson();
        Object x = gson.fromJson(jsonObject.toString(), Object.class);
        gson.toJsonTree(x);
        log.info("X : {}", x);
    }

    @Test
    void toJsonTreeTest() {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        String x = "{\"a\":\"1\", \"b\": \"2\"}";
        JsonElement element = gson.toJsonTree(map);
        log.info("ELEMENT {}", element);
        log.info("ELEMENT To Json Object{}", element.getAsJsonObject());

        log.info("isJsonNull : {}", element.isJsonNull());
        log.info("isPrimitive : {}", element.isJsonPrimitive());
        log.info("isArray : {}", element.isJsonArray());
        log.info("isObject : {}", element.isJsonObject());

        String empty = "";
        JsonElement emptyElement = gson.toJsonTree(empty);
        log.info("emptyElement : {}", emptyElement);
        log.info("isJsonNull : {}", emptyElement.isJsonNull());
        log.info("isJsonObject : {}", emptyElement.isJsonObject());

        JsonElement fromParser = null;
        try {
            fromParser = JsonParser.parseString("");
        } catch (JsonSyntaxException e) {
            log.error(e.getMessage());
            fromParser = JsonParser.parseString("{}");
        }
        log.info("fromParser : {}", fromParser);
        log.info("isJsonObject : {}", fromParser.isJsonObject());
        log.info("isJsonNull : {}", fromParser.isJsonNull());
        log.info("isPrimitive : {}", fromParser.isJsonPrimitive());
        log.info("isArray : {}", fromParser.isJsonArray());
    }
}
