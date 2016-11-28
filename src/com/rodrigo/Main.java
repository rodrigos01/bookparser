package com.rodrigo;

import com.rodrigo.model.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        // write your code here
        if (args.length < 2) {
            System.out.println("Voce precisa informar os arquivos de entrada e saida");
            return;
        }

        System.out.print(String.format("Carregando arquivo %s...", args[0]));
        File in = new File(args[0]);
        try {
            BufferedReader reader = Files.newBufferedReader(in.toPath());
            System.out.println(" ok");
            System.out.print("Gerando a árvore...");
            Book book = new Book(reader);
            reader.close();
            System.out.println(" ok");
            System.out.println(String.format("  Capitulos...: %d", book.getChapterCount()));
            System.out.println(String.format("  Seções......: %d", book.getSectionCount()));
            System.out.println(String.format("  Subseções...: %d", book.getSubsectionCount()));
            System.out.println(String.format("  Parágrafos..: %d", book.getParagraphCount()));

            System.out.println("Gerando o sumário... ok");

            System.out.println(String.format("Imprimindo o livro para o arquivo %s... ok.", args[1]));
            printBook(args[1], book);
        } catch (IOException e) {
            System.out.println("Não foi possivel abrir o arquivo de entrada");
        }
    }

    private static void printBook(String arg, Book book) {
        String bookContent = book.print();
        try {
            FileOutputStream out = new FileOutputStream(arg);
            out.write(bookContent.getBytes());
            out.close();
        } catch (IOException e) {
            System.out.println("Não foi possivel abrir o arquivo de saida");
        }
    }
}
