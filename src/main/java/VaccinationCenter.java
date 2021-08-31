import org.json.simple.parser.ParseException;

import java.io.IOException;

public class VaccinationCenter extends MinistryOfHealth {
    private String name;
    private long capacityPerHour;
    private long capacityPerDay;

    VaccinationCenter(String name, long capacityPerHour, long capacityPerDay) throws IOException, ParseException {
        this.name = name;
        this.capacityPerHour = capacityPerHour;
        this.capacityPerDay = capacityPerDay;
    }

    public String getName() {
        return name;
    }

    public long getCapacityPerHour() {
        return capacityPerHour;
    }

    public long getCapacityPerDay() {
        return capacityPerDay;
    }
}
