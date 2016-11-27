package com.rodrigo;

import com.rodrigo.model.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        // write your code here
        if (args.length < 2) {
            System.out.println("Voce precisa informar os arquivos de entrada e saida");
            return;
        }

        File in = new File(args[0]);
        try {
            BufferedReader reader = Files.newBufferedReader(in.toPath());
            Book book = new Book(reader);

            System.out.println(book.print());
        } catch (IOException e) {
            System.out.println("Não foi possivel abrir o arquivo de entrada");
        }
    }
}
