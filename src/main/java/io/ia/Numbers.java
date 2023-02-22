package io.ia;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    private List<Number> numbers = new ArrayList<>();
    private int length = 0;

    public Numbers() {
    }

    public void addNumber(long n) {
        Number num = new Number(n);
        addNumber(num);
    }

    public void addNumber(Number num) {
        numbers.add(num);
        length++;
    }
    public void addNumbersByLength(long start, int count) {
        for (int i =0; i < count; i++) {
            this.addNumber(start);
            start++;
        }
    }

    public void addNumbersByProperties(long start, int count, List<Property> requiredProps, List<Property> excludedProperties) {
        while (this.length < count) {
            Number n = new Number(start);
            if (excludedProperties.isEmpty()) {
                if (n.getProperties().containsAll(requiredProps)) this.addNumber(n);
            } else {
                if (requiredProps.isEmpty()) {
                    if (!n.getProperties().containsAll(excludedProperties)) {
                        this.addNumber(n);
                    }
                } else {
                    if (n.getProperties().containsAll(requiredProps) && notContains(n, excludedProperties)) {
                        this.addNumber(n);
                    }
                }
            }
            start++;
        }
    }

    private boolean notContains(Number n, List<Property> excludedProperties) {
        for (Property prop: excludedProperties) {
            if (n.getProperties().contains(prop)) return false;
        }

        return true;
    }
    public void printAllProperties() {
        for (int i = 0; i <  length; i++) {
            numbers.get(i).printPropertiesSingleLine();
        }
    }
}
