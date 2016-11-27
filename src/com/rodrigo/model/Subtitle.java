package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.Collections;
import java.util.List;

/**
 * Created by rodrigo on 27/11/16.
 */
public class Subtitle implements Printable {

    private String prefix;
    private String title;

    public Subtitle(String title) {
        this.prefix = "";
        this.title = title;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public int getLineCount() {
        return 1;
    }

    @Override
    public List<String> print() {
        return Collections.singletonList(String.format("%s %s", prefix, title));
    }
}
