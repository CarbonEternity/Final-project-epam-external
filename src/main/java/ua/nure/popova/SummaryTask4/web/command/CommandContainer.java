package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private CommandContainer(){
        //no op
    }

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands

        commands.put("register", new RegistrationCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());
//
//        // client commands
        commands.put("sortFacultyList", new ListOrdersCommand());
        commands.put("viewFacultyAndRequirements", new ListRequirementsCommand());
//
//        // admin commands
        commands.put("adminkahome", new ListEnrolleesCommand());









        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
