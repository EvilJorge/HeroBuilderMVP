package com.example.android.herobuilder_mvp.PathFinder.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;
import com.example.android.herobuilder_mvp.R;

public class PathfinderActivity
        extends AppCompatActivity
        implements MVP_PathFinder.RequiredViewOps, AbilitiesFragment.OnAttributeUpdatedListener {

    private CharacterPagerAdapter mPFCharacterPagerAdapter;
    ViewPager mPFCharacterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathfinder);

        setupViews();
    }

    /**
     * Set up the Views.
     */

    private void setupViews(){
        // Setup the ViewPager Adapter
        mPFCharacterPagerAdapter =
                new CharacterPagerAdapter(this, getSupportFragmentManager());
        mPFCharacterViewPager = (ViewPager) findViewById(R.id.PFPager);
        mPFCharacterViewPager.setAdapter(mPFCharacterPagerAdapter);

        // Set up the TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPFCharacterViewPager);
    }

    /** RequiredViewOps Methods **/

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    /** OnAttributesUpdated Methods **/

    @Override
    public void onAttributeDecremented(String attribute){

    }

    @Override
    public void onAttributeIncremented(String attribute){

    }
}
