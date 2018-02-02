package com.example.android.herobuilder_mvp.PathFinder.presenter;

import android.content.Context;

import com.example.android.herobuilder_mvp.MVP_Main;
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
    private MVP_Main.ProvidedModelOps mModel;

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
