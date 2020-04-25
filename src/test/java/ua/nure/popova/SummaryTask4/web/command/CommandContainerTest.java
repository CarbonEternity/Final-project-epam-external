package ua.nure.popova.SummaryTask4.web.command;

import org.junit.Test;
import static org.junit.Assert.assertSame;

public class CommandContainerTest {

    @Test
    public void doGetTest() {
        assertSame(LoginCommand.class, CommandContainer.get("login").getClass());
    }

    @Test
    public void noSuchCommandTest() {
        assertSame(NoCommand.class, CommandContainer.get("not").getClass());
    }

    @Test
    public void noCommandTest() {
        assertSame(NoCommand.class, CommandContainer.get(null).getClass());
    }

}
