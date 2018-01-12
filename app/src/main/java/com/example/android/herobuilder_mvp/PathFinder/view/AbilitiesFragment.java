package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.herobuilder_mvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment {

    OnAttributeUpdatedListener mCallback;

    // Attribute Views
    private View mStrengthView;
    private View mDexterityView;
    private View mConstitutionView;
    private View mIntelligenceView;
    private View mWisdomView;
    private View mCharismaView;

    public AbilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_abilities, container, false);
        // Set Up Views
        setupViews(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        // This makes sure the container activity has implemented
        // the callback interface.  If not, it throws an exception
        try {
            mCallback = (OnAttributeUpdatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnAttributeUpdatedListener");
        }
    }

    /**
     * Set up Views
     */

    private void setupViews(View view){
        mStrengthView = (View) view.findViewById(R.id.strength);
        mDexterityView = (View) view.findViewById(R.id.dexterity);
        mConstitutionView = (View) view.findViewById(R.id.constitution);
        mIntelligenceView = (View) view.findViewById(R.id.intelligence);
        mWisdomView = (View) view.findViewById(R.id.wisdom);
        mCharismaView = (View) view.findViewById(R.id.charisma);
    }

    /** Attribute Update Callback Interface **/

    public interface OnAttributeUpdatedListener{
        public void onAttributeDecremented(String Attribute);
        public void onAttributeIncremented(String Attribute);
    }
}
