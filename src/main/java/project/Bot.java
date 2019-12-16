package project;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private MassageHandler messageHandler;

    public Bot(MassageHandler messageHandler){
        this.messageHandler = messageHandler;
    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message == null || !message.hasText()) {
            return;
        }

        var userId = update.getMessage().getChatId();

        var clusterMessage = messageHandler.onUpdateReceived(message.getText(), userId);
        if (clusterMessage.haveMessage()) {
            sendMsg(update.getMessage(), clusterMessage.getMessage());
        }
        if (clusterMessage.havePhoto()) {
            sendMessagePhoto(update.getMessage(), clusterMessage.getPhoto());
        }

    }

    private void sendMsg(Message message, String text) {
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);
        try {

            GetButtons getButtons = new GetButtons();
            getButtons.setButtons(sendMessage);
            sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessagePhoto(Message message, String photo) {
        var sendPhoto = new SendPhoto();

        sendPhoto.setChatId(message.getChatId().toString());

        sendPhoto.setPhoto(photo);
        try {
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return MyFileReader.read("src\\main\\resources\\botusername.txt");
    }

    public String getBotToken() {
        return MyFileReader.read("src\\main\\resources\\token.txt");
    }
}