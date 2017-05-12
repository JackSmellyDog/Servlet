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
}
