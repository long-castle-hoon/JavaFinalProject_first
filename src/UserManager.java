import java.util.ArrayList;

public class UserManager {
    private static UserManager instance = null;
    private ArrayList<User> users;
    private User currentUser = null;

    private UserManager() {
        users = new ArrayList<>();
        // 기본 사용자 추가
        users.add(new User("admin", "1234", "관리자"));
        users.add(new User("user1", "1234", "사용자1"));
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean register(String id, String password, String nickname) {
        // ID 중복 체크
        for (User user : users) {
            if (user.getId().equals(id)) {
                return false;
            }
        }
        users.add(new User(id, password, nickname));
        return true;
    }

    public boolean login(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.checkPassword(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
