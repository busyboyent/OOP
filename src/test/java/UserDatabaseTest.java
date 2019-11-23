import static org.junit.Assert.*;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;

public class UserDatabaseTest {

    @Test
    public void TryGetUserData_Null_EmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.TryGetUserData("aaaaa");
        Assert.assertEquals(null, result);
    }

    @Test
    public void TryCreateNewUserData_NotNull_EmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.TryCreateNewUserData("aaaaa");
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void CreateNewUserData_NotNull_NotEmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.ClearUserData("aaaaa");
        result.SetName("ssss");
        result = dataBase.ClearUserData("aaaaa");
        Assert.assertEquals("unknownName", result.GetName());
    }
}