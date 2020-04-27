package ua.nure.popova.SummaryTask4.db.entity;

/**
 * Discipline entity
 *
 * @author A.Popova
 */
public class Discipline extends Entity {

    private static final long serialVersionUID = 5692708766041889376L;

    private int mark;
    private String disciplineName;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
