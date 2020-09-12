package com.example.luckytripassignment.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

//Json parser into model class
public class Util {

    public static <CLS> CLS objectFromJSON(String jsonData, Class<CLS> cls) throws Exception {
        if (jsonData == null)
            throw new Exception("No Data");
        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        return gson.fromJson(jsonData, cls);
    }

}

class JsonDateDeserializer implements JsonDeserializer<Date> {
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String s = json.getAsJsonPrimitive().getAsString();
        long l = Long.parseLong(s.substring(6, s.length() - 2));
        return new Date(l);
    }
}