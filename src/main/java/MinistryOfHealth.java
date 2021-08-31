import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class MinistryOfHealth {
    private ArrayList<Recipient> recipients;

    MinistryOfHealth() throws IOException, ParseException {
        loadRecipientData();
    }

    public void loadRecipientData() throws IOException, ParseException {
        recipients = new ArrayList<>();
        JSONObject recipientsJObj = Helper.readJSONFile("recipients.json");
        JSONArray recipientJArray = (JSONArray) recipientsJObj.get("recipients");
        for (Object recipientObj : recipientJArray) {
            JSONObject recipientJObj = (JSONObject) recipientObj;
            recipients.add(new Recipient((String) recipientJObj.get("name"), (String) recipientJObj.get("phoneNumber"), (long) recipientJObj.get("vaccinationStatus")));
        }
        System.out.println(recipients);
    }
}
