package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 25/11/16.
 */
public class Paragraph implements Printable {

    private int lineCount;

    public Paragraph(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
    public int getLineCount() {
        return lineCount;
    }

    @Override
    public List<String> print() {
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= lineCount; i++) {
            lines.add(String.format("Lorem Ipsum %d", i));
        }
        return lines;
    }
}
