import java.time.LocalDateTime;

public class Dose {
    private LocalDateTime appointmentDT;
    private String vaccinationCenter;

    public Dose(LocalDateTime appointmentDT, String vaccinationCenter) {
        this.appointmentDT = appointmentDT;
        this.vaccinationCenter = vaccinationCenter;
    }

    public LocalDateTime getAppointmentDT() {
        return appointmentDT;
    }

    public String getVaccinationCenter() {
        return vaccinationCenter;
    }
}
