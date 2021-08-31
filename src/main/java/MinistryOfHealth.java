
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MinistryOfHealth {
    private ArrayList<Recipient> recipients;
    private ArrayList<VaccinationCenter> vaccinationCenters;

    MinistryOfHealth() throws IOException {
        loadData();
    }

    private void loadData() throws IOException {
        recipients = Helper.deserializeToArrayList("recipients.json", Recipient.class);
        recipients.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        vaccinationCenters = Helper.deserializeToArrayList("vaccinationCenters.json", VaccinationCenter.class);
        vaccinationCenters.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    public void printRecipientData() {
        System.out.printf("%-20s | %-20s | %20s\n", "Name", "Phone Number", "Vaccination Status");
        System.out.println("------------------------------------------------------------------");
        for (Recipient recipient : recipients) {
            System.out.printf("%-20s | %-20s | %20d\n", recipient.getName(), recipient.getPhoneNumber(), recipient.getVaccinationStatus());
        }
    }
    
    public void printVaccinationCenterData(){
        System.out.printf("%-20s | %-20s | %20s\n", "Center Name", "Total Capacity", "Number of Recipients with Completed Doses", "Number of Pending Recipients", 
                          "Percentage of Completed Doses");
        System.out.println("------------------------------------------------------------------");
        for (VaccinationCenter vaccinationcenter : vaccinationcenter){
            System.out.println("%20s | %-20s | %20d\n", vaccinationcenter.getName(), vaccinationcenter.getcapacityTotal(), vaccinationcenter.getNumberofCompletedRecipients(),
                              vaccinationcenter.getPendingRecipients(), vaccinationcenter.getPercentageCompleted());
        }
    }

    public void searchRecipient(String name) {
        Recipient recipient = recipients.stream().filter(r -> name.equals(r.getName())).findAny().orElse(null);
        if (recipient == null) {
            System.out.println("Recipient " + name + " is not found");
        } else {
            System.out.println(recipient);
        }
    }

    private int checkVCRemainingCapacity(int capacity, LocalDateTime selectedDate) {
        for (Recipient recipient : recipients) {
            ArrayList<Dose> doses = recipient.getDoses();
            if (doses == null) continue;
            for (Dose dose : doses) {
                if (dose.getAppointmentDT().getYear() == selectedDate.getYear() &&
                        dose.getAppointmentDT().getMonth() == selectedDate.getMonth() &&
                        dose.getAppointmentDT().getDayOfMonth() == selectedDate.getDayOfMonth())
                    capacity--;
            }
        }
        return capacity;
    }

    public void distributeVaccines() {
        for (Recipient recipient : recipients) {
            LocalDateTime appointmentDate = LocalDateTime.now().plusDays(1);
            if (recipient.getVaccinationStatus() == 1 || recipient.getVaccinationStatus() == 3) {
                for (int j = 0; j < vaccinationCenters.size(); j++) {
                    VaccinationCenter vc = vaccinationCenters.get(j);
                    int remainingCapacity = checkVCRemainingCapacity(vc.getCapacityPerDay(), appointmentDate);
                    if (remainingCapacity == 0 && j == vaccinationCenters.size() - 1) {
                        appointmentDate = appointmentDate.plusDays(1);
                        j = 0;
                    }
                    if (remainingCapacity == 0) continue;
                    Dose newDose = new Dose(appointmentDate, vc.getName());
                    recipient.setAppointment(newDose);
                    recipient.setVaccinationStatus();
                    remainingCapacity--;
                    System.out.println(recipient);
                    break;
                }
            }
        }
    

       /* for (int i = 0; i < vaccinationCenters.size(); i++) {
            VaccinationCenter vc = vaccinationCenters.get(i);
            int remainingCapacity = checkVCRemainingCapacity(vc.getCapacityPerDay(), appointmentDate);
            System.out.println("Distributing vaccines for " + vc.getName() + " with capacity " + remainingCapacity);
            for (int j = 0; j < recipients.size(); j++) {
                Recipient recipient = recipients.get(j);
                if (recipient.getVaccinationStatus() == 1 || recipient.getVaccinationStatus() == 3) {
                    Dose newDose = new Dose(appointmentDate, vc.getName());
                    recipient.setAppointment(newDose);
                    recipient.setVaccinationStatus();
                    System.out.println(recipient);
                    remainingCapacity--;
                }
                if (remainingCapacity == 0) break;
            }
        }*/

        /*for (int i = 0; i < recipients.size(); i++) {
            Recipient recipient = recipients.get(i);
            if (recipient.getVaccinationStatus() == 1 || recipient.getVaccinationStatus() == 3) {
                Dose newDose = new Dose(appointmentDate, vc.getName());
                recipient.setAppointment(newDose);
                recipient.setVaccinationStatus();
                remainingCapacity--;
            }
            if (remainingCapacity == 0) break;
        }*/
        // Distribute vaccines start from date tomorrow
//        ArrayList<Recipient> recipientsGivenAppointment = new ArrayList<>();
        // Check capacity
    }

    public static void main(String[] args) throws IOException {
        MinistryOfHealth ministryOfHealth = new MinistryOfHealth();
        ministryOfHealth.distributeVaccines();
        System.out.println(ministryOfHealth.recipients);
        Helper.writeJsonFile(ministryOfHealth.recipients, "recipients.json");
    }
}
