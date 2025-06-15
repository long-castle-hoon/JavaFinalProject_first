public class User {
    private String id;
    private String password;
    private String nickname;

    public User(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + "', nickname='" + nickname + "'}";
    }
}
