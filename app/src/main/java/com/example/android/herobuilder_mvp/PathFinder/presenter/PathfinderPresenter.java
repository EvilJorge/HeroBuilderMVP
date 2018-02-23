package com.example.android.herobuilder_mvp.PathFinder.presenter;

import android.content.Context;
import android.util.Log;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;

import java.lang.ref.WeakReference;

/**
 * Created by cpalomares on 2/2/2018.
 */

public class PathfinderPresenter
        implements MVP_PathFinder.ProvidedPresenterOps, MVP_PathFinder.RequiredPresenterOps {

    // View reference.  We use as a WeakReference because
    // the Activity could be destroyed at any time and we
    // don't want to create a memory leak.
    private WeakReference<MVP_PathFinder.RequiredViewOps> mView;

    // Model reference.
    private MVP_PathFinder.ProvidedModelOps mModel;

    /**
     * Presenter Constructor
     * @param view PathfinderActivity
     */
    public PathfinderPresenter(MVP_PathFinder.RequiredViewOps view){
        mView = new WeakReference<>(view);
    }

    /**
     * Return the View reference.
     * Throw an exception if the View is unavailable.
     */
    private MVP_PathFinder.RequiredViewOps getView() throws NullPointerException{
        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("View is unavailable.");
    }

    /**
     * Called by View during the reconstruction events
     * @param view Activity instance
     */
    @Override
    public void setView(MVP_PathFinder.RequiredViewOps view){
        mView = new WeakReference<>(view);
    }

    /**
     * Called by Activity during MVP setup.  Only called once.
     * @param model Model instance
     */
    public void setModel(MVP_PathFinder.ProvidedModelOps model){
        mModel = model;

        // start to load data
        loadData();
    }

    /**
     * Load data from Model in an AsyncTask
     */
    private void loadData(){
        /** To be added. Will call loadData from Model Ops. **/
    }

    /** Provided Presenter Ops **/

    /**
     * Called by View when attribute picker value is updated.
     * @param ability Attribute to be updated.
     * @param value Value to be set on attribute.
     */
    @Override
    public void updateAbilityValue(int ability, int value){
        // Update value in Model.
        mModel.setAbilityValue(ability, value);

        Log.d("DEBUG", ability + " is being updated.");
        // Refresh Abilities Page
        getView().refreshAbilitiesPage();
    }

    /**
     * Called by View to retrieve Ability Value
     * @param ability Ability value to retrieve
     * @return Ability's value
     */
    @Override
    public int getAbilityValue(int ability){
        return mModel.getAbilityValue(ability);
    }

    /**
     * Called by View to calculate Ability Modifier
     * @param ability Ability modifier to retrieve
     * @return Ability's modifier
     */
    @Override
    public int getAbilityModifier(int ability){
        int modifier;

        modifier = mModel.getAbilityModifier(ability);

        return modifier;
    }

    /** Required Presenter Ops **/

    /**
     * @return Application context
     */
    @Override
    public Context getAppContext(){
        try {
            return getView().getAppContext();
        } catch (NullPointerException e){
            return null;
        }
    }

    /**
     * @return Activity context
     */
    @Override
    public Context getActivityContext(){
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
