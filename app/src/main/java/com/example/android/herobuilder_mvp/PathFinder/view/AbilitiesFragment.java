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

    // Secondary Attribute Views
    private TextView mAttackBonusView;
    private TextView mMeleeAttackBonusView;
    private TextView mRangedAttackBonusView;

    private TextView mCombatManeuverBonusView;
    private TextView mCombatManeuverDefenseView;

    private TextView mArmorClassView;
    private TextView mTouchACView;
    private TextView mFlatFootedACView;

    private TextView mFortitudeSaveView;
    private TextView mReflexSaveView;
    private TextView mWillpowerSaveView;

    private TextView mInitiativeView;
    private TextView mHitPointsView;
    private TextView mSpeedView;
    private TextView mEncumbranceView;

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

        // Secondary Attribute views
        mAttackBonusView = (TextView) view.findViewById(R.id.attack_bonus_textview);
        mMeleeAttackBonusView = (TextView) view.findViewById(R.id.melee_attack_textview);
        mRangedAttackBonusView = (TextView) view.findViewById(R.id.ranged_attack_textview);

        mCombatManeuverBonusView = (TextView) view.findViewById(R.id.cmb_textview);
        mCombatManeuverDefenseView = (TextView) view.findViewById(R.id.cmd_textview);

        mArmorClassView = (TextView) view.findViewById(R.id.armor_class_textview);
        mTouchACView = (TextView) view.findViewById(R.id.touch_ac_textview);
        mFlatFootedACView = (TextView) view.findViewById(R.id.flatfooted_ac_textview);

        mFortitudeSaveView = (TextView) view.findViewById(R.id.fortitude_textview);
        mReflexSaveView = (TextView) view.findViewById(R.id.reflex_textview);
        mWillpowerSaveView = (TextView) view.findViewById(R.id.willpower_textview);

        mInitiativeView = (TextView) view.findViewById(R.id.initiative_textview);
        mHitPointsView = (TextView) view.findViewById(R.id.hit_point_textview);
        mSpeedView = (TextView) view.findViewById(R.id.speed_textview);
        mEncumbranceView = (TextView) view.findViewById(R.id.encumbrance_textview);

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
        // Ability Modifier value
        int strModifier;
        int dexModifier;
        int conModifier;
        int intModifier;
        int wisModifier;
        int chaModifier;

        /** Calculate and set Ability Modifiers **/

        strModifier = mCallback.getAbilityModifier(STRENGTH);
        mStrengthModifierView.setText(String.format(strModifier == 0 ? "%d" : "%+d", strModifier));

        dexModifier = mCallback.getAbilityModifier(DEXTERITY);
        mDexterityModifierView.setText(String.format(dexModifier == 0 ? "%d" : "%+d", dexModifier));

        conModifier = mCallback.getAbilityModifier(CONSTITUTION);
        mConstitutionModifierView.setText(String.format(conModifier == 0 ? "%d" : "%+d", conModifier));

        intModifier = mCallback.getAbilityModifier(INTELLIGENCE);
        mIntelligenceModifierView.setText(String.format(intModifier == 0 ? "%d" : "%+d", intModifier));

        wisModifier = mCallback.getAbilityModifier(WISDOM);
        mWisdomModifierView.setText(String.format(wisModifier == 0 ? "%d" : "%+d", wisModifier));

        chaModifier = mCallback.getAbilityModifier(CHARISMA);
        mCharismaModifierView.setText(String.format(chaModifier == 0 ? "%d" : "%+d", chaModifier));

        /** Populate Attack Bonus Fields **/

        mAttackBonusView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.attack_bonus_label),
                1
        ));
        mMeleeAttackBonusView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.melee_attack_label),
                1
        ));
        mRangedAttackBonusView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.ranged_attack_label),
                1
        ));

        /** Populate Combat Maneuver Bonus Fields **/

        mCombatManeuverBonusView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.cm_bonus_label),
                1
        ));
        mCombatManeuverDefenseView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.cm_defense_label),
                1
        ));

        /** Populate Armor Class Fields **/

        mArmorClassView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.armor_class_label),
                1
        ));
        mTouchACView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.touch_ac_label),
                1
        ));
        mFlatFootedACView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.flatfooted_ac_label),
                1
        ));

        /** Populate Defenses (Saving Throws) **/

        mFortitudeSaveView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.fortitude_label),
                1
        ));
        mReflexSaveView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.reflex_label),
                1
        ));
        mWillpowerSaveView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.willpower_label),
                1
        ));

        /** Populate Miscellaneous Attributes **/

        mInitiativeView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.initiative_label),
                1
        ));
        mHitPointsView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.hit_points_label),
                1
        ));
        mSpeedView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.speed_label),
                1
        ));
        mEncumbranceView.setText(formatSecondaryAttributeString(
                getResources().getString(R.string.encumbrance_label),
                1
        ));
    }

    /** Click Handler **/

    @Override
    public void OnValueChanged(ScrollingNumberPicker snpicker, int value){
        // Callback to parent Activity to update data value

        switch(snpicker.getId()){
            case R.id.strength_value_picker:
                mCallback.onAbilityUpdated(STRENGTH, value);
                break;
            case R.id.dexterity_value_picker:
                mCallback.onAbilityUpdated(DEXTERITY, value);
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

    @Override
    public void OnValueIncremented(ScrollingNumberPicker snpicker){
        // Callback to parent Activity to increment data value

        switch(snpicker.getId()){
            case R.id.strength_value_picker:
                mCallback.onAbilityIncremented(STRENGTH);
                break;
            case R.id.dexterity_value_picker:
                mCallback.onAbilityIncremented(DEXTERITY);
                break;
            case R.id.constitution_value_picker:
                mCallback.onAbilityIncremented(CONSTITUTION);
                break;
            case R.id.intelligence_value_picker:
                mCallback.onAbilityIncremented(INTELLIGENCE);
                break;
            case R.id.wisdom_value_picker:
                mCallback.onAbilityIncremented(WISDOM);
                break;
            case R.id.charisma_value_picker:
                mCallback.onAbilityIncremented(CHARISMA);
        }
    }

    @Override
    public void OnValueDecremented(ScrollingNumberPicker snpicker){
        // Callback to parent Activity to increment data value

        switch(snpicker.getId()){
            case R.id.strength_value_picker:
                mCallback.onAbilityDecremented(STRENGTH);
                break;
            case R.id.dexterity_value_picker:
                mCallback.onAbilityDecremented(DEXTERITY);
                break;
            case R.id.constitution_value_picker:
                mCallback.onAbilityDecremented(CONSTITUTION);
                break;
            case R.id.intelligence_value_picker:
                mCallback.onAbilityDecremented(INTELLIGENCE);
                break;
            case R.id.wisdom_value_picker:
                mCallback.onAbilityDecremented(WISDOM);
                break;
            case R.id.charisma_value_picker:
                mCallback.onAbilityDecremented(CHARISMA);
        }
    }

    /** Utility **/

    public String formatSecondaryAttributeString(String label, int value){
        return String.format(value == 0 ? "%-20s" + "%5d" : "%-20s" + "%+5d", label, value);
    }

    /** Attribute Update Callback Interface **/

    interface AbilitiesPageListener {
        // Notify parent of ability update.
        void onAbilityUpdated(int ability, int value);

        // Notify parent ability has been incremented by 1.
        void onAbilityIncremented(int ability);

        // Notify parent ability has been decremented by 1.
        void onAbilityDecremented(int ability);

        // Get ability value from parent.
        int getAbilityValue(int ability);

        // Get ability modifier from parent.
        int getAbilityModifier(int ability);
    }
}
