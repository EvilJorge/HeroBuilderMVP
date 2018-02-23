package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.herobuilder_mvp.R;
import com.example.android.herobuilder_mvp.view.AttributeComboLabel;
import com.example.android.herobuilder_mvp.view.ScrollingNumberPicker;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.CHARISMA;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.CONSTITUTION;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.DEXTERITY;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.INTELLIGENCE;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.STRENGTH;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.WISDOM;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment
        implements ScrollingNumberPicker.OnValueChangedListener{

    // Callback listener to communicate with parent
    private AbilitiesPageListener mCallback;

    // Ability Views
    private ScrollingNumberPicker mStrengthPicker;
    private ScrollingNumberPicker mDexterityPicker;
    private ScrollingNumberPicker mConstitutionPicker;
    private ScrollingNumberPicker mIntelligencePicker;
    private ScrollingNumberPicker mWisdomPicker;
    private ScrollingNumberPicker mCharismaPicker;

    // Ability Modifier Views
    private TextView mStrengthModifierView;
    private TextView mDexterityModifierView;
    private TextView mConstitutionModifierView;
    private TextView mIntelligenceModifierView;
    private TextView mWisdomModifierView;
    private TextView mCharismaModifierView;

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
            mCallback = (AbilitiesPageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement AbilitiesPageListener");
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
        mStrengthModifierView = (TextView) view.findViewById(R.id.strength_modifier_textview);

        // Dexterity Attribute view
        mDexterityPicker = (ScrollingNumberPicker) view.findViewById(R.id.dexterity_value_picker);
        mDexterityPicker.setOnValueChangedListener(this);
        mDexterityModifierView = (TextView) view.findViewById(R.id.dexterity_modifier_textview);

        // Constitution Attribute view
        mConstitutionPicker = (ScrollingNumberPicker) view.findViewById(R.id.constitution_value_picker);
        mConstitutionPicker.setOnValueChangedListener(this);
        mConstitutionModifierView = (TextView) view.findViewById(R.id.constitution_modifier_textview);

        // Intelligence Attribute view
        mIntelligencePicker = (ScrollingNumberPicker) view.findViewById(R.id.intelligence_value_picker);
        mIntelligencePicker.setOnValueChangedListener(this);
        mIntelligenceModifierView = (TextView) view.findViewById(R.id.intelligence_modifier_textview);

        // Wisdom Attribute view
        mWisdomPicker = (ScrollingNumberPicker) view.findViewById(R.id.wisdom_value_picker);
        mWisdomPicker.setOnValueChangedListener(this);
        mWisdomModifierView = (TextView) view.findViewById(R.id.wisdom_modifier_textview);

        // Charisma Attribute view
        mCharismaPicker = (ScrollingNumberPicker) view.findViewById(R.id.charisma_value_picker);
        mCharismaPicker.setOnValueChangedListener(this);
        mCharismaModifierView = (TextView) view.findViewById(R.id.charisma_modifier_textview);

        loadAbilityValues();
        calculatePageFields();
    }

    /**
     * Initialize Ability values
     */
    public void loadAbilityValues(){
        // Load Strength Value
        mStrengthPicker.setValue(mCallback.getAbilityValue(STRENGTH));

        // Load Dexterity Value
        mDexterityPicker.setValue(mCallback.getAbilityValue(DEXTERITY));

        // Load Constitution Value
        mConstitutionPicker.setValue(mCallback.getAbilityValue(CONSTITUTION));

        // Load Intelligence Value
        mIntelligencePicker.setValue(mCallback.getAbilityValue(INTELLIGENCE));

        // Load Wisdom Value
        mWisdomPicker.setValue(mCallback.getAbilityValue(WISDOM));

        // Load Charisma Value
        mCharismaPicker.setValue(mCallback.getAbilityValue(CHARISMA));
    }

    /**
     * Calculate and populate the values of this page.
     */
    public void calculatePageFields(){
        int strModifier;
        int dexModifier;
        int conModifier;
        int intModifier;
        int wisModifier;
        int chaModifier;

        // Calculate and set Ability Modifiers
        strModifier = mCallback.getAbilityModifier(STRENGTH);
        mStrengthModifierView.setText(Integer.toString(strModifier));

        dexModifier = mCallback.getAbilityModifier(DEXTERITY);
        mDexterityModifierView.setText(Integer.toString(dexModifier));

        conModifier = mCallback.getAbilityModifier(CONSTITUTION);
        mConstitutionModifierView.setText(Integer.toString(conModifier));

        intModifier = mCallback.getAbilityModifier(INTELLIGENCE);
        mIntelligenceModifierView.setText(Integer.toString(intModifier));

        wisModifier = mCallback.getAbilityModifier(WISDOM);
        mWisdomModifierView.setText(Integer.toString(wisModifier));

        chaModifier = mCallback.getAbilityModifier(CHARISMA);
        mCharismaModifierView.setText(Integer.toString(chaModifier));
    }

    /** Click Handler **/

    @Override
    public void OnValueChanged(ScrollingNumberPicker snpicker, int value){
        // Callback to parent Activity to update data value

        switch(snpicker.getId()){
            case R.id.strength_value_picker:
                mCallback.onAbilityUpdated(STRENGTH, value);
                Log.d("DEBUG", STRENGTH + " value was changed.");
                break;
            case R.id.dexterity_value_picker:
                mCallback.onAbilityUpdated(DEXTERITY, value);
                Log.d("DEBUG", DEXTERITY + " value was changed.");
                break;
            case R.id.constitution_value_picker:
                mCallback.onAbilityUpdated(CONSTITUTION, value);
                break;
            case R.id.intelligence_value_picker:
                mCallback.onAbilityUpdated(INTELLIGENCE, value);
                break;
            case R.id.wisdom_value_picker:
                mCallback.onAbilityUpdated(WISDOM, value);
                break;
            case R.id.charisma_value_picker:
                mCallback.onAbilityUpdated(CHARISMA, value);
        }

    }

    /** Attribute Update Callback Interface **/

    interface AbilitiesPageListener {
        // Notify parent of ability update.
        void onAbilityUpdated(int ability, int value);

        // Get ability value from parent.
        int getAbilityValue(int ability);

        // Get ability modifier from parent.
        int getAbilityModifier(int ability);
    }
}
