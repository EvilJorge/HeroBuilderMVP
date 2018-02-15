package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private OnAbilityUpdatedListener mCallback;

    // Ability Views
    private ScrollingNumberPicker mStrengthPicker;
    private ScrollingNumberPicker mDexterityPicker;
    private ScrollingNumberPicker mConstitutionPicker;
    private ScrollingNumberPicker mIntelligencePicker;
    private ScrollingNumberPicker mWisdomPicker;
    private ScrollingNumberPicker mCharismaPicker;

    // Ability Modifier Views
    private TextView mStrengthModifier;

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
            mCallback = (OnAbilityUpdatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnAbilityUpdatedListener");
        }
    }

    /**
     * Set up Views
     */

    private void setupViews(View view){
        AttributeComboLabel attributeLabelView;

        // Strength Attribute view
        mStrengthPicker = (ScrollingNumberPicker) view.findViewById(R.id.strength_value_picker);
        mStrengthPicker.setOnValueChangedListener(this);

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

    /**
     * Calculate and populate the values of this page.
     */
    public void calculatePageFields(){
        // Calculate Ability Modifiers

    }

    /** Click Handler **/

    @Override
    public void OnValueChanged(ScrollingNumberPicker snpicker, int value){
        // Callback to parent Activity to update data value
        switch(snpicker.getId()){
            case R.id.strength_value_picker:
                mCallback.onAbilityUpdated(STRENGTH, value);
                break;
        }

    }

    /** Attribute Update Callback Interface **/

    interface OnAbilityUpdatedListener {
        public void onAbilityUpdated(int ability, int value);
    }
}
