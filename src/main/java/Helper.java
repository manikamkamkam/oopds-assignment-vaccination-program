import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Helper {
    public static JSONObject readJSONFile(String path) throws IOException, ParseException {
        FileReader fileReader = new FileReader(path);
        Object dataObj = new JSONParser().parse(fileReader);
        return (JSONObject) dataObj;
    }

    public static void writeJSONFile(JSONObject data, String path) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(path);
        writer.write(data.toJSONString());
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException, ParseException {

    }
}
