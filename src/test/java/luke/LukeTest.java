package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LukeTest {
    @Test
    public void UITest() {
        Luke luke = new Luke();
        assertEquals("Hello! I'm Luke\n"
                + "What can I do for you?", luke.ui.hello);
        assertEquals("Bye. Hope to see you again soon!", luke.ui.goodbye);
    }
}

