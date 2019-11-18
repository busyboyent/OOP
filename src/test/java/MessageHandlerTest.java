import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class MessageHandlerTest {

    @Test
    public void onUpdateReceived_Help() {

        var messageHandler = new MessageHandler();

        var helpTest = messageHandler.onUpdateReceived("/help");
        Assert.assertEquals(Text.HELP_MASSEGE, helpTest);
    }

    @Test
    public void onUpdateReceived_Start() {

        var messageHandler = new MessageHandler();

        var startTest = messageHandler.onUpdateReceived("/start");
        Assert.assertEquals(Text.START_MASSEGE, startTest);
    }

    @Test
    public void onUpdateReceived_Default() {

        var messageHandler = new MessageHandler();

        var defaultTest = messageHandler.onUpdateReceived("ooAAooAAoo");
        Assert.assertEquals(Text.DEFAULT_MASSEGE, defaultTest);
    }

}