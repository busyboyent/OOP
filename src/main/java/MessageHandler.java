import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MessageHandler {

    public String onUpdateReceived(String messageText) {

        switch (messageText) {

            case "/start":
                return Text.START_MASSEGE;

            case "/help":
                return Text.HELP_MASSEGE;
        }
        return Text.DEFAULT_MASSEGE;
    }
}
