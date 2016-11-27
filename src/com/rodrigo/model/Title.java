package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.ArrayList;
import java.util.List;

public class Title implements Printable {

    public String title;

    public Title(String title) {
        this.title = title;
    }

    @Override
    public int getLineCount() {
        return 15;
    }

    @Override
    public List<String> print() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lines.add("");
        }
        lines.add(String.format("%s", title));
        for (int i = 0; i < 8; i++) {
            lines.add("");
        }
        return lines;
    }
}
