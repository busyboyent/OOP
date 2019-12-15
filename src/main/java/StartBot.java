import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class StartBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        var database = new Database("src\\main\\resources\\userDatabase.txt");
        var messageHandler = new WasteBotMessageHandler(database);

        try {
            telegramBotsApi.registerBot(new Bot(messageHandler));

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
