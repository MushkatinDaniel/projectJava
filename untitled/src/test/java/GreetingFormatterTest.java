import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GreetingFormatterTest {

    @Test
    public void testBuildGreeting_validArguments_success() {

        String name = "Daniil";
        int port = 54821;


        String result = GreetingFormatter.buildGreeting(name, port);


        Assertions.assertEquals("Hi Daniil, your port is 54821", result);
    }

    @Test
    public void testBuildGreeting_containsNameAndPort_success() {

        String name = "Alice";
        int port = 8080;


        String result = GreetingFormatter.buildGreeting(name, port);


        Assertions.assertTrue(result.contains(name));
        Assertions.assertTrue(result.contains(String.valueOf(port)));
    }

    @Test
    public void testBuildGreeting_blankName_throwsException() {

        String name = "   ";
        int port = 8080;


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> GreetingFormatter.buildGreeting(name, port));
    }

    @Test
    public void testBuildGreeting_nullName_throwsException() {

        String name = null;
        int port = 8080;


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> GreetingFormatter.buildGreeting(name, port));
    }

    @Test
    public void testBuildGreeting_invalidPort_throwsException() {

        String name = "Bob";
        int invalidPort = -1;


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> GreetingFormatter.buildGreeting(name, invalidPort));
    }
}
