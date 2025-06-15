import java.time.LocalDate;

public class DiaryEntry {
    private LocalDate date;
    private String content;
    private String emotion; // 감정 상태 (행복, 슬픔, 화남, 보통 등)

    public DiaryEntry(LocalDate date, String content, String emotion) {
        this.date = date;
        this.content = content;
        this.emotion = emotion;
    }

    // Getter
    public LocalDate getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getEmotion() {
        return emotion;
    }

    @Override
    public String toString() {
        return String.format("[%s] 감정: %s\n%s", date, emotion, content);
    }
}
