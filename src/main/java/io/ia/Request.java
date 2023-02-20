package io.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Request {
    private static final Scanner input = new Scanner(System.in);
    private long number;
    private int count;
    private List<Property> properties = new ArrayList<>();


    public Request() {
        this.createRequest();
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void addProperty(Property p) {
        this.properties.add(p);
    }

    private long parseLong(String n) throws NumberFormatException{
        long num = Long.parseLong(n);
        if (num < 0) throw new NumberFormatException("Parameter should be a natural number.");
        return num;
    }

    private int parseInt(String n) throws NumberFormatException{
        int num = Integer.parseInt(n);
        if (num < 0) throw new NumberFormatException("Parameter should be a natural number.");
        return num;
    }

    private void createRequest() {
        boolean inLoop = true;
        while (inLoop) {
            System.out.println();
            System.out.println("Enter a request:");
            String txt = input.nextLine();
            if (txt.isBlank()) {
                printInstructions();
            } else {
                String[] entries = txt.trim().split(" ");

                if (entries.length >= 1 && entries[0] != null) {
                    try {
                        this.setNumber(parseLong(entries[0]));
                    } catch (NumberFormatException e) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }
                }

                if (entries.length >= 2 && entries[1] != null) {
                    try {
                        this.setCount(parseInt(entries[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }
                }

                if (entries.length >= 3) {
                    String[] propsArr = Arrays.copyOfRange(entries, 2, entries.length);
                    List<String> reqProps = Arrays.stream(propsArr).map(String::toLowerCase).collect(Collectors.toList());
                    if (reqProps.containsAll(List.of("even", "odd"))) {
                        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", reqProps.get(0).toUpperCase(), reqProps.get(1).toUpperCase());
                        continue;
                    } else if (reqProps.containsAll(List.of("square", "sunny"))) {
                        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", reqProps.get(0).toUpperCase(), reqProps.get(1).toUpperCase());
                        continue;
                    } else if (reqProps.containsAll(List.of("duck", "spy"))) {
                        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", reqProps.get(0).toUpperCase(), reqProps.get(1).toUpperCase());
                        continue;
                    } else if (reqProps.size() == 1 && !Property.getNames().contains(reqProps.get(0))) {
                        System.out.printf("The property [%s] is wrong.\nAvailable properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]\n", reqProps.get(0).toUpperCase());
                        continue;
                    } else if (reqProps.size() == 2 && !Property.getNames().contains(reqProps.get(0)) && !Property.getNames().contains(reqProps.get(1))) {
                        System.out.printf("The properties [%s, %s] are wrong.\nAvailable properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]\n ", reqProps.get(0).toUpperCase(), reqProps.get(1).toUpperCase());
                        continue;
                    }
                    for (String prop:  reqProps) {
                        if (!Property.getNames().contains(prop)) {
                            System.out.printf("The property [%s] is wrong.\nAvailable properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]\n", prop.toUpperCase());
                            break;
                        } else {
                            this.addProperty(Property.valueOf(prop));
                        }
                    }
                }
                System.out.println();
                inLoop = false;
            }
        }
    }

    public static void printGreeting() {
        System.out.print("Welcome to Amazing Numbers!\n\n");
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;\s
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    @Override
    public String toString() {
        return "Request{" +
                "number=" + number +
                ", count=" + count +
                ", properties=" + properties +
                '}';
    }
}
