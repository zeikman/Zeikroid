package com.zeikman.zeikroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Android CardView Widget – Add Cards to a List using RecyclerView
 * - http://techlovejump.com/android-cardview-widget-example-add-to-list-using-recyclerview/
 *
 * Android CardView Example (card onClick listner)
 * - http://www.truiton.com/2015/03/android-cardview-example/
 */
public class ScrollingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static String LOG_TAG = "CardViewActivity";

    String[][] dataArray = new String[][] {
            { "Item 1", "Content 1" },
            { "Item 2", "Content 2" },
            { "Item 3", "Content 3" },
            { "Item 4", "Content 4" },
            { "Item 5", "Content 5" },
            { "Item 6", "Content 6" },
            { "Item 7", "Content 7" },
            { "Item 8", "Content 8" },
            { "Item 9", "Content 9" }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Change params:layout_scrollFlag programmatically in CollapsingToolbarLayout
        /*CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) ctl.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        ctl.setLayoutParams(params);*/

        //createCardView();

        recyclerView = (RecyclerView) findViewById(R.id.card_container);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //adapter = new RecyclerAdapter(dataArray);
        adapter = new RecyclerAdapter(getDataSet());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((RecyclerAdapter) adapter).setOnItemClickLisntener(new RecyclerAdapter
                .CardClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 20; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        return results;
    }

    private int dpToPixel(int dp) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        int px = Math.round(dp*(displayMetrics.xdpi/DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private int pixelToDp(int px) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        int dp = Math.round(px/(displayMetrics.xdpi/DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    private void createCardView() {
        ImageView photo = new ImageView(this);
        photo.setId(R.id.the_photo);
        Drawable d = getResources().getDrawable(android.R.drawable.ic_dialog_email);
        photo.setImageDrawable(d);
        RelativeLayout.LayoutParams photolp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        photolp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        photolp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        int photoRightMargin = dpToPixel(16);
        photolp.setMargins(0, 0, photoRightMargin, 0);
        photo.setLayoutParams(photolp);

        TextView title = new TextView(this);
        title.setId(R.id.the_title);
        RelativeLayout.LayoutParams titlelp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        titlelp.addRule(RelativeLayout.RIGHT_OF, photo.getId());
        titlelp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        title.setLayoutParams(titlelp);
        title.setTextSize(30);
        title.setText("Title 2");

        TextView desc = new TextView(this);
        RelativeLayout.LayoutParams desclp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        desclp.addRule(RelativeLayout.RIGHT_OF, photo.getId());
        desclp.addRule(RelativeLayout.BELOW, title.getId());
        desc.setLayoutParams(desclp);
        desc.setText("Content 2");

        RelativeLayout cardInner = new RelativeLayout(this);
        RelativeLayout.LayoutParams cardInnerlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        int cardInnerMargins = dpToPixel(16);
        cardInner.setPadding(cardInnerMargins, cardInnerMargins, cardInnerMargins, cardInnerMargins);
        cardInner.setLayoutParams(cardInnerlp);
        cardInner.addView(photo);
        cardInner.addView(title);
        cardInner.addView(desc);

        CardView card = new CardView(this, null, 0);
        card.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        ));
        card.addView(cardInner);

//        LinearLayout nsv = (LinearLayout) findViewById(R.id.cardview_container);
//        nsv.addView(card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent("com.zeikman.zeikroid.SETTINGS_ACTIVITY");
                startActivity(intent);
                break;
            case R.id.fragment_menu:
                Intent intent1 = new Intent("com.zeikman.zeikroid.FRAGMENT_MENU");
                startActivity(intent1);
                break;
            case R.id.action_exit:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return false;
    }
}
