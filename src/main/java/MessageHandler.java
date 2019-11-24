import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

class MessageHandler {

    private UserDatabase userDatabase;
    private Long[] mainUsers;

    MessageHandler(String dataFile){
        userDatabase = new UserDatabase(dataFile);
        mainUsers = new Long[] { (long)462903671 };
    }

    private String onUpdateReceivedSwitch(String messageText, Long userId){
        var userData = userDatabase.TryCreateNewUserData(userId);

        switch (messageText) {

            case "/start":
                return Text.START_MASSEGE;

            case "/help":
                return Text.HELP_MASSEGE;

            case "/id":
                return userId.toString();

            case "/data":
                return userData.toString();

            case "/city":
                userData.setRegistrationStatus(RegistrationStatus.SET_CITY);
                return Text.CITY_REGISTRATION_MESSAGE;

            case "/name":
                userData.setRegistrationStatus(RegistrationStatus.SET_NAME);
                return Text.NAME_REGISTRATION_MESSAGE;

            default:
                switch (userData.getRegistrationStatus()) {
                    case SET_CITY:
                        userData.SetCity(messageText);
                        userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                        return Text.REGISTRATION_END_MESSEGE;
                    case SET_NAME:
                        userData.SetName(messageText);
                        userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                        return Text.REGISTRATION_END_MESSEGE;
                }
        }
        return Text.DEFAULT_MASSEGE;
    }

    String onUpdateReceived(String messageText, Long userId) {
        var result = onUpdateReceivedSwitch(messageText, userId);
        userDatabase.SaveData();
        return result;
    }
}
