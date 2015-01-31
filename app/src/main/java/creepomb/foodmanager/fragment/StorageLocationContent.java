package creepomb.foodmanager.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import creepomb.foodmanager.util.StorageLocationItem;

public class StorageLocationContent {

    private static List<StorageLocationItem> ITEMS;

    public static List<StorageLocationItem> readFormDB() {
        ITEMS = new ArrayList<StorageLocationItem>();
        addItem(new StorageLocationItem(-1, 0, "全部"));

        //Test
        addItem(new StorageLocationItem(1, 1, "冰箱"));
        addItem(new StorageLocationItem(2, 2, "零食櫃"));

        return ITEMS;
    }
    public static void addItem(StorageLocationItem item) {
        ITEMS.add(item);
    }
}
