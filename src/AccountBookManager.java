import java.time.LocalDate;
import java.util.ArrayList;

public class AccountBookManager {
    private static AccountBookManager instance = null;
    private ArrayList<AccountBookEntry> entries;

    private AccountBookManager() {
        entries = new ArrayList<>();
    }

    public static AccountBookManager getInstance() {
        if (instance == null) {
            instance = new AccountBookManager();
        }
        return instance;
    }

    public void addEntry(LocalDate date, String category, int amount, boolean isIncome, String memo) {
        entries.add(new AccountBookEntry(date, category, amount, isIncome, memo));
    }

    public ArrayList<AccountBookEntry> getEntriesByDate(LocalDate date) {
        ArrayList<AccountBookEntry> result = new ArrayList<>();
        for (AccountBookEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                result.add(entry);
            }
        }
        return result;
    }

    public int getDailyTotal(LocalDate date, boolean isIncome) {
        int total = 0;
        for (AccountBookEntry entry : entries) {
            if (entry.getDate().equals(date) && entry.isIncome() == isIncome) {
                total += entry.getAmount();
            }
        }
        return total;
    }
}
