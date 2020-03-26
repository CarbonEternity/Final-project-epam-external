package ua.nure.popova.SummaryTask4.db.entity;

import java.util.Objects;

public class Faculty extends Entity implements Comparable<Faculty>{

    private static final long serialVersionUID = 1L;

    private String name;
    private int countBudget;
    private int countTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountBudget() {
        return countBudget;
    }

    public void setCountBudget(int countBudget) {
        this.countBudget = countBudget;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return getName().equals(faculty.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Faculty o) {
        return this.getName().compareTo(o.getName());
    }
}
