package io.ia;

import java.util.ArrayList;
import java.util.List;

public class Number {
    private long num;
    private List<Property> properties = new ArrayList<>();

    public Number(long num) {
        this.num = num;
        calculateProperties();
    }
    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public boolean checkProperty(Property property) {
        return this.properties.contains(property);
    }

    private boolean isGapful() {
        String numStr = String.valueOf(num);
        if (numStr.length() < 3) return false;
        int divider = Integer.parseInt(String.format("%s%s", numStr.charAt(0), numStr.charAt(numStr.length() -1 )));
        return this.num % divider == 0;
    }

    private boolean isPalindrome() {
        if (this.num < 10) {
            return true;
        }
        String numStr = String.valueOf(this.num);
        int j = numStr.length() - 1;
        for (int i = 0; i < numStr.length() / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(j)) {
                return false;
            }
            j--;
        }
        return true;
    }

    private boolean isDuck() {
        String numStr = String.valueOf(this.num);
        return numStr.contains("0");
    }

    private boolean isEven() {
        return this.num % 2 == 0;
    }

    private boolean isBuzz() {
        boolean case1 = this.num % 7 == 0;
        boolean case2 = this.num % 10 == 7;
        return case1 || case2;
    }

    private boolean isSpy() {
        long product = 1;
        long sum = 0;
        long n = this.num;

        while ( n != 0 ) {
            product = product * (n % 10);
            sum = sum + (n % 10);
            n = n / 10;
        }

        return product == sum;
    }

    private boolean isSquare() {
        double n = Math.sqrt(this.num);
        return n - (int) n == 0;
    }

    private boolean isSunny() {
        this.num++;
        boolean c = isSquare();
        this.num--;
        return c;
    }

    private boolean isJumping() {
        if (this.num < 10) return true;

        String numStr = String.valueOf(this.num);
        for (int i = 0; i < numStr.length() - 1; i++) {
            int result= Character.getNumericValue(numStr.charAt(i)) - Character.getNumericValue(numStr.charAt(i + 1));
            if (result != 1 && result != -1) {
                return false;
            }
        }
        return true;
    }

    private boolean isHappy() {
        if (this.num == 1) return true;

        int sum = 0;
        long n = this.num;

        List<Long> calculatedNumbers = new ArrayList<>();

        while (sum != 1) {
            sum = 0;
            String nStr = String.valueOf(n);
            for (int i = 0; i < nStr.length(); i++) {
                int digit = Character.getNumericValue(nStr.charAt(i));
                sum += digit * digit;
            }
            n = sum;
            if (calculatedNumbers.contains(n)) return false;
            calculatedNumbers.add(n);
        }
        return true;
    }

    private void calculateProperties() {
        if (isBuzz()) {
            this.addProperty(Property.buzz);
        }
        if (isPalindrome()) {
            this.addProperty(Property.palindromic);
        }
        if (isDuck()) {
            this.addProperty(Property.duck);
        }
        if (isGapful()) {
            this.addProperty(Property.gapful);
        }
        if (isSpy()){
            this.addProperty(Property.spy);
        }
        if (isSquare()) {
            this.addProperty(Property.square);
        }
        if (isSunny()){
            this.addProperty(Property.sunny);
        }
        if (isJumping()) {
            this.addProperty(Property.jumping);
        }
        if (isEven()) {
            this.addProperty(Property.even);
        } else {
            this.addProperty(Property.odd);
        }
        if (isHappy()) {
            this.addProperty(Property.happy);
        } else {
            this.addProperty(Property.sad);
        }
    }

    public void printPropertiesMultiline() {
        StringBuilder txt = new StringBuilder(String.format("Properties of %d\n", this.num));
        for(Property p: Property.values()) {
            txt.append(String.format("%s: %b\n", p.name(), this.checkProperty(p)));
        }
        System.out.println(txt);
    }

    public void printPropertiesSingleLine() {
        StringBuilder txt = new StringBuilder(String.format("%d is ", this.num));
        for(int i = 0; i < properties.size(); i++) {
            txt.append(String.format("%s", properties.get(i).name()));
            if (i != properties.size() -1) {
                txt.append(", ");
            }
        }
        System.out.println(txt);
    }
}
