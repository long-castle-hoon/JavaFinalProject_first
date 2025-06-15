import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarUtil {
    public static void printCalendar(LocalDate date) {
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDay = yearMonth.atDay(1);
        int monthLength = yearMonth.lengthOfMonth();

        System.out.println("\n" + yearMonth.getYear() + "년 " + yearMonth.getMonthValue() + "월");
        System.out.println("일  월  화  수  목  금  토");

        // 첫 날의 요일에 따른 공백 출력
        int dayOfWeek = firstDay.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < dayOfWeek; i++) {
            System.out.print("    ");
        }

        // 날짜 출력
        for (int day = 1; day <= monthLength; day++) {
            System.out.printf("%2d  ", day);

            // 토요일이면 줄바꿈
            if ((dayOfWeek + day) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public static boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
