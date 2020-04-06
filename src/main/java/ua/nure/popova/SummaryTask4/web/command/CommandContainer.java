package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.web.command.admin.*;
import ua.nure.popova.SummaryTask4.web.command.client.*;

import java.util.Map;
import java.util.TreeMap;

public final class CommandContainer {

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

//        // client commands
        commands.put("sortFacultyList", new ListOrdersCommand());
        commands.put("viewFacultyAndRequirements", new ListRequirementsCommand());
        commands.put("createOrder", new CreateOrderCommand());
        commands.put("deleteApplication", new DeleteOrderCommand());

//        // admin commands
        commands.put("actionWithFaculties", new ListFacultiesForAdminCommand()); //faculties with buttons
        commands.put("sortFaculties", new SortFacultiesCommand()); // just sort faculties
        commands.put("changeFaculty", new ChangeFacultyCommand());
        commands.put("redactFaculty", new RedactFaculty());
        commands.put("addFaculty", new AddFaculty());
        commands.put("createFaculty", new CreateFaculty());
        commands.put("actionWithEnrollees", new ActionsWithEnrollees()); // block and unblock, show enrollees list


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
