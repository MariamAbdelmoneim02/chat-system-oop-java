public class Message {
    private User sender;
    private User recipient;     // null for broadcast messages
    private String content;
    private boolean isBroadcast;

    // Constructor for direct message
    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.isBroadcast = false;
    }

    // Constructor for broadcast message
    public Message(User sender, String content) {
        this.sender = sender;
        this.recipient = null;
        this.content = content;
        this.isBroadcast = true;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public boolean isBroadcast() {
        return isBroadcast;
    }

    public boolean isForUser(User user) {
        // Message is for this user if it's a direct message to them OR a broadcast message
        return isBroadcast || (recipient != null && recipient.equals(user));
    }

    @Override
    public String toString() {
        if (isBroadcast) {
            return "[BROADCAST] From " + sender.getName() + ": " + content;
        } else {
            return "From " + sender.getName() + ": " + content;
        }
    }
}