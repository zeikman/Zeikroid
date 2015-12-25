package com.zeikman.zeikroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by zeikman on 12/25/15.
 *
 * Demonstrates how fragments can participate in the options menu.
 * http://www.java2s.com/Code/Android/UI/Demonstrateshowfragmentscanparticipateintheoptionsmenu.htm
 */
public class FragmentMenu extends Activity {
    Fragment mF1, mF2;
    CheckBox mCb1, mCb2;

    // Update fragment visibility when check boxes are changed
    final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateFragmentVisibility();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        // Make sure the two menu fragments are created
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        mF1 = fm.findFragmentByTag("f1");
        if (mF1 == null) {
            mF1 = new MenuFragment();
            ft.add(mF1, "f1");
        }
        mF2 = fm.findFragmentByTag("f2");
        if (mF2 == null) {
            mF2 = new Menu2Fragment();
            ft.add(mF2, "f2");
        }
        ft.commit();

        // Watch check box clicks
        mCb1 = (CheckBox) findViewById(R.id.f_menu1);
        mCb2 = (CheckBox) findViewById(R.id.f_menu2);
        mCb1.setOnClickListener(mClickListener);
        mCb2.setOnClickListener(mClickListener);
        
        // Make sure fragments start out with correct visibility
        updateFragmentVisibility();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Make sure fragments are updated after check box view state is restore
        updateFragmentVisibility();
    }

    // Update fragment visiblity based on current check box state
    private void updateFragmentVisibility() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (mCb1.isChecked()) ft.show(mF1);
        else ft.hide(mF1);
        if (mCb2.isChecked()) ft.show(mF2);
        else ft.hide(mF2);
        ft.commit();
    }

    /**
     * A fragment that displays a menu. This fragment happens to not
     * have a UI (it does not implement onCreateView, but it could also
     * have one if it wanted
     */
    public static class MenuFragment extends Fragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            //super.onCreateOptionsMenu(menu, inflater);
            menu.add("menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    /**
     * Second fragment with a menu
     */
    public static class Menu2Fragment extends Fragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            //super.onCreateOptionsMenu(menu, inflater);
            menu.add("menu 2").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }
}
