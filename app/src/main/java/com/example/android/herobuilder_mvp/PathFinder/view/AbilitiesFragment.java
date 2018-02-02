package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.herobuilder_mvp.R;
import com.example.android.herobuilder_mvp.view.AttributeComboLabel;
import com.example.android.herobuilder_mvp.view.ScrollingNumberPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment
        implements ScrollingNumberPicker.OnValueChangedListener{

    // Attribute Constants
    private final static int STRENGTH = 1;
    private final static int DEXTERITY = 2;
    private final static int CONSTITUTION = 3;
    private final static int INTELLIGENCE = 4;
    private final static int WISDOM = 5;
    private final static int CHARISMA = 6;

    // Callback listener to communicate with parent
    private OnAttributeUpdatedListener mCallback;

    // Attribute Views
    private ScrollingNumberPicker mStrengthPicker;
    private ScrollingNumberPicker mDexterityPicker;
    private ScrollingNumberPicker mConstitutionPicker;
    private ScrollingNumberPicker mIntelligencePicker;
    private ScrollingNumberPicker mWisdomPicker;
    private ScrollingNumberPicker mCharismaPicker;

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
        AttributeComboLabel attributeLabelView;

        // Strength Attribute view
        mStrengthPicker = (ScrollingNumberPicker) view.findViewById(R.id.strength_value_picker);

        // Dexterity Attribute view
        mDexterityPicker = (ScrollingNumberPicker) view.findViewById(R.id.dexterity_value_picker);

        // Constitution Attribute view
        mConstitutionPicker = (ScrollingNumberPicker) view.findViewById(R.id.constitution_value_picker);

        // Intelligence Attribute view
        mIntelligencePicker = (ScrollingNumberPicker) view.findViewById(R.id.intelligence_value_picker);

        // Wisdom Attribute view
        mWisdomPicker = (ScrollingNumberPicker) view.findViewById(R.id.wisdom_value_picker);

        // Charisma Attribute view
        mCharismaPicker = (ScrollingNumberPicker) view.findViewById(R.id.charisma_value_picker);
    }

    /** Click Handler **/

    @Override
    public void OnValueChanged(ScrollingNumberPicker snpicker, int value){
        mCallback.onAttributeUpdated(value);
    }

    /** Attribute Update Callback Interface **/

    public interface OnAttributeUpdatedListener{
        public void onAttributeUpdated(int value);
    }
}
