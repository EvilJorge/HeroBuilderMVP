package com.example.android.herobuilder_mvp.PathFinder.view;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;
import com.example.android.herobuilder_mvp.PathFinder.model.PathfinderModel;
import com.example.android.herobuilder_mvp.PathFinder.presenter.PathfinderPresenter;
import com.example.android.herobuilder_mvp.R;
import com.example.android.herobuilder_mvp.common.StateMaintainer;
import com.example.android.herobuilder_mvp.presenter.MainPresenter;
import com.example.android.herobuilder_mvp.view.MainActivity;

public class PathfinderActivity
        extends AppCompatActivity
        implements MVP_PathFinder.RequiredViewOps, AbilitiesFragment.OnAbilityUpdatedListener {

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

            // Set the Presenter as an interface
            // to limit the communication with it
            mPresenter = presenter;
        }
        // get the Presenter from StateMaintainer
        else{
            // Get the Presenter
            mPresenter = mStateMaintainer.get(MainPresenter.class.getName());
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
                mPFFragmentManager.findFragmentById(R.id.abilities_fragment);

        if (abilitiesFragment != null){
            abilitiesFragment.calculatePageFields();
        }
    }

    /** OnAbilityUpdated Methods **/

    @Override
    public void onAbilityUpdated(int ability, int value){
        mPresenter.updateAbilityValue(ability, value);
    }
}
