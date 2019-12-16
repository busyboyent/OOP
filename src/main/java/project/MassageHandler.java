package project;

public interface MassageHandler {
    ClusterMessage onUpdateReceived(String messageText, Long userId);
}
