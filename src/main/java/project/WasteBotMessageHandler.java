package project;

import java.util.Arrays;
import java.util.Random;


class WasteBotMessageHandler implements MassageHandler {

    private UserDatabase database;
    private Long[] mainUsers;

    WasteBotMessageHandler(UserDatabase database){
        this.database = database;
        mainUsers = new Long[] { (long)462903671, (long)549213748 };
    }

    private ClusterMessage onUpdateReceivedSwitch(String messageText, Long userId){
        var userData = database.tryCreateNewUserData(userId);

        switch (messageText) {

            case "/start":
                return new ClusterMessage(Text.START_MASSEGE);

            case "/help":
                return new ClusterMessage(Text.HELP_MASSEGE);

            case "/id":
                return new ClusterMessage(userId.toString());

            case "/data":
                return new ClusterMessage(userData.toString());

            case "/city":
                userData.setRegistrationStatus(RegistrationStatus.SET_CITY);
                return new ClusterMessage(Text.CITY_REGISTRATION_MESSAGE);

            case "/name":
                userData.setRegistrationStatus(RegistrationStatus.SET_NAME);
                return new ClusterMessage(Text.NAME_REGISTRATION_MESSAGE);

            case "/meme":
                var random = new Random();
                var index = random.nextInt(Photo.memes.length);
                return new ClusterMessage(null, Photo.memes[index]);

            case "/allData":
                if (Arrays.asList(mainUsers).contains(userId))
                    return new ClusterMessage(database.toString());
                break;
            case "/trash":
                return new ClusterMessage(Text.TRASH_DESCRIPTION, Photo.trash);
            case "/glass":
                return new ClusterMessage(Text.GLASS);
            case "/plastic":
                return new ClusterMessage(Text.PLASTIC);
            case "/organics":
                return new ClusterMessage(Text.ORGANIC);
            case "/paper":
                return new ClusterMessage(Text.PAPER);
            case "/metal":
                return new ClusterMessage(Text.METAL);

            default:
                var result = checkUserRegistrationStatusSwitch(userData, messageText);
                if (result != null) return result;
        }
        return new ClusterMessage(Text.DEFAULT_MASSEGE);
    }

    private ClusterMessage checkUserRegistrationStatusSwitch(UserData userData, String messageText){
        switch (userData.getRegistrationStatus()) {
            case SET_CITY:
                userData.setCity(messageText);
                userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                return new ClusterMessage(Text.REGISTRATION_END_MESSEGE);
            case SET_NAME:
                userData.setName(messageText);
                userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                return new ClusterMessage(Text.REGISTRATION_END_MESSEGE);
        }
        return null;
    }

    public ClusterMessage onUpdateReceived(String messageText, Long userId) {
        var result = onUpdateReceivedSwitch(messageText, userId);
        database.saveData();
        return result;
    }
}
