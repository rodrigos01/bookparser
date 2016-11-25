package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 25/11/16.
 */
public class Chapter implements Printable {

    private int number;
    private String title;
    private List<Printable> elements;

    public Chapter(String title) {
        this.number = 0;
        this.title = title;
        elements = new ArrayList<>();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addSection(Section section) {
        elements.add(section);
    }

    public void addParagraph(Paragraph paragraph) {
        elements.add(paragraph);
    }

    public List<Printable> getElements() {
        return elements;
    }

    @Override
    public String print() {
        return null;
    }
}
