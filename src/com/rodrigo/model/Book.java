package com.rodrigo.model;

import com.rodrigo.GenericTree;
import com.rodrigo.Printable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book extends GenericTree<Printable> {

    private static final String PREFIX_TITLE = "L";
    private static final String PREFIX_CHAPTER = "C";
    private static final String PREFIX_SECTION = "S";
    private static final String PREFIX_SUBSECTION = "SS";
    private static final String PREFIX_PARAGRAPH = "P";

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CHAPTER = 1;
    private static final int TYPE_SECTION = 2;
    private static final int TYPE_SUBSECTION = 3;
    private static final int TYPE_PARAGRAPH = 4;

    private int chapterCount;
    private int sectionCount;
    private int subsectionCount;
    private int paragraphCount;


    public Book(BufferedReader reader) throws IOException {
        Node currentParent = null;
        String line = reader.readLine();
        while (line != null) {
            Node node = getNodeForLine(line);
            if (currentParent == null) {
                currentParent = node;
                setRoot(currentParent);
            } else if (currentParent.type == node.type) {
                currentParent = (Node) currentParent.getParent();
                currentParent.addSubtree(node);
                currentParent = node;
            } else if (currentParent.type < node.type) {
                currentParent.addSubtree(node);
                currentParent = node;
            } else {
                while (currentParent.type >= node.type) {
                    currentParent = (Node) currentParent.getParent();
                }
                currentParent.addSubtree(node);
                currentParent = node;
            }
            line = reader.readLine();
        }
    }

    private Node getNodeForLine(String line) {
        int type = getTypeForPrefix(line.substring(0, 2).replace(" ", ""));
        String text = line.substring(2).replaceAll("^\\s", "");

        Printable element = null;
        switch (type) {
            case TYPE_TITLE:
                element = new Title(text);
                break;
            case TYPE_CHAPTER:
                chapterCount++;
                element = new Subtitle(text);
                break;
            case TYPE_SECTION:
                sectionCount++;
                element = new Subtitle(text);
                break;
            case TYPE_SUBSECTION:
                subsectionCount++;
                element = new Subtitle(text);
                break;
            case TYPE_PARAGRAPH:
                paragraphCount++;
                element = new Paragraph(Integer.parseInt(text));
                break;
        }
        return new Node(element, type);
    }

    private int getTypeForPrefix(String prefix) {
        switch (prefix) {
            case PREFIX_TITLE:
                return TYPE_TITLE;
            case PREFIX_CHAPTER:
                return TYPE_CHAPTER;
            case PREFIX_SECTION:
                return TYPE_SECTION;
            case PREFIX_SUBSECTION:
                return TYPE_SUBSECTION;
            case PREFIX_PARAGRAPH:
                return TYPE_PARAGRAPH;
        }
        return -1;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public int getSectionCount() {
        return sectionCount;
    }

    public int getSubsectionCount() {
        return subsectionCount;
    }

    public int getParagraphCount() {
        return paragraphCount;
    }

    public String print() {
        List<String> lines = print((Node) getRoot(), "");
        lines.addAll(printSummary((Node) getRoot(), ""));
        StringBuilder builder = new StringBuilder();
        int lineNumber = 1;
        int pageCount = -1;
        for (String line : lines) {
            if (lineNumber == 1) {
                builder.append("-------------------------------------");
                if (pageCount == 0) {
                    builder.append(" Capa\n");
                } else if (pageCount > 0) {
                    builder.append(String.format(" Pg. %d\n", pageCount));
                } else {
                    builder.append("\n");
                }
                pageCount++;
            }
            if (lineNumber < 10) {
                builder.append(String.format("%d   %s\n", lineNumber, line));
            } else {
                builder.append(String.format("%d  %s\n", lineNumber, line));
            }
            if (lineNumber == 15) {
                lineNumber = 1;
            } else {
                lineNumber++;
            }
        }
        return builder.toString();
    }

    private List<String> print(Node node, String prefix) {
        List<String> lines = new ArrayList<>();
        Printable element = node.getElement();
        if (element instanceof Subtitle) {
            ((Subtitle) element).setPrefix(prefix);
        }
        lines.addAll(element.print());
        int subtitleCount = 0;
        for (int i = 0; i < node.getSubtreeSize(); i++) {
            Node subtree = (Node) node.getSubtree(i);
            if (subtree.getElement() instanceof Subtitle) {
                subtitleCount++;
            }
            String subtreePrefix = String.format("%s%d.", prefix, subtitleCount);
            lines.addAll(print(subtree, subtreePrefix));
        }
        return lines;
    }

    private List<String> printSummary(Node node, String prefix) {
        List<String> lines = new ArrayList<>();
        Printable element = node.getElement();
        if (element instanceof Paragraph) {
            return lines;
        } else if (element instanceof Subtitle) {
            ((Subtitle) element).setPrefix(prefix);
            lines.addAll(element.print());
        }
        int subtitleCount = 0;
        for (int i = 0; i < node.getSubtreeSize(); i++) {
            Node subtree = (Node) node.getSubtree(i);
            if (subtree.getElement() instanceof Subtitle) {
                subtitleCount++;
            }
            String subtreePrefix = String.format("%s%d.", prefix, subtitleCount);
            lines.addAll(printSummary(subtree, subtreePrefix));
        }
        return lines;
    }

    private class Node extends GenericTree.Node<Printable> {

        private int type;

        Node(Printable element, int type) {
            super(element);
            this.type = type;
        }
    }
}
