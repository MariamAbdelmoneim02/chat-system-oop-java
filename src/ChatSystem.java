import java.util.ArrayList;
import java.util.List;

public class ChatSystem {
    private List<User> users;
    private List<Message> messages;

    public ChatSystem() {
        users = new ArrayList<>();
        messages = new ArrayList<>();
    }

    // Create a new user
    public boolean createUser(String name, User.UserType type) {
        if (findUserByName(name) != null) {
            System.out.println("Error: User '" + name + "' already exists!");
            return false;
        }
        User newUser = new User(name, type);
        users.add(newUser);
        System.out.println("User '" + name + "' created as " + type);
        return true;
    }

    // Find user by name
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    // Get list of all users
    public List<User> getUsers() {
        return users;
    }

    // Send a direct message
    public boolean sendMessage(User sender, User recipient, String content) {
        if (sender == null || recipient == null) {
            System.out.println("Error: Invalid sender or recipient.");
            return false;
        }
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Error: Message content cannot be empty.");
            return false;
        }
        Message message = new Message(sender, recipient, content);
        messages.add(message);
        System.out.println("Message sent from " + sender.getName() + " to " + recipient.getName());
        return true;
    }

    // Broadcast message (Admin only)
    public boolean broadcastMessage(User adminUser, String content) {
        if (adminUser == null || !adminUser.isAdmin()) {
            System.out.println("Error: Only admin users can send broadcast messages.");
            return false;
        }
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Error: Broadcast content cannot be empty.");
            return false;
        }
        Message broadcast = new Message(adminUser, content);
        messages.add(broadcast);
        System.out.println("Broadcast message sent by admin '" + adminUser.getName() + "' to all users.");
        return true;
    }

    // Get all messages for a specific user
    public List<Message> getMessagesForUser(User user) {
        List<Message> userMessages = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.isForUser(user)) {
                userMessages.add(msg);
            }
        }
        return userMessages;
    }

    // Display all messages for a user
    public void displayMessagesForUser(User user) {
        List<Message> userMessages = getMessagesForUser(user);
        if (userMessages.isEmpty()) {
            System.out.println(user.getName() + " has no messages yet.");
        } else {
            System.out.println("\n=== Messages for " + user.getName() + " ===");
            for (int i = 0; i < userMessages.size(); i++) {
                System.out.println((i + 1) + ". " + userMessages.get(i));
            }
        }
        System.out.println();
    }

    // Check if any users exist
    public boolean hasUsers() {
        return !users.isEmpty();
    }

    // Get list of admin users
    public List<User> getAdminUsers() {
        List<User> admins = new ArrayList<>();
        for (User user : users) {
            if (user.isAdmin()) {
                admins.add(user);
            }
        }
        return admins;
    }
}