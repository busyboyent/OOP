import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

//src\\main\\resources\\userDatabase.txt
public class UserDatabase {
    Map<String, UserData> data;
    String dataName;

    public UserDatabase(String file){
        dataName = file;
        data = InitUserDatabase();
    }

    public HashMap<String, UserData> InitUserDatabase(){
        var data = SimpleReader.Read(dataName);
        if (data == null) return new HashMap<>();
        Gson gson = new Gson();
        HashMap<String, UserData> userDatabase = gson.fromJson(
                data,
                new TypeToken<HashMap<String, UserData>>(){}.getType());
        return  userDatabase;
    }

    public UserData TryGetUserData(String user){
        return data.get(user);
    }

    public UserData TryCreateNewUserData(String user){
        var userData = data.get(user);
        if (userData == null){
            userData = new UserData();
            data.put(user, userData);
        }
        return userData;
    }

    public UserData ClearUserData(String user){
        var userData = new UserData();
        data.put(user, userData);
        return userData;
    }

    public void SaveData(){
        String dataJson = new Gson().toJson(data);
        SimpleWriter.Write(dataJson, dataName);
    }
}
