package main.java;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MassegeHandler {

    public void sendMsg(Message message, String text, Bot bot) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);
        try {

            GetButtons getButtons = new GetButtons();
            getButtons.setButtons(sendMessage);
            bot.sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update, Bot bot) {

        Message message = update.getMessage();
        if (message == null || !message.hasText()) {
            return;
        }

        String response = Text.DEFAULT_MASSEGE;
        switch (message.getText()) {

            case "/start":
                response = Text.START_MASSEGE;
                break;

            case "/help":
                response = Text.HELP_MASSEGE;
                break;
        }
        sendMsg(message, response, bot);
    }
}
