import java.util.Arrays;


class WasteBotMessageHandler implements MassageHandler {

    private UserDatabase database;
    private Long[] mainUsers;

    WasteBotMessageHandler(UserDatabase database){
        this.database = database;
        mainUsers = new Long[] { (long)462903671, (long)549213748 };
    }

    private String onUpdateReceivedSwitch(String messageText, Long userId){
        var userData = database.tryCreateNewUserData(userId);

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

            case "/allData":
                if (Arrays.asList(mainUsers).contains(userId))
                    return database.toString();
                break;

            default:
                var result = checkUserRegistrationStatusSwitch(userData, messageText);
                if (result != null) return result;
        }
        return Text.DEFAULT_MASSEGE;
    }

    private String checkUserRegistrationStatusSwitch(UserData userData, String messageText){
        switch (userData.getRegistrationStatus()) {
            case SET_CITY:
                userData.setCity(messageText);
                userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                return Text.REGISTRATION_END_MESSEGE;
            case SET_NAME:
                userData.setName(messageText);
                userData.setRegistrationStatus(RegistrationStatus.INACTIVE);
                return Text.REGISTRATION_END_MESSEGE;
        }
        return null;
    }

    public String onUpdateReceived(String messageText, Long userId) {
        var result = onUpdateReceivedSwitch(messageText, userId);
        database.saveData();
        return result;
    }
}
