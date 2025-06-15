import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = UserManager.getInstance();
    private static AccountBookManager accountManager = AccountBookManager.getInstance();
    private static ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static DiaryManager diaryManager = DiaryManager.getInstance();
    private static LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) {
        System.out.println("=== 가계부 + 일정 + 감정일기 프로그램 ===");

        boolean running = true;
        while (running) {
            if (userManager.getCurrentUser() == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n=== 로그인 메뉴 ===");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("0. 종료");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 0:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void login() {
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        if (userManager.login(id, password)) {
            System.out.println(userManager.getCurrentUser().getNickname() + "님 환영합니다!");
        } else {
            System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
        }
    }

    private static void register() {
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("닉네임: ");
        String nickname = scanner.nextLine();

        if (userManager.register(id, password, nickname)) {
            System.out.println("회원가입 성공! 로그인해주세요.");
        } else {
            System.out.println("회원가입 실패. 이미 존재하는 아이디입니다.");
        }
    }

    private static void showMainMenu() {
        // 달력 출력
        CalendarUtil.printCalendar(currentDate);
        System.out.println("현재 날짜: " + currentDate);

        System.out.println("\n=== 메인 메뉴 ===");
        System.out.println("1. 가계부 입력/조회");
        System.out.println("2. 일정 입력/조회");
        System.out.println("3. 감정일기 작성/조회");
        System.out.println("4. 날짜 변경");
        System.out.println("5. 로그아웃");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                accountBookMenu();
                break;
            case 2:
                scheduleMenu();
                break;
            case 3:
                diaryMenu();
                break;
            case 4:
                changeDateMenu();
                break;
            case 5:
                userManager.logout();
                System.out.println("로그아웃 되었습니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void accountBookMenu() {
        System.out.println("\n=== 가계부 메뉴 ===");
        System.out.println("1. 수입/지출 입력");
        System.out.println("2. 오늘 가계부 조회");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                addAccountEntry();
                break;
            case 2:
                viewAccountEntries();
                break;
            case 0:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void addAccountEntry() {
        System.out.print("카테고리: ");
        String category = scanner.nextLine();

        System.out.print("금액: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        System.out.print("수입(1)/지출(2): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        boolean isIncome = (type == 1);

        System.out.print("메모: ");
        String memo = scanner.nextLine();

        accountManager.addEntry(currentDate, category, amount, isIncome, memo);
        System.out.println("가계부 항목이 추가되었습니다.");
    }

    private static void viewAccountEntries() {
        ArrayList<AccountBookEntry> entries = accountManager.getEntriesByDate(currentDate);

        if (entries.isEmpty()) {
            System.out.println("해당 날짜의 가계부 내역이 없습니다.");
            return;
        }

        System.out.println("\n=== " + currentDate + " 가계부 내역 ===");
        for (AccountBookEntry entry : entries) {
            System.out.println(entry);
        }

        int incomeTotal = accountManager.getDailyTotal(currentDate, true);
        int expenseTotal = accountManager.getDailyTotal(currentDate, false);

        System.out.println("\n총 수입: " + incomeTotal + "원");
        System.out.println("총 지출: " + expenseTotal + "원");
        System.out.println("일일 수지: " + (incomeTotal - expenseTotal) + "원");
    }

    private static void scheduleMenu() {
        System.out.println("\n=== 일정 메뉴 ===");
        System.out.println("1. 일정 추가");
        System.out.println("2. 일정 조회");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                addSchedule();
                break;
            case 2:
                viewSchedules();
                break;
            case 0:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void addSchedule() {
        System.out.print("일정 제목: ");
        String title = scanner.nextLine();

        System.out.print("시작 시간(HH:mm): ");
        String startTimeStr = scanner.nextLine();
        LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        System.out.print("종료 시간(HH:mm): ");
        String endTimeStr = scanner.nextLine();
        LocalTime endTime = LocalTime.parse(endTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        System.out.print("메모: ");
        String description = scanner.nextLine();

        scheduleManager.addSchedule(currentDate, startTime, endTime, title, description);
        System.out.println("일정이 추가되었습니다.");
    }

    private static void viewSchedules() {
        ArrayList<Schedule> schedules = scheduleManager.getSchedulesByDate(currentDate);

        if (schedules.isEmpty()) {
            System.out.println("해당 날짜의 일정이 없습니다.");
            return;
        }

        System.out.println("\n=== " + currentDate + " 일정 ===");
        for (Schedule schedule : schedules) {
            System.out.println(schedule);
        }
    }

    private static void diaryMenu() {
        System.out.println("\n=== 감정일기 메뉴 ===");
        System.out.println("1. 일기 작성");
        System.out.println("2. 일기 조회");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                writeDiary();
                break;
            case 2:
                viewDiary();
                break;
            case 0:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void writeDiary() {
        System.out.println("오늘의 감정을 선택하세요.");
        System.out.println("1. 행복 2. 슬픔 3. 화남 4. 보통 5. 기타");
        System.out.print("선택: ");
        int emotionChoice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        String emotion;
        switch (emotionChoice) {
            case 1: emotion = "행복"; break;
            case 2: emotion = "슬픔"; break;
            case 3: emotion = "화남"; break;
            case 4: emotion = "보통"; break;
            default:
                System.out.print("감정 입력: ");
                emotion = scanner.nextLine();
        }

        System.out.println("일기 내용을 작성하세요 (종료: 빈 줄 입력):");
        StringBuilder content = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            content.append(line).append("\n");
        }

        diaryManager.addEntry(currentDate, content.toString(), emotion);
        System.out.println("일기가 저장되었습니다.");
    }

    private static void viewDiary() {
        DiaryEntry entry = diaryManager.getEntryByDate(currentDate);

        if (entry == null) {
            System.out.println("해당 날짜의 일기가 없습니다.");
            return;
        }

        System.out.println("\n=== " + currentDate + " 일기 ===");
        System.out.println(entry);
    }

    private static void changeDateMenu() {
        System.out.println("\n=== 날짜 변경 ===");
        System.out.println("1. 전날");
        System.out.println("2. 다음날");
        System.out.println("3. 날짜 선택");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        switch (choice) {
            case 1:
                currentDate = currentDate.minusDays(1);
                System.out.println("날짜가 변경되었습니다: " + currentDate);
                break;
            case 2:
                currentDate = currentDate.plusDays(1);
                System.out.println("날짜가 변경되었습니다: " + currentDate);
                break;
            case 3:
                setSpecificDate();
                break;
            case 0:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void setSpecificDate() {
        System.out.print("연도: ");
        int year = scanner.nextInt();

        System.out.print("월(1-12): ");
        int month = scanner.nextInt();

        System.out.print("일: ");
        int day = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (CalendarUtil.isValidDate(year, month, day)) {
            currentDate = LocalDate.of(year, month, day);
            System.out.println("날짜가 변경되었습니다: " + currentDate);
        } else {
            System.out.println("유효하지 않은 날짜입니다.");
        }
    }
}
