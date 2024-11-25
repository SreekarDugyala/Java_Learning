package com.example.demo.zjavaUnderstandings.collections;


// Array Lists are basically dynamic arrays. These are used when we do not know the size of array.


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@Slf4j
public class ArrayListExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("10");
        arrayList.add("20");
        arrayList.add("30");
        arrayList.add("40");
        arrayList.add("50");
        arrayList.add("Ravi");
        arrayList.add("Vijay");
        arrayList.add("Ajay");
        arrayList.add("Sreekar");

        System.out.println(arrayList);

        // Arraylist is Iterable

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            log.info("element is: " + iterator.next());
        }

        // Iterating through for each / enhanced for loop

        for (String value : arrayList) {
            log.info("elements is: " + value);
        }

        // Get and Set values in array list
        String a = arrayList.get(2);   // to fetch the element at index
        arrayList.set(2, "100");    // to set an element at index
        arrayList.remove("100");
        arrayList.remove("50");
        arrayList.remove("40");
        arrayList.remove("30");
        arrayList.remove("20");

        log.info(arrayList.toString());

        Collections.sort(arrayList);
        log.info(arrayList.toString());

        //Deserialize
        FileOutputStream fileOutputStream = new FileOutputStream("file.json");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(arrayList);
        fileOutputStream.close();
        objectOutputStream.close();

        //Serialize
        FileInputStream fileInputStream = new FileInputStream("file.json");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList list = (ArrayList) objectInputStream.readObject();
        System.out.println(list);
        objectInputStream.close();
        fileInputStream.close();

    }
}
