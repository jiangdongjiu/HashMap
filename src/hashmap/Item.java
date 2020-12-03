package hashmap;

import java.text.DecimalFormat;
import java.util.Objects;

public class Item implements Comparable<Item>{
    String name;
    int goldPieces;
    double weight;

    public Item(String name, int goldPieces, double weight) {
        this.name = name;
        this.goldPieces = goldPieces;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return goldPieces == item.goldPieces &&
                Double.compare(item.weight, weight) == 0 &&
                Objects.equals(name, item.name);
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##0.###");
        return name + " is worth " + goldPieces + "gp and " +
                "weighs " + decimalFormat.format(weight) + "kg";
    }

    public int compareTo(Item otherItem){
        return this.name.compareTo(otherItem.name);
    }

    public String getName() {
        return name;
    }

    public int getGoldPieces() {
        return goldPieces;
    }
    public double getWeight() {
        return weight;
    }
}
