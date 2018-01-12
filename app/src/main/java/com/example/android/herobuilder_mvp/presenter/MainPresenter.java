package com.example.android.herobuilder_mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.android.herobuilder_mvp.MVP_Main;
import com.example.android.herobuilder_mvp.PathFinder.view.PathfinderActivity;

import java.lang.ref.WeakReference;

/**
 * Created by cpalomares on 1/4/2018.
 */

public class MainPresenter
        implements MVP_Main.ProvidedPresenterOps, MVP_Main.RequiredPresenterOps{

    // View reference.  We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak.
    private WeakReference<MVP_Main.RequiredViewOps> mView;

    // Model reference
    private MVP_Main.ProvidedModelOps mModel;

    /**
     * Presenter Constructor
     * @param view MainActivity
     */
    public MainPresenter(MVP_Main.RequiredViewOps view){
        mView = new WeakReference<>(view);
    }

    /**
     * Return the View reference.
     * Throw an exception if the View is unavailable.
     */
    private MVP_Main.RequiredViewOps getView() throws NullPointerException{
        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("View is unavailable.");
    }

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
        } catch (NullPointerException e){
            return null;
        }
    }

    /**
     * Called by View when user clicks on the Pathfinder button.
     * Creates a new intent, then loads the PathFinder activity
     * from that intent.
     */
    @Override
    public void clickLoadPathfinder(){
        Context context = getActivityContext();
        Intent intent = new Intent(context, PathfinderActivity.class);
        context.startActivity(intent);
    }
}
