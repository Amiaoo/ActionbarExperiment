package com.cornez.actionbarexperiment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MyActivity extends AppCompatActivity {

    private static final String TAB_KEY_INDEX = "tab_key";
    private Fragment breakfastFragment;
    private Fragment lunchFragment;
    private Fragment snackFragment;
    private Fragment billFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //SET THE ACTIONBAR
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(false);

        //CREATE THE TAB AND BIND THEM TO THE ACTIONBAR
        ActionBar.Tab appetizerTab = actionBar.newTab().setText(
                getString(R.string.ui_tabname_appetizer));
        ActionBar.Tab entreeTab = actionBar.newTab().setText(
                getString(R.string.ui_tabname_entree));
        ActionBar.Tab dessertTab = actionBar.newTab().setText(
                getString(R.string.ui_tabname_dessert));
        ActionBar.Tab billTab = actionBar.newTab().setText(
                getString(R.string.ui_tabname_bill));

        //CREATE EACH FRAGMENT AND BIND THEM TO THE ACTIONBAR
        breakfastFragment = new Appetizer();
        snackFragment = new Dessert();
        lunchFragment = new Entree();
        billFragment = new Bill();

        //SET LISTENER EVENTS FOR EACH OF THE ACTIONBAR TABS
        appetizerTab.setTabListener(new
                MyTabsListener(breakfastFragment,
                getApplicationContext()));
        dessertTab.setTabListener(new
                MyTabsListener(snackFragment,
                getApplicationContext()));
        entreeTab.setTabListener(new
                MyTabsListener(lunchFragment,
                getApplicationContext()));
        billTab.setTabListener(new
                MyTabsListener(billFragment,
                getApplicationContext()));

        //ADD EACH OF THE TABS TO THE ACTIONBAR
        actionBar.addTab(appetizerTab);
        actionBar.addTab(entreeTab);
        actionBar.addTab(dessertTab);
        actionBar.addTab(billTab);

        //RESTORE NAVIGATION
        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(
                    savedInstanceState.getInt(TAB_KEY_INDEX, 0));
        }
    }


    class MyTabsListener implements ActionBar.TabListener{
        public Fragment fragment;

        public MyTabsListener(Fragment f, Context context) {
            fragment = f;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab,
                                    FragmentTransaction ft){
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab,
                                  FragmentTransaction ft){
            ft.replace(R.id.fragment_container, fragment);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab,
                                     FragmentTransaction ft) {
            ft.remove(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       //Handle action bar item clicks here. The action bar will
       //automatically handle clicks on the Home/Up button, so long
       //as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();
      //   if (id == R.id.menuitem_search) {
      //     Toast.makeText(getApplicationContext(),"hjkhjkjhkj", Toast.LENGTH_LONG).show();
      //     return true;
      // }
       return super.onOptionsItemSelected(item);
   }
}


