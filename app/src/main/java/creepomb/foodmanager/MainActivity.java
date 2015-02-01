package creepomb.foodmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.*;
import android.support.v4.widget.DrawerLayout;
import android.database.sqlite.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import creepomb.foodmanager.db.DBCategoryProcess;
import creepomb.foodmanager.db.DBFoodItemsProcess;
import creepomb.foodmanager.fragment.AddStoreFoodFragment;
import creepomb.foodmanager.fragment.BaseFragment;
import creepomb.foodmanager.fragment.FoodListFragment;
import creepomb.foodmanager.fragment.StorageLocationFragment;

import creepomb.foodmanager.db.DBStorageLocationItemsProcess;
import creepomb.foodmanager.db.DBHelper;
import creepomb.foodmanager.util.FoodItem;
import creepomb.foodmanager.util.StorageLocationItem;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
                   //BaseFragment.OnFragmentInteractionListener {

    public static DBHelper helper;
    public static DBStorageLocationItemsProcess dbStorageLocationItemsProcess;
    public static DBFoodItemsProcess dbFoodItemsProcess;
    public static DBCategoryProcess dbCategoryProcess;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private int flag = 0;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        helper = new DBHelper(this);
        dbStorageLocationItemsProcess = new DBStorageLocationItemsProcess(helper);
        dbFoodItemsProcess = new DBFoodItemsProcess(helper);
        dbCategoryProcess = new DBCategoryProcess(helper);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        onNavigationDrawerItemSelected(newFragmentInstance(position + 1));
    }

    public void onNavigationDrawerItemSelected(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(flag == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            flag = 1;
        }
        else{
            fragmentManager.beginTransaction()
                    .addToBackStack(fragment.toString())/*Add this transaction to the back stack*/
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

    public Fragment newFragmentInstance(int number) {
        switch (number) {
            case 1:
                return StorageLocationFragment.newInstance(number);
            case 2:
                List<FoodItem> items = this.dbFoodItemsProcess.getAll();

                Collections.sort(items, new Comparator<FoodItem>() {
                    @Override
                    public int compare(FoodItem f1, FoodItem f2) {
                        long t1 = f1.getOutDated();
                        long t2 = f2.getOutDated();
                        if (t1 < t2) return -1;
                        else if (t1 > t2) return 1;
                        return 0;
                    }
                });

                return FoodListFragment.newInstance(2, items);

            case 3:
                return AddStoreFoodFragment.newInstance(number);
            default:
                return BaseFragment.newInstance(number);
        }
    }

    @Override
    public void onBackPressed() {

        if(mNavigationDrawerFragment.isDrawerOpen()) {
            mDrawerLayout.closeDrawers();
        }
//        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setMessage("是否離開?")
//                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            android.os.Process.killProcess(android.os.Process.myPid());
//                            System.exit(0);
//                        }
//                    })
//                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {}
//                    });
//            AlertDialog logout_dialog = builder.create();
//            logout_dialog.show();
//        }
        else if (getFragmentManager().getBackStackEntryCount() <=1) {
            super.onBackPressed();
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStackImmediate();
        }



    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 5:
                mTitle = getString(R.string.title_section5) + (FoodListFragment.titleName != "" ? " - " + FoodListFragment.titleName : "");
                break;
        }
        //BAD~~~
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(mTitle);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_import_food) {
            onNavigationDrawerItemSelected(2);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
/*
    @Override
    public void onFragmentInteraction(BaseFragment fragment, Uri uri) {
        System.out.println("Hello, world");
    }*/
}

