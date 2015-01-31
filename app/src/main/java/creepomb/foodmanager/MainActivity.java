package creepomb.foodmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.*;
import android.support.v4.widget.DrawerLayout;
import android.database.sqlite.*;

import creepomb.foodmanager.db.DBFoodItemsProcess;
import creepomb.foodmanager.fragment.AddStoreFoodFragment;
import creepomb.foodmanager.fragment.BaseFragment;
import creepomb.foodmanager.fragment.StorageLocationFragment;

import creepomb.foodmanager.db.DBStorageLocationItemsProcess;
import creepomb.foodmanager.db.DBHelper;
import creepomb.foodmanager.util.StorageLocationItem;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
<<<<<<< HEAD
    private int flag = 0;
=======

    public static DBHelper helper;
    public static DBStorageLocationItemsProcess dbStorageLocationItemsProcess;
    public static DBFoodItemsProcess dbFoodItemsProcess;

>>>>>>> 53b1b0f327fb27f245770f1ad7969d8bcb8a4dfe
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

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
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        helper = new DBHelper(this);
        dbStorageLocationItemsProcess = new DBStorageLocationItemsProcess(helper);
        dbFoodItemsProcess = new DBFoodItemsProcess(helper);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                 .replace(R.id.container, newFragmentInstance(position + 1))
                 .commit();
    }

    public Fragment newFragmentInstance(int number) {
        switch (number) {
            case 1:
                return StorageLocationFragment.newInstance(number);
            case 3:
                return AddStoreFoodFragment.newInstance(number);
            default:
                return BaseFragment.newInstance(number);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("是否離開?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                });
        AlertDialog logout_dialog = builder.create();
        logout_dialog.show();
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
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
        //BAD~~~
        getSupportActionBar().setTitle(mTitle);
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

}

