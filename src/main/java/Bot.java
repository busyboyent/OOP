import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private MessageHandler messageHandler = new MessageHandler("src\\main\\resources\\userDatabase.txt");

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message == null || !message.hasText()) {
            return;
        }

        var userId = update.getMessage().getChatId();

        var text = messageHandler.onUpdateReceived(message.getText(), userId);
        sendMsg(update.getMessage(), text);

    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
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

    public String getBotUsername() {
        return SimpleReader.Read("src\\main\\resources\\botusername.txt");
    }

    public String getBotToken() {
        return SimpleReader.Read("src\\main\\resources\\token.txt");
    }
}