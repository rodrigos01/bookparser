package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.List;

/**
 * Created by rodrigo on 25/11/16.
 */
public class SubSection implements Printable {

    public String title;

    private List<Paragraph> paragraphs;

    @Override
    public String print() {
        return null;
    }
}
