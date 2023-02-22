package io.ia;

public class Main {
    public static void main(String[] args) {
        Request.printGreeting();
        Request.printInstructions();

        boolean inGame = true;
        while (inGame) {
            Request req = new Request();
            System.out.println(req);
            if (req.getNumber() == 0) {
                inGame = false;
            } else {
                if (req.getCount() == 0 && req.getRequiredProperties().isEmpty() && req.getExcludedProperties().isEmpty()) {
                    Number n = new Number(req.getNumber());
                    n.printPropertiesMultiline();
                } else if (req.getRequiredProperties().isEmpty() && req.getExcludedProperties().isEmpty()) {
                    Numbers numbers = new Numbers();
                    numbers.addNumbersByLength(req.getNumber(), req.getCount());
                    numbers.printAllProperties();
                } else {
                    Numbers numbers = new Numbers();
                    numbers.addNumbersByProperties(req.getNumber(), req.getCount(), req.getRequiredProperties(), req.getExcludedProperties());
                    numbers.printAllProperties();
                }
            }
        }
    }
}