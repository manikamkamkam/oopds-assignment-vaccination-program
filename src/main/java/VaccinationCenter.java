import java.io.IOException;

public class VaccinationCenter {
    private String name;
    private int capacityPerHour;
    private int capacityPerDay;
    private int capacityTotal;

    VaccinationCenter() {
    }

    VaccinationCenter(String name, int capacityPerHour, int capacityPerDay, int capacityTotal) throws IOException {
        this.name = name;
        this.capacityPerHour = capacityPerHour;
        this.capacityPerDay = capacityPerDay;
        this.totalCapacity = capacityTotal;
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
    
    public int capacityTotal(){
        return capacityTotal;
    }
}
