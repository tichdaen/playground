package com.daen.playground.service;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TestGenerator {

    public JSONObject testDataGenerator(int count) {
        JSONObject result = new JSONObject();
        JSONArray subResult = new JSONArray();

        for (int i = 0; i < count; i++) {
            JSONObject testData = createRandomData();
            subResult.put(testData);
        }

        try {
            result.put("data", subResult);
        } catch (JSONException e) {}

        return result;
    }

    public JSONObject testGeneratorWithTime(String code) {
        int randomList = (int) Math.floor(Math.random() * 10);
        Double randomTime = Math.random() * 10000;

        System.out.println(code + "는 " + Math.floor(randomTime / 1000) + "초 걸릴것이다.");
        try {
            Thread.sleep(randomTime.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject result = new JSONObject();
        JSONArray arr = new JSONArray();

        try {
            result.put("rsp_code", "00000");
            for ( int i = 0; i < randomList; i++ ) {
                arr.put(createRandomData());
            }
            result.put("orgCode", code);
            result.put("account_list", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private JSONObject createRandomData() {
        JSONObject test = new JSONObject();

        double randomNumber = Math.floor(Math.random() * 1000);

        try {
            test.put("rsp_code", "00000");
            test.put("random_number", randomNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(test);

        return test;
    }
}
