package project;

public interface UserDatabase {
    UserData tryCreateNewUserData(Long user);
    UserData clearUserData(Long user);
    void saveData();
}
