package com.apiexamples.examples;

import java.util.function.Consumer;

//import static java.util.stream.Nodes.collect;

public class A {
    public static void main(String[] args) {
     Consumer<String> dt= X-> System.out.println(X);
     dt.accept("mike");
     System.out.println(dt);

    }

}
