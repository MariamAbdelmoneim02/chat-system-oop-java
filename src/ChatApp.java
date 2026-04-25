import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ChatSystem chatSystem = new ChatSystem();

    public static void main(String[] args) {
        System.out.println("=== Welcome to the Console Chat System ===\n");

        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    sendMessage();
                    break;
                case 3:
                    viewMessages();
                    break;
                case 4:
                    broadcastMessage();
                    break;
                case 5:
                    System.out.println("Exiting chat system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void displayMenu() {
        System.out.println("\n=== Chat System Menu ===");
        System.out.println("1. Create User");
        System.out.println("2. Send Message");
        System.out.println("3. View Messages");
        System.out.println("4. Broadcast Message (Admin only)");
        System.out.println("5. Exit");
    }

    private static void createUser() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Username cannot be empty.");
            return;
        }

        System.out.println("Select user type:");
        System.out.println("1. Admin");
        System.out.println("2. Regular");
        int typeChoice = getIntInput("Choice: ");

        User.UserType type;
        if (typeChoice == 1) {
            type = User.UserType.ADMIN;
        } else if (typeChoice == 2) {
            type = User.UserType.REGULAR;
        } else {
            System.out.println("Invalid choice. Defaulting to Regular user.");
            type = User.UserType.REGULAR;
        }

        chatSystem.createUser(name, type);
    }

    private static void sendMessage() {
        if (!chatSystem.hasUsers()) {
            System.out.println("No users exist. Please create at least one user first.");
            return;
        }

        System.out.println("\n--- Select Sender ---");
        User sender = selectUser("Choose sender (enter number): ");
        if (sender == null) return;

        System.out.println("\n--- Select Receiver ---");
        User receiver = selectUser("Choose receiver (enter number): ");
        if (receiver == null) return;

        if (sender.equals(receiver)) {
            System.out.println("You cannot send a message to yourself.");
            return;
        }

        System.out.print("Enter message content: ");
        String content = scanner.nextLine().trim();

        if (content.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }

        chatSystem.sendMessage(sender, receiver, content);
    }

    private static void viewMessages() {
        if (!chatSystem.hasUsers()) {
            System.out.println("No users exist. Please create at least one user first.");
            return;
        }

        User user = selectUser("Select user to view messages (enter number): ");
        if (user != null) {
            chatSystem.displayMessagesForUser(user);
        }
    }

    private static void broadcastMessage() {
        if (!chatSystem.hasUsers()) {
            System.out.println("No users exist. Please create at least one user first.");
            return;
        }

        List<User> adminUsers = chatSystem.getAdminUsers();
        if (adminUsers.isEmpty()) {
            System.out.println("No admin users available. Please create an admin user to broadcast.");
            return;
        }

        System.out.println("\n--- Broadcast Message (Admin only) ---");
        System.out.println("Select admin user to send broadcast:");
        for (int i = 0; i < adminUsers.size(); i++) {
            System.out.println((i + 1) + ". " + adminUsers.get(i).getName());
        }

        int adminChoice = getIntInput("Choice: ");
        if (adminChoice < 1 || adminChoice > adminUsers.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        User adminUser = adminUsers.get(adminChoice - 1);
        System.out.print("Enter broadcast message content: ");
        String content = scanner.nextLine().trim();

        if (content.isEmpty()) {
            System.out.println("Broadcast message cannot be empty.");
            return;
        }

        chatSystem.broadcastMessage(adminUser, content);
    }

    private static User selectUser(String prompt) {
        List<User> users = chatSystem.getUsers();
        System.out.println("Available users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }

        int choice = getIntInput(prompt);
        if (choice < 1 || choice > users.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return users.get(choice - 1);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}