import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleManager {
    private static ScheduleManager instance = null;
    private ArrayList<Schedule> schedules;

    private ScheduleManager() {
        schedules = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addSchedule(LocalDate date, LocalTime startTime, LocalTime endTime, String title, String description) {
        schedules.add(new Schedule(date, startTime, endTime, title, description));
    }

    public ArrayList<Schedule> getSchedulesByDate(LocalDate date) {
        ArrayList<Schedule> result = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (schedule.getDate().equals(date)) {
                result.add(schedule);
            }
        }
        return result;
    }
}
