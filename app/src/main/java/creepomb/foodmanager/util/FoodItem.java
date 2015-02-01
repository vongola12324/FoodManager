package creepomb.foodmanager.util;

import android.database.Cursor;
import android.provider.CalendarContract;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by vongola12324 on 15/1/31.
 */
public class FoodItem {
    private long id = 0;
    private String name = "";
    private int amount = 0;
    private String unit = "";
    private int category = 0;
    private long outDated = 0;
    private int storedLoc = 0;


    public FoodItem(String name, int amount, String unit, int category, long outDated, int storedLoc){
        //this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.category = category;
        this.outDated = outDated;
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

    public static long getDateInMilli(int year, int month, int day){
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"), Locale.TAIWAN);
        gc.set(year, month, day);
        return gc.getTimeInMillis();
    }

    public static String getDisplay(long mills) {

        //http://stackoverflow.com/a/6782571
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(mills);

        return String.format("%04d年%02d月%02d日", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public long getOutDated() {
        return this.outDated;
    }

    public int getStoredLoc() {
        return storedLoc;
    }


}
