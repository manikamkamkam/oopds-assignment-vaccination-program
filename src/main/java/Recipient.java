import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Recipient {
    private String name;
    private String phoneNumber;
    private long vaccinationStatus;

    public Recipient(String name, String phoneNumber, long status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vaccinationStatus = status;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void register() throws IOException, ParseException {
        JSONObject recipientsJObj = Helper.readJSONFile("recipients.json");
        JSONArray recipientJArray = (JSONArray) recipientsJObj.get("recipients");
        JSONObject recipentJOBj = new JSONObject();
        recipentJOBj.put("name", name);
        recipentJOBj.put("phoneNumber", phoneNumber);
        recipentJOBj.put("vaccinationStatus", vaccinationStatus);
        recipientJArray.add(recipentJOBj);
        recipentJOBj.put("recipients", recipientJArray);
//        Helper.writeJSONFile(recipentJOBj, "recipients.json");
//        System.out.println("Register successfully");

        System.out.println(recipentJOBj);
    }
}
