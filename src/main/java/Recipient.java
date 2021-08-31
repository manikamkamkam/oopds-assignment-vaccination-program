import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Recipient {
    final private transient String[] vaccinationStatusDescription = {"", "Pending", "1st Dose Appointment", "1st Dose Completed", "2nd Dose Appointment", "2nd Dose Completed"};

    private String name;
    private String phoneNumber;
    private int vaccinationStatus;
    private ArrayList<Dose> doses;

    public Recipient(String name) {
        this.name = name;
    }

    public Recipient(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vaccinationStatus = 1;
        this.doses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getVaccinationStatus() {
        return vaccinationStatus;
    }

    public ArrayList<Dose> getDoses() {
        return doses;
    }

    public void setVaccinationStatus() {
        this.vaccinationStatus++;
    }

    public void setAppointment(Dose dose) {
        if (this.doses == null) {
            this.doses = new ArrayList<>();
        }
        this.doses.add(dose);
    }

    public void register() throws IOException {
        Helper.appendJsonFile(this, "recipients.json");
        System.out.println("Register successfully");
    }

    public void signIn() throws IOException {
        new MinistryOfHealth().searchRecipient(name);
    }

    @Override
    public String toString() {
//        System.out.println(vaccinationStatus);
        String data = String.format("%-20s: %s\n%-20s: %s\n%-20s: %s\n", "Name", name, "Phone Number", phoneNumber, "Vaccination Status", vaccinationStatus);

        if (doses == null) return data;

        for (int i = 0; i < doses.size(); i++) {
            String date = doses.get(i).getAppointmentDT().toString();
//            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(doses.get(i).getAppointmentDT());
            data += String.format("%-20s: %s, %s\n", "Dose " + (i + 1), doses.get(i).getVaccinationCenter(), date);
        }

        return data;
    }
}