import java.time.LocalDate;

public class AccountBookEntry {
    private LocalDate date;
    private String category;
    private int amount;
    private boolean isIncome; // true: 수입, false: 지출
    private String memo;

    public AccountBookEntry(LocalDate date, String category, int amount, boolean isIncome, String memo) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.isIncome = isIncome;
        this.memo = memo;
    }

    // Getter
    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public String getMemo() {
        return memo;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %,d원 (%s) - %s",
                date, category, amount, isIncome ? "수입" : "지출", memo);
    }
}
