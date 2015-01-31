package creepomb.foodmanager.util;

public class StorageLocationItem {
    public int id;
    public int iconIndex;
    public String name;

    public StorageLocationItem(int id, int iconIndex, String name) {
        this.id = id;
        this.iconIndex = iconIndex;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
