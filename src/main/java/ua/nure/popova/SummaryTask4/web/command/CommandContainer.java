package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.web.command.admin.*;
import ua.nure.popova.SummaryTask4.web.command.admin.blockUnblockEnrolees.LockUnlockEnrollee;
import ua.nure.popova.SummaryTask4.web.command.admin.competition.*;
import ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions.*;
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

        // client commands
        commands.put("sortFacultyList", new SortFacultiesForEnroleeCommand());
        commands.put("viewFacultyAndRequirements", new ShowRequirementsCommand());
        commands.put("createOrder", new ApplyForStudyCommand());
        commands.put("deleteApplication", new DeleteApplicationCommand());

        // admin commands
        commands.put("adminHome", new AdminHomeCommand());
        commands.put("actionWithFaculties", new ListFacultiesForAdminCommand()); //faculties with buttons
        commands.put("sortFaculties", new SortFacultiesCommand()); // just sort faculties
        commands.put("sortEnrolleeList", new SortEnrolleesCommand()); // just sort enrollees
        commands.put("changeFaculty", new DeleteOrEditFacultyCommand());
        commands.put("redactFaculty", new RedactFacultyCommand());
        commands.put("addFaculty", new AddFacultyCommand());
        commands.put("createFaculty", new UpdateFacultyAndDisciplines());

        commands.put("actionWithEnrollees", new LockUnlockEnrollee());

        commands.put("showFacultiesForCompetition", new ShowFacultiesForCompetition()); //list faculties with button *show applications*
        commands.put("showApplications", new ShowApplicationsCommand()); //list enrollees with select *show* and *add to competition*
        commands.put("competition", new ActionWithCompetitionCommand()); // actions *show profile* or *admit* enrollee
        commands.put("showEnrolee", new ShowEnroleeCommand()); // show enrollee profile
        commands.put("showStatement", new ShowStatementCommand()); // statement with button run competition
        commands.put("runCompetition", new CalculateEnrolleedStudentsCommand());
        commands.put("showResult", new ShowResultCommand());
        commands.put("showEnrolleeCertificate", new ShowEnrolleeCertificateCommand());


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
