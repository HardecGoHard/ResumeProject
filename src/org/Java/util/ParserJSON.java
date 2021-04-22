package org.Java.util;


import com.google.gson.*;
import org.Java.Model.Section;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

public class ParserJSON {
    private static final GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter());
    private static final Gson GSON = gsonBuilder.create();


    public static <T> T reader(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(Writer writer, T obj) {
        GSON.toJson(obj, writer);
    }

    public static <T> T reader(String reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> String write(Class<T> clazz, T obj) {
        return GSON.toJson(obj, clazz);
    }

    public static class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
        private static final String CLASSNAME = "CLASSNAME";
        private static final String INSTANCE = "INSTANCE";

        @Override
        public T deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();

            try {
                Class clazz = Class.forName(className);
                return context.deserialize(jsonObject.get(INSTANCE), clazz);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e.getMessage());
            }
        }


        @Override
        public JsonElement serialize(T section, Type type, JsonSerializationContext context) {
            JsonObject retValue = new JsonObject();
            retValue.addProperty(CLASSNAME, section.getClass().getName());
            JsonElement elem = context.serialize(section);
            retValue.add(INSTANCE, elem);
            return retValue;
        }
    }
}
