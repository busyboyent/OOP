import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import java.io.File;

public class DatabaseTest {

    @Test
    public void TryGetUserData_Null_EmptyData() {
        var dataBase = new Database("src\\main\\resources\\testData.txt");
        var result = dataBase.tryGetUserData((long)4);
        assertNull(result);
    }

    @Test
    public void TryCreateNewUserData_NotNull_EmptyData() {
        var dataBase = new Database("src\\main\\resources\\testData.txt");
        var result = dataBase.tryCreateNewUserData((long)2);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void CreateNewUserData_NotNull_NotEmptyData() {
        var dataBase = new Database("src\\main\\resources\\testData.txt");
        var result = dataBase.clearUserData((long)1);
        result.setName("ssss");
        result = dataBase.clearUserData((long)1);
        Assert.assertEquals("unknownName", result.getName());
    }

    @Test
    public void ToString() {
        var dataBase = new Database("src\\main\\resources\\testData.txt");
        var result = dataBase.clearUserData((long)1);
        result.setName("ssss");
        result = dataBase.clearUserData((long)1);
        Assert.assertEquals("id: 1\ndata:\nname: unknownName\ncity: unknownCity\n\n", dataBase.toString());
    }

    @After
    public void tearDown(){
        File file = new File("src\\main\\resources\\testData.txt");
        file.delete();
    }
}