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
        numbers.add(num);
        length++;
    }

    public void addNumbersByLength(long start, int count) {
        for (int i =0; i < count; i++) {
            this.addNumber(start);
            start++;
        }
    }

    public void addNumbersByProperties(long start, int count, List<Property> props) {
        while (this.length < count) {
            Number n = new Number(start);
            if (n.getProperties().containsAll(props)){
                this.addNumber(start);
            }
            start++;
        }
    }

    public void printAllProperties() {
        for (int i = 0; i <  length; i++) {
            numbers.get(i).printPropertiesSingleLine();
        }
    }
}
