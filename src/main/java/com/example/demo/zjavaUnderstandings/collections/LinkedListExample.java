package com.example.demo.zjavaUnderstandings.collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;

class Book {
    int id, quantity;
    String name,author,publisher;

    public Book (int id, int quantity, String name, String author, String publisher) {
        this.id = id;
        this.quantity = quantity;
        this.author = author;
        this.name = name;
        this.publisher = publisher;
    }
}

public class LinkedListExample {
    public static void main(String[] args) throws JsonProcessingException {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Sreekar");
        linkedList.add("Rao");
        linkedList.add("Dugyala");
        linkedList.add("here");
        linkedList.add("tech");
        linkedList.add("wizard");

        linkedList.remove(3);
        System.out.println(linkedList);

        for(String element : linkedList) {
            System.out.println(element);
        }
        Book b1 = new Book(1, 100, "The Coders", "CodeMaestro", "freeCodeCamp");
        Book b2 = new Book(2,100,"The Coding Practices", "CodeMaestro", "freeCodeCamp");
        LinkedList<Book> bookList = new LinkedList<>();
        bookList.add(b1);
        bookList.add(b2);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(bookList));


    }
}
