import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

//src\\main\\resources\\userDatabase.txt
class UserDatabase {
    private Map<Long, UserData> data;
    private String dataName;

    UserDatabase(String file){
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

    UserData tryCreateNewUserData(Long user){
        var userData = data.get(user);
        if (userData == null){
            userData = new UserData();
            data.put(user, userData);
        }
        return userData;
    }

    UserData clearUserData(Long user){
        var userData = new UserData();
        data.put(user, userData);
        return userData;
    }

    void saveData(){
        String dataJson = new Gson().toJson(data);
        MyFileWriter.write(dataJson, dataName);
    }

    @Override
    public String toString() {
        var string = "";
        for(Map.Entry<Long, UserData> item : data.entrySet()){
            string += "id: " + item.getKey() + "\ndata:\n" + item.getValue().toString() + "\n\n";
        }
        return string;
    }
}
