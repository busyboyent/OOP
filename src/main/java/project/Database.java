package project;

import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

//src\\main\\resources\\userDatabase.txt
class Database implements UserDatabase {
    private Map<Long, UserData> data;
    private String dataName;

    Database(String file){
        dataName = file;
        data = initUserDatabase();
    }

    private HashMap<Long, UserData> initUserDatabase(){
        var data = MyFileReader.read(dataName);
        if (data == null) return new HashMap<Long, UserData>();
        Gson gson = new Gson();
        return gson.fromJson(
                data,
                new TypeToken<HashMap<Long, UserData>>(){}.getType());
    }

    UserData tryGetUserData(Long user){
        return data.get(user);
    }

    public UserData tryCreateNewUserData(Long user){
        var userData = data.get(user);
        if (userData == null){
            userData = new UserData();
            data.put(user, userData);
        }
        return userData;
    }

    public UserData clearUserData(Long user){
        var userData = new UserData();
        data.put(user, userData);
        return userData;
    }

    public void saveData(){
        String dataJson = new Gson().toJson(data);
        MyFileWriter.write(dataJson, dataName);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Map.Entry<Long, UserData> item : data.entrySet()){
            string
                    .append("id: ")
                    .append(item.getKey())
                    .append("\ndata:\n")
                    .append(item.getValue().toString())
                    .append("\n\n");
        }
        return string.toString();
    }
}
