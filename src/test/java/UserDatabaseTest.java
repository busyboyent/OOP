import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import java.io.File;

public class UserDatabaseTest {

    @Test
    public void TryGetUserData_Null_EmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.TryGetUserData((long)4);
        assertNull(result);
    }

    @Test
    public void TryCreateNewUserData_NotNull_EmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.TryCreateNewUserData((long)2);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void CreateNewUserData_NotNull_NotEmptyData() {
        var dataBase = new UserDatabase("src\\main\\resources\\testData.txt");
        var result = dataBase.ClearUserData((long)1);
        result.SetName("ssss");
        result = dataBase.ClearUserData((long)1);
        Assert.assertEquals("unknownName", result.GetName());
    }

    @After
    public void tearDown(){
        File file = new File("src\\main\\resources\\testData.txt");
        file.delete();
    }
}