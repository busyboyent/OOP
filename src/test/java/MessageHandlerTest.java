import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import java.io.File;


public class MessageHandlerTest {

    @Test
    public void init(){
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
    }

    @Test
    public void onUpdateReceived_Help() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var helpTest = messageHandler.onUpdateReceived("/help", id);
        Assert.assertEquals(Text.HELP_MASSEGE, helpTest);
    }

    @Test
    public void onUpdateReceived_Start() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var startTest = messageHandler.onUpdateReceived("/start", id);
        Assert.assertEquals(Text.START_MASSEGE, startTest);
    }

    @Test
    public void onUpdateReceived_Default() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var defaultTest = messageHandler.onUpdateReceived("ooAAooAAoo", id);
        Assert.assertEquals(Text.DEFAULT_MASSEGE, defaultTest);
    }

    @Test
    public void onUpdateReceived_MyData() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var defaultTest = messageHandler.onUpdateReceived("/data", id);
        Assert.assertEquals("name: unknownName\ncity: unknownCity", defaultTest);
    }

    @Test
    public void onUpdateReceived_SetMyName() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var defaultTest = messageHandler.onUpdateReceived("/name", id);
        Assert.assertEquals(Text.NAME_REGISTRATION_MESSAGE, defaultTest);
        defaultTest = messageHandler.onUpdateReceived("Инакентий", id);
        Assert.assertEquals(Text.REGISTRATION_END_MESSEGE, defaultTest);
        defaultTest = messageHandler.onUpdateReceived("/data", id);
        Assert.assertEquals("name: Инакентий\ncity: unknownCity", defaultTest);
    }

    @Test
    public void onUpdateReceived_SetMyCity() {
        var database = new Database("src\\main\\resources\\testData.txt");
        var messageHandler = new MessageHandler(database);
        long id = 1;

        var defaultTest = messageHandler.onUpdateReceived("/city", id);
        Assert.assertEquals(Text.CITY_REGISTRATION_MESSAGE, defaultTest);
        defaultTest = messageHandler.onUpdateReceived("Мухосранск", id);
        Assert.assertEquals(Text.REGISTRATION_END_MESSEGE, defaultTest);
        defaultTest = messageHandler.onUpdateReceived("/data", id);
        Assert.assertEquals("name: unknownName\ncity: Мухосранск", defaultTest);
    }

    @After
    public void tearDown(){
        File file = new File("src\\main\\resources\\testData.txt");
        file.delete();
    }
}