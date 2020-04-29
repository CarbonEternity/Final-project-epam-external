package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.dao.StatementDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;
import ua.nure.popova.SummaryTask4.web.util.SendMail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Command for calculate enrolleed students.
 */
public class CalculateEnrolleedStudentsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CalculateEnrolleedStudentsCommand.class);
    private static final long serialVersionUID = 34567876543L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
       LOG.info("Command started");

        Map<Faculty, List<Enrollee>> mapOfList = new StatementDAO().getStatement();
        UserDAO userDAO = new UserDAO();

        Map<Faculty, Double> facultyAverage = new HashMap<>();
        Map<Faculty, List<Enrollee>> mapEnrolled = new HashMap<>();


        mapOfList.forEach((k,v)->{
            if (!mapOfList.get(k).isEmpty()){
                List<Enrollee> fromStetementByFaculty = userDAO.findAllEnrolleesFromStatementByFacultyId(k.getId());
                List<Integer> allMarks = new ArrayList<>(findEnroleesMarks(fromStetementByFaculty, k.getId()));

                if(!allMarks.isEmpty()) {
                    double avg = calculateAverage(allMarks);
                    facultyAverage.put(k, avg);
                }
            }
        });



//enrollled
        if(!facultyAverage.isEmpty()){
            facultyAverage.forEach((k,v)-> {
                List<Enrollee> enrolled = calculateEnrolled(mapOfList.get(k), k, v);

                if(!enrolled.isEmpty()) {
                    mapEnrolled.put(k, enrolled);
                }
            });
        }



//not enrolled = just delete enrolled
        mapOfList.forEach((k,v)->{
            if(mapEnrolled.get(k)!=null) {
                v.removeAll(mapEnrolled.get(k));
            }
        });

        setResultOfCompetetion(mapOfList, false);
        setResultOfCompetetion(mapEnrolled, true);

        sendEmailToEnrolled(mapEnrolled, "invited");
        sendEmailToEnrolled(mapOfList, "not invited");


        new StatementDAO().removeStetement();
        LOG.info("Command finished. Statement removed");
        return Path.COMMAND_SHOW_RESULT;
    }

    private void sendEmailToEnrolled(Map<Faculty, List<Enrollee>> mapEnrolled, String message) {
        SendMail sendMail = new SendMail();

        mapEnrolled.forEach((k,v)-> {
            v.forEach(enrollee -> {
                sendMail.send("University entrance",
                        "You are "+message+" to study at the faculty of "+k.getName()+ "at karazin university!",
                        enrollee.getEmail());
            });

        });
    }

    private void setResultOfCompetetion(Map<Faculty, List<Enrollee>> resultOfCompetetion, boolean res){
        if (!resultOfCompetetion.isEmpty()) {
            new UserDAO().setResult(resultOfCompetetion, res);
        }
    }

    private List<Integer> findEnroleesMarks(List<Enrollee> fromStetementByFaculty, Long fac) {
        UserDAO userDAO = new UserDAO();
        List<Discipline> disciplines = new ArrayList<>();

        fromStetementByFaculty.forEach(x->{
            disciplines.addAll(userDAO.findZnoByEnroleeId(Math.toIntExact(x.getId()), Math.toIntExact(fac)));
        });

        List<Integer> marks = new ArrayList<>();

        disciplines.forEach(d->{
            marks.add(d.getMark());
        });

        return marks;
    }


    private double calculateAverage(List<Integer> marks){
        OptionalDouble average = marks.stream().mapToInt(Integer::intValue).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }


    private List<Enrollee> calculateEnrolled(List<Enrollee> enrollees, Faculty k, Double v){
        Map<Enrollee, List<Integer>> enrolleeAndMarks = new HashMap<>();

        enrollees.forEach(x-> {
            List<Integer> list = findEnroleesMarks(enrollees, k.getId());
            enrolleeAndMarks.put(x,list);
        });

        List<Enrollee> enrolled = new ArrayList<>();
        enrolleeAndMarks.forEach((enr, marks)->{
            if(calculateAverage(marks)>=v){
                enrolled.add(enr);
            }
        });

        return enrolled;
    }
}
