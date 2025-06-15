import java.time.LocalDate;
import java.util.ArrayList;

public class DiaryManager {
    private static DiaryManager instance = null;
    private ArrayList<DiaryEntry> entries;

    private DiaryManager() {
        entries = new ArrayList<>();
    }

    public static DiaryManager getInstance() {
        if (instance == null) {
            instance = new DiaryManager();
        }
        return instance;
    }

    public void addEntry(LocalDate date, String content, String emotion) {
        // 같은 날짜에 일기가 있으면 덮어쓰기
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getDate().equals(date)) {
                entries.remove(i);
                break;
            }
        }
        entries.add(new DiaryEntry(date, content, emotion));
    }

    public DiaryEntry getEntryByDate(LocalDate date) {
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                return entry;
            }
        }
        return null;
    }
}
