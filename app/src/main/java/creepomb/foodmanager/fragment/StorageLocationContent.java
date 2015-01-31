package creepomb.foodmanager.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.util.StorageLocationItem;

public class StorageLocationContent {

    private static List<StorageLocationItem> ITEMS;

    public static List<StorageLocationItem> readFormDB() {
        ITEMS = new ArrayList<StorageLocationItem>();
        addItem(new StorageLocationItem(-1, 0, "全部"));

        ITEMS.addAll(MainActivity.dbStorageLocationItemsProcess.getAll());

        return ITEMS;
    }
    public static void addItem(StorageLocationItem item) {
        ITEMS.add(item);
    }
}
