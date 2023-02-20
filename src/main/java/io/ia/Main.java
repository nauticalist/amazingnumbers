package io.ia;

public class Main {
    public static void main(String[] args) {
        Request.printGreeting();
        Request.printInstructions();

        boolean inGame = true;
        while (inGame) {
            Request req = new Request();
            if (req.getNumber() == 0) {
                inGame = false;
            } else {
                if (req.getCount() == 0 && req.getProperties().size() == 0) {
                    Number n = new Number(req.getNumber());
                    n.printPropertiesMultiline();
                } else if (req.getProperties().size() == 0) {
                    Numbers numbers = new Numbers();
                    numbers.addNumbersByLength(req.getNumber(), req.getCount());
                    numbers.printAllProperties();
                } else {
                    Numbers numbers = new Numbers();
                    numbers.addNumbersByProperties(req.getNumber(), req.getCount(), req.getProperties());
                    numbers.printAllProperties();
                }
            }
        }
    }
}