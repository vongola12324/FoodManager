package creepomb.foodmanager;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by vongola12324 on 15/1/31.
 */
public class FoodItem {
    private long id = 0;
    private String name = "";
    private int amount = 0;
    private String unit = "";
    private int category = 0;
    private GregorianCalendar outDated = new GregorianCalendar();
    private int storedLoc = 0;


    public FoodItem(String name, int amount, String unit, int category, GregorianCalendar outDated, int storedLoc){
        //this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.category = category;
        this.outDated = (GregorianCalendar)outDated.clone();
        this.storedLoc = storedLoc;
    }

    public void setId(long id){
        this.id = id;
        return;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public int getCategory() {
        return category;
    }

    public static   GregorianCalendar packDate(long time){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(time);
        return gc;
    }

    public Date getOutDated() {
        return  (Date)outDated.clone();
    }

    public int getStoredLoc() {
        return storedLoc;
    }
}
