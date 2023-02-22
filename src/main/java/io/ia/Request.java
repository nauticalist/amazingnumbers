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
    private List<Property> requiredProperties = new ArrayList<>();

    private List<Property> excludedProperties = new ArrayList<>();


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

    public List<Property> getRequiredProperties() {
        return this.requiredProperties;
    }

    public List<Property> getExcludedProperties() {
        return excludedProperties;
    }

    public void addProperty(Property p) {
        this.requiredProperties.add(p);
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
                    List<String> requestProps = Arrays.stream(propsArr)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());

                    try {
                        checkForUnknown(requestProps);
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    try {
                        checkMutuallyExclusive(requestProps);
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    List<String> excludedProps = new ArrayList<>();
                    List<String> includedProps = new ArrayList<>();

                    requestProps.stream()
                            .map(s -> {
                                if (s.startsWith("-")) {
                                    s = s.substring(1);
                                    excludedProps.add(s);
                                } else {
                                    includedProps.add(s);
                                };
                                return s;
                            })
                            .collect(Collectors.toList());

                    for (String prop:  includedProps) {
                        this.addProperty(Property.valueOf(prop));
                    }
                    if (!excludedProps.isEmpty()) {
                        for (String prop: excludedProps) {
                            this.excludedProperties.add(Property.valueOf(prop));
                        }
                    }
                }
                System.out.println();
                inLoop = false;
            }
        }
    }

    private void checkMutuallyExclusive(List<String> properties) throws InputException {
        for (int i = 0; i < properties.size(); i++) {
            String prop = properties.get(i);
            if (!prop.startsWith("-") && properties.contains(String.format("-%s", prop))) {
                String err = String.format("The request contains mutually exclusive properties: [%s, -%s]\nThere are no numbers with these properties.", prop, prop);
                throw new InputException(err);
            } else if (prop.startsWith("-") && properties.contains(prop.substring(1))) {
                String err = String.format("The request contains mutually exclusive properties: [%s, %s]\nThere are no numbers with these properties.", prop, prop.substring(1));
                throw new InputException(err);
            }
        }
        List<List<String>> mEProps = List.of(
                List.of("even", "odd"), List.of("square", "sunny"), List.of("duck", "spy"), List.of("happy", "sad"),
                List.of("-even", "-odd"), List.of("-square", "-sunny"), List.of("-duck", "-spy"), List.of("-happy", "-sad")
        );
        if (properties.size() > 1) {
            for (List<String> item : mEProps) {
                if (properties.containsAll(item)) {
                    String err = String.format("The request contains mutually exclusive properties: %s\nThere are no numbers with these properties", item.stream().map(String::toUpperCase).collect(Collectors.toList()));
                    throw new InputException(err);
                }
            }
        }
    }

    private void checkForUnknown(List<String> properties) throws InputException {
        List<String> availableProps = new ArrayList<>();
        Property.getNames().stream()
                .forEach(e -> {
                    availableProps.add(e);
                    availableProps.add(String.format("-%s", e));
                });
        List<String> unknownProps = new ArrayList<>();
        for (String prop: properties) {
            if (!availableProps.contains(prop)) unknownProps.add(prop.toUpperCase());
        }
        if (!unknownProps.isEmpty() && unknownProps.size() == 1) {
            throw new InputException(String.format("The property %s is wrong.\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n", unknownProps));
        } else if (!unknownProps.isEmpty()) {
            throw new InputException(String.format("The properties %s are wrong.\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n", unknownProps));
        }
    }

    public static void printGreeting() {
        System.out.print("Welcome to Amazing Numbers!\n\n");
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    @Override
    public String toString() {
        return "Request{" +
                "number=" + number +
                ", count=" + count +
                ", requiredProperties=" + requiredProperties +
                ", excludedProperties=" + excludedProperties +
                '}';
    }
}