package com.example.android.herobuilder_mvp.common;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Retains and maintains object's state between configuration changes
 * in Activities and Fragments
 *
 * Created by Chris Palomares on 2/15/2018.
 *
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 *     framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 *     Dr. Douglas Schmidth</a>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 */

public class StateMaintainer {

    protected final String TAG = getClass().getSimpleName();

    private final String mStateMaintenerTag;
    private final WeakReference<FragmentManager> mFragmentManager;
    private StateMngFragment mStateMaintainerFrag;
    private boolean mIsRecreating;

    /**
     * Constructor
     */
    public StateMaintainer(FragmentManager fragmentManager, String stateMaintainerTag){
        mFragmentManager = new WeakReference<>(fragmentManager);
        mStateMaintenerTag = stateMaintainerTag;
    }

    /**
     * Creates the Fragment responsible to maintain the objects.
     * @return true: fragment just created
     */
    public boolean firstTimeIn(){
        try{
            // Retrieve the reference
            mStateMaintainerFrag = (StateMngFragment)
                    mFragmentManager.get().findFragmentByTag(mStateMaintenerTag);

            // Creating new RetainedFragment
            if (mStateMaintainerFrag == null){
                Log.d(TAG, "Creating new RetainedFragment " + mStateMaintenerTag);
                mStateMaintainerFrag = new StateMngFragment();
                mFragmentManager.get().beginTransaction()
                        .add(mStateMaintainerFrag, mStateMaintenerTag).commit();
                mIsRecreating = false;
                return true;
            } else {
                Log.d(TAG, "Returning retained existing fragment " + mStateMaintenerTag);
                mIsRecreating = true;
                return false;
            }
        } catch (NullPointerException e){
            Log.w(TAG, "Error firstTimeIn()");
            return false;
        }
    }

    /**
     * Return true if the current Activity was recreated at least one time
     * @return If the Activity was recreated
     */
    public boolean wasRecreated(){
        return mIsRecreating;
    }

    /**
     * Insert the object to be preserved.
     * @param key object's TAG
     * @param obj object to maintain
     */
    public void put(String key, Object obj){
        mStateMaintainerFrag.put(key, obj);
    }

    /**
     * Insert the object to be preserved.
     * @param obj object to maintain
     */
    public void put(Object obj){
        put(obj.getClass().getName(), obj);
    }

    /**
     * Recovers the object saved
     * @param key   Object's TAG
     * @param <T>   Object's type
     * @return      Object saved
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key){
        return mStateMaintainerFrag.get(key);
    }

    /**
     * Fragment responsible to preserve objects.
     * Instantiated only once.  Uses a hashmap to save objs
     */
    public static class StateMngFragment extends Fragment{
        private HashMap<String, Object> mData = new HashMap<>();

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            // Grants that the fragment will be preserved
            setRetainInstance(true);
        }

        /**
         * Insert objects on the hashmap
         * @param key   Reference key
         * @param obj   Object to be saved
         */
        public void put(String key, Object obj){
            mData.put(key, obj);
        }

        /**
         * Insert objects on the hashmap
         * @param object    Object to be saved
         */
        public void put(Object object){
            put(object.getClass().getName(), object);
        }

        /**
         * Recovers saved object
         * @param key   Reference key
         * @param <T>   Object type
         * @return      Object saved
         */
        @SuppressWarnings("unchecked")
        public <T> T get(String key){
            return (T) mData.get(key);
        }
    }
}
