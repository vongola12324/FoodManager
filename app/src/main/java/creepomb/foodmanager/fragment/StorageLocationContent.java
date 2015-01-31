package creepomb.foodmanager.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import creepomb.foodmanager.util.StorageLocationItem;

public class StorageLocationContent {

    public static List<StorageLocationItem> ITEMS;

    //public static Map<String, StorageLocationItem> ITEM_MAP = new HashMap<String, StorageLocationItem>();

    static {
        //Test
        //addItem(new StorageLocationItem("1", "冰箱"));
        //addItem(new StorageLocationItem("2", "零食櫃"));
    }

    public static List<StorageLocationItem> readFormDB() {
        ITEMS = new ArrayList<StorageLocationItem>();
        addItem(new StorageLocationItem(-1, 0, "全部"));



        return ITEMS;
    }

    public static void addItem(StorageLocationItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

}
