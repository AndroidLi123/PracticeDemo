package com.gank.common;

import com.gank.data.StringObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import io.realm.RealmList;

/**
 * Created by LiXiaoWang
 */
public class TagRealmListConverter implements JsonSerializer<RealmList<StringObject>>,
        JsonDeserializer<RealmList<StringObject>> {

    @Override
    public JsonElement serialize(RealmList<StringObject> src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (StringObject tag : src) {
            ja.add(context.serialize(tag));
        }
        return ja;
    }

    @Override
    public RealmList<StringObject> deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context)
            throws JsonParseException {
        RealmList<StringObject> tags = new RealmList<>();
        JsonArray ja = json.getAsJsonArray();
        for (JsonElement je : ja) {
            tags.add((StringObject) context.deserialize(je,StringObject.class));
        }
        return tags;
    }

}
