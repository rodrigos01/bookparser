package com.rodrigo.model;

import com.rodrigo.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by rodrigo on 25/11/16.
 */
public class Book implements Printable {

    public String title;
    List<Chapter> chapters;

    public Book(String title) {
        this.title = title;
        chapters = new ArrayList<>();
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append("-------------------------------------\n");
        int line = 1;
        while (line < 7) {
            builder.append(String.format(Locale.getDefault(), "%d\n", line));
            line++;
        }
        builder.append(String.format(Locale.getDefault(), "%d    %s\n", line, title));
        while (line <= 15) {
            builder.append(String.format(Locale.getDefault(), "%d\n", line));
            line++;
        }
        for (int i = 1; i <= chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            chapter.setNumber(i);
            builder.append(chapter.print());
        }
        return builder.toString();
    }
}
