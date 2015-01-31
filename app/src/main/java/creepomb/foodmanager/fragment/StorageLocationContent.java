package creepomb.foodmanager.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageLocationContent {

    public static List<StorageLocationItem> ITEMS = new ArrayList<StorageLocationItem>();

    public static Map<String, StorageLocationItem> ITEM_MAP = new HashMap<String, StorageLocationItem>();

    static {
        //Test
        //addItem(new StorageLocationItem("1", "冰箱"));
        //addItem(new StorageLocationItem("2", "零食櫃"));
    }

    public static void addItem(StorageLocationItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class StorageLocationItem {
        public String id;
        public String content;

        public StorageLocationItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
