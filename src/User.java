public class User {
    private String name;
    private UserType type;

    public enum UserType {
        ADMIN, REGULAR
    }

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public boolean isAdmin() {
        return type == UserType.ADMIN;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}