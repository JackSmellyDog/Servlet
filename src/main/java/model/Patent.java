package model;


import java.util.Calendar;

public class Patent extends Item {
    private String formula;
    private String essay;
    private Author author;
    private Calendar fillingDate;

    public String getFormula() {
        return formula;
    }

    public String getEssay() {
        return essay;
    }

    public Author getAuthor() {
        return author;
    }

    public Calendar getFillingDate() {
        return fillingDate;
    }

    public Patent(long id, String name, Author author, String formula, String essay, Calendar fillingDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.formula = formula;
        this.essay = essay;
        this.fillingDate = fillingDate;
    }

    @Override
    public String toString() {
        return String.format("%s; Author: %s; %s; %s ",
                super.toString(),
                author.getName(),
                formula,
                essay
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patent)) return false;
        if (!super.equals(o)) return false;

        Patent patent = (Patent) o;

        if (formula != null ? !formula.equals(patent.formula) : patent.formula != null) return false;
        return essay != null ? essay.equals(patent.essay) : patent.essay == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (formula != null ? formula.hashCode() : 0);
        result = 31 * result + (essay != null ? essay.hashCode() : 0);
        return result;
    }
}
