package main.java;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class Bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

       MassegeHandler massegeHandler = new MassegeHandler();
       massegeHandler.onUpdateReceived(update, this);

    }

    public String getBotUsername() {
        try {
            Scanner scanner = new Scanner(Paths.get("src\\main\\resources\\botusername.txt"),
                    StandardCharsets.UTF_8.name());
            String botUsername = scanner.useDelimiter("\n").next();
            return botUsername;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
//логика токена
    public String getBotToken() {
        try{
            Scanner scanner = new Scanner(Paths.get("src\\main\\resources\\token.txt"),
                    StandardCharsets.UTF_8.name());
            String token = scanner.useDelimiter("\n").next();

            scanner.close();

            return token;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}