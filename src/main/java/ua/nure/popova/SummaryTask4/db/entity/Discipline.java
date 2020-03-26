package ua.nure.popova.SummaryTask4.db.entity;

public class Discipline extends Entity {

    private static final long serialVersionUID = 5692708766041889376L;

    private int minMark;
    private String disciplineName;

    public int getMinMark() {
        return minMark;
    }

    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
