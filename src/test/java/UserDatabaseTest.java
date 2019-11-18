import static org.junit.Assert.*;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;

public class UserDatabaseTest {
    @Test
    public void InitUserDatabase() {
        Gson gson = new Gson();
        var expect = new HashMap<String, UserData>();
        expect.put("a", new UserData());
        expect.get("a").SetCity("asdf");
        String data = gson.toJson(expect);
        SimpleWriter.Write(data, "src\\main\\resources\\userDatabase.txt");
        var result = new UserDatabase().InitUserDatabase();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("asdf", result.get("a").GetCity());
    }
}