import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class UserDatabase {
    Map<String, UserData> data;

    public UserDatabase(){

    }

    public HashMap<String, UserData> InitUserDatabase(){
        var data = SimpleReader.Read("src\\main\\resources\\userDatabase.txt");
        if (data == null) return new HashMap<>();
        Gson gson = new Gson();
        HashMap<String, UserData> userDatabase = gson.fromJson(
                data,
                new TypeToken<HashMap<String, UserData>>(){}.getType());
        return  userDatabase;
    }
}
