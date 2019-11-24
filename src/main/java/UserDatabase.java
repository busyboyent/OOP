import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

//src\\main\\resources\\userDatabase.txt
class UserDatabase {
    private Map<Long, UserData> data;
    private String dataName;

    UserDatabase(String file){
        dataName = file;
        data = InitUserDatabase();
    }

    private HashMap<Long, UserData> InitUserDatabase(){
        var data = SimpleReader.Read(dataName);
        if (data == null) return new HashMap<Long, UserData>();
        Gson gson = new Gson();
        return gson.fromJson(
                data,
                new TypeToken<HashMap<Long, UserData>>(){}.getType());
    }

    UserData TryGetUserData(Long user){
        return data.get(user);
    }

    UserData TryCreateNewUserData(Long user){
        var userData = data.get(user);
        if (userData == null){
            userData = new UserData();
            data.put(user, userData);
        }
        return userData;
    }

    UserData ClearUserData(Long user){
        var userData = new UserData();
        data.put(user, userData);
        return userData;
    }

    void SaveData(){
        String dataJson = new Gson().toJson(data);
        SimpleWriter.Write(dataJson, dataName);
    }
}
