package com.example.android.herobuilder_mvp.PathFinder.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;
import com.example.android.herobuilder_mvp.PathFinder.model.PathfinderModel;
import com.example.android.herobuilder_mvp.PathFinder.presenter.PathfinderPresenter;
import com.example.android.herobuilder_mvp.R;
import com.example.android.herobuilder_mvp.common.StateMaintainer;
import com.example.android.herobuilder_mvp.view.MainActivity;

import java.util.ArrayList;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.ABILITIES;

public class PathfinderActivity
        extends AppCompatActivity
        implements
        MVP_PathFinder.RequiredViewOps,
        AbilitiesFragment.AbilitiesPageListener,
        RaceFragment.RacePageListener {

    private MVP_PathFinder.ProvidedPresenterOps mPresenter;

    // Responsible for maintaining the object's integrity
    // during configuration change
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(getFragmentManager(), MainActivity.class.getName());

    // Pager and Adapter
    private CharacterPagerAdapter mPFCharacterPagerAdapter;
    private FragmentManager mPFFragmentManager;
    private ViewPager mPFCharacterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathfinder);

        setupViews();
        setupMVP();
    }

    /**
     * Set up the Views.
     */

    private void setupViews(){
        // Initialize Fragment Manager
        mPFFragmentManager = getSupportFragmentManager();

        // Setup the ViewPager Adapter
        mPFCharacterPagerAdapter =
                new CharacterPagerAdapter(this, mPFFragmentManager);
        mPFCharacterViewPager = (ViewPager) findViewById(R.id.PFPager);
        mPFCharacterViewPager.setAdapter(mPFCharacterPagerAdapter);

        // Set up the TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPFCharacterViewPager);
    }

    /**
     * Setup Model View Presenter pattern.
     * Use a {@link StateMaintainer} to maintain the
     * Presenter and Model instances between configuration changes.
     * Could be done differently with a dependency injection, but
     * for now, we'll use this.
     */
    private void setupMVP(){
        // Check if StateMaintainer has been created
        if(mStateMaintainer.firstTimeIn()){
            // Create the Presenter
            PathfinderPresenter presenter = new PathfinderPresenter(this);
            // Create the Model
            PathfinderModel model = new PathfinderModel(presenter);
            // Set Presenter model
            presenter.setModel(model);

            // Add Presenter and Model to StateMaintainer
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);

            // Set the Presenter as an interface
            // to limit the communication with it
            mPresenter = presenter;
        }
        // get the Presenter from StateMaintainer
        else{
            // Get the Presenter
            mPresenter = mStateMaintainer.get(PathfinderPresenter.class.getName());
            // Update the View in Presenter
            mPresenter.setView(this);
        }
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

    /**
     * Refresh values on Abilities Page.  Called by Pathfinder Presenter
     * when ability values are updated.
     */
    @Override
    public void refreshAbilitiesPage(){
        AbilitiesFragment abilitiesFragment = (AbilitiesFragment)
                mPFCharacterPagerAdapter.getItem(ABILITIES);

        Log.d("DEBUG", "Is abilitiesFragment null? " + (abilitiesFragment == null));
        if (abilitiesFragment != null){
            abilitiesFragment.calculatePageFields();
        }
    }

    /** RacePageListener Methods **/

    /**
     * Notification from Race Fragment that a race has been
     * selected
     * @param race Race that has been selected
     */
    @Override
    public void onRaceSelected(int race){
        mPresenter.setRace(race);
    }

    /**
     * Request to Presenter for Racial Traits
     * @return List of Racial Traits
     */
    @Override
    public ArrayList<String> getRacialTraits(){
        // Return list from Presenter
        return mPresenter.getRacialTraits();
    }

    /** AbilitiesPageListener Methods **/

    /**
     * Notification from Abilities Fragment of updated ability.
     * @param ability Ability updated
     * @param value New ability value
     */
    @Override
    public void onAbilityUpdated(int ability, int value){
        mPresenter.updateAbilityValue(ability, value);
    }

    /**
     * Notification from Abilities Fragment to increment ability by 1.
     * @param ability Ability to be incremented.
     */
    @Override
    public void onAbilityIncremented(int ability){
        mPresenter.incrementAbilityValue(ability);
    }

    /**
     * Notification from Abilities Fragment to decrement ability by 1.
     * @param ability Ability to be decremented.
     */
    @Override
    public void onAbilityDecremented(int ability){
        mPresenter.decrementAbilityValue(ability);
    }

    /**
     * Request from Abilities Fragment to retrieve Ability value
     * @param ability Ability value to retrieve
     * @return Ability value
     */
    @Override
    public int getAbilityValue(int ability){
        int value;

        value = mPresenter.getAbilityValue(ability);

        return value;
    }

    /**
     * Request from Abilities Fragment to retrieve Ability modifier
     * @param ability Ability modifier to retrieve
     * @return Ability modifier
     */
    @Override
    public int getAbilityModifier(int ability){
        int modifier;

        modifier = mPresenter.getAbilityModifier(ability);
        return modifier;
    }
}
