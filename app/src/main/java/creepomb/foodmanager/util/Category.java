package creepomb.foodmanager.util;

/**
 * Created by vongola12324 on 15/1/31.
 */
public class Category {
    private long id = 0;
    private String name = "";

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public Category(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Category(Category ct){
        this.id = ct.id;
        this.name = ct.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
