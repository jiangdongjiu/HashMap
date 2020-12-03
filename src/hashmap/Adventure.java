package hashmap;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Adventure {
    private HashMap<StringKey, Item> map;

    public Adventure(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Invalid fileName.");
        }
        try{
            List<String> lines = Files.readAllLines(Paths.get((String)o), StandardCharsets.UTF_8);
            map = new HashMap<StringKey, Item>();
            for (String line:
                    lines) {
                String[] lineStringList = line.split("[,]", 0);
                String name = lineStringList[0].trim();
                int goldPieces = Integer.parseInt(lineStringList[1].trim());
                double weight = Double.parseDouble(lineStringList[2].trim());
                map.put(new StringKey(name), new Item(name, goldPieces, weight));
            }
        }catch (IOException e){
            throw new IllegalArgumentException("Invalid fileName.");
        }
    }

    public HashMap<StringKey, Item> getMap() {
        return map;
    }

    public String printLootMap() {
        String returnString = "";
        List<Item> itemList = new ArrayList<>();
        for (Iterator<Item> items = map.values(); items.hasNext(); ) {
            Item item = items.next();
            if (item.getGoldPieces() != 0){
                itemList.add(item);
            }
        }

        Collections.sort(itemList);
        for (Item item: itemList) {
            returnString += item.toString() + "\n";
        }
        return returnString;
    }
}
