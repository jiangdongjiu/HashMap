package hashmap;

import java.util.Objects;

public class StringKey implements Comparable<StringKey>{
    String keyName;

    public StringKey(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringKey stringKey = (StringKey) o;
        return Objects.equals(keyName, stringKey.keyName);
    }

    @Override
    public int hashCode() {
        char[] letters = keyName.toCharArray();
        int hashcode = 0;
        int coefficient = 31;

        for (int i = 0; i < letters.length; i++) {
            int ascii = (int)letters[i];
            int resultOfIntPow = intPow(coefficient, i);
            hashcode += ascii * resultOfIntPow;
        }
        return Math.abs(hashcode);
    }

    @Override
    public String toString() {
        return "KeyName: " + keyName + " HashCode: " + hashCode() ;
    }

    private int intPow(int number1, int number2) {
        int result = 1;
        for (int i = 0; i < number2; i++) {
            result = result * number1;
        }
        return result;
    }

    public String getKeyName() {
        return keyName;
    }

    public int compareTo(StringKey stringKey) {
        return this.keyName.compareTo(stringKey.keyName);
    }
}
