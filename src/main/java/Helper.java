import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Helper {
    static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(Locale.ENGLISH));
        }
    }

    static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    public static <T> ArrayList<T> deserializeToArrayList(String path, Class<T> className) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Reader reader = Files.newBufferedReader(Paths.get(path));
        return gsonBuilder.create().fromJson(reader, TypeToken.getParameterized(ArrayList.class, className).getType());
    }

    public static void appendJsonFile(Object newObject, String path) throws IOException {
        ArrayList<Object> data = deserializeToArrayList(path, Object.class);
        data.add(newObject);
        Writer writer = Files.newBufferedWriter(Paths.get(path));
        new Gson().toJson(data, writer);
        writer.close();
    }

    public static void writeJsonFile(ArrayList<Recipient> recipients, String path) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Writer writer = Files.newBufferedWriter(Paths.get(path));
        gsonBuilder.create().toJson(recipients, writer);
        writer.close();
    }
}
