package com.example.android.herobuilder_mvp.PathFinder.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.herobuilder_mvp.R;

/**
 * Created by cpalomares on 1/5/2018.
 */

public class CharacterPagerAdapter extends FragmentPagerAdapter {
    private int NUM_PAGES = 8;

    Context mContext;

    // Fragment Pages
    Fragment abilitiesPage, racePage, classPage, skillsPage,
            featPage, equipmentPage, detailsPage, spellsPage;

    // Public Constructor
    public CharacterPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;

        // Initialize tab pages
        abilitiesPage = new AbilitiesFragment();
        racePage = new RaceFragment();
        classPage = new ClassFragment();
        skillsPage = new SkillsFragment();
        featPage = new FeatsFragment();
        equipmentPage = new EquipmentFragment();
        detailsPage = new DetailsFragment();
        spellsPage = new SpellsFragment();
    }

    @Override
    public int getCount(){
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position){
        // Return page based on position
        switch(position){
            case 0: return racePage;
            case 1: return classPage;
            case 2: return abilitiesPage;
            case 3: return skillsPage;
            case 4: return featPage;
            case 5: return equipmentPage;
            case 6: return detailsPage;
            default: return spellsPage;
        }
    }

    // Pull the label for each tab
    @Override
    public CharSequence getPageTitle(int position){
        // Generate label based on item position
        switch(position){
            case 0: return mContext.getString(R.string.race_tab_label);
            case 1: return mContext.getString(R.string.class_tab_label);
            case 2: return mContext.getString(R.string.abilities_tab_label);
            case 3: return mContext.getString(R.string.skills_tab_label);
            case 4: return mContext.getString(R.string.feats_tab_label);
            case 5: return mContext.getString(R.string.equipment_tab_label);
            case 6: return mContext.getString(R.string.details_tab_label);
            case 7: return mContext.getString(R.string.spells_tab_label);
            default:
                return null;
        }
    }
}
