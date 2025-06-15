import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileUtil {
    private static final String USER_FILE = "users.dat";
    private static final String ACCOUNT_FILE = "accounts.dat";
    private static final String SCHEDULE_FILE = "schedules.dat";
    private static final String DIARY_FILE = "diaries.dat";

    // 데이터 저장 메소드들
    public static void saveUsers(ArrayList<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("사용자 데이터 저장 실패: " + e.getMessage());
        }
    }

    public static void saveAccountEntries(ArrayList<AccountBookEntry> entries) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
            for (AccountBookEntry entry : entries) {
                writer.println(entry.getDate() + "," +
                        entry.getCategory() + "," +
                        entry.getAmount() + "," +
                        entry.isIncome() + "," +
                        entry.getMemo());
            }
        } catch (IOException e) {
            System.out.println("가계부 데이터 저장 실패: " + e.getMessage());
        }
    }

    // 데이터 로드 메소드들 (예시)
    public static ArrayList<AccountBookEntry> loadAccountEntries() {
        ArrayList<AccountBookEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String category = parts[1];
                    int amount = Integer.parseInt(parts[2]);
                    boolean isIncome = Boolean.parseBoolean(parts[3]);
                    String memo = parts[4];
                    entries.add(new AccountBookEntry(date, category, amount, isIncome, memo));
                }
            }
        } catch (IOException e) {
            System.out.println("가계부 데이터 로드 실패: " + e.getMessage());
        }
        return entries;
    }
}
