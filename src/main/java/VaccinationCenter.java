import java.io.IOException;

public class VaccinationCenter {
    private String name;
    private int capacityPerHour;
    private int capacityPerDay;

    VaccinationCenter() {
    }

    VaccinationCenter(String name, int capacityPerHour, int capacityPerDay) throws IOException {
        this.name = name;
        this.capacityPerHour = capacityPerHour;
        this.capacityPerDay = capacityPerDay;
    }

    public String getName() {
        return name;
    }

    public int getCapacityPerHour() {
        return capacityPerHour;
    }

    public int getCapacityPerDay() {
        return capacityPerDay;
    }
}
