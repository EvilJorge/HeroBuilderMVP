package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.herobuilder_mvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment
        implements View.OnClickListener{

    // Attribute Constants
    private final static int STRENGTH = 1;
    private final static int DEXTERITY = 2;
    private final static int CONSTITUTION = 3;
    private final static int INTELLIGENCE = 4;
    private final static int WISDOM = 5;
    private final static int CHARISMA = 6;

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
        TextView abbrText;
        TextView labelText;
        //Button incrementAttribute;
        //Button decrementAttribute;

        // Strength Attribute view
        mStrengthView = (View) view.findViewById(R.id.strength);
        abbrText = (TextView) mStrengthView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mStrengthView.findViewById(R.id.attribute_label_textview);
        //incrementAttribute = (Button) mStrengthView.findViewById(R.id.attribute_increment_button);
        //decrementAttribute = (Button) mStrengthView.findViewById(R.id.attribute_decrement_button);
        abbrText.setText(R.string.strength_abbr_label);
        labelText.setText(R.string.strength_label);
        //incrementAttribute.setOnClickListener(this);
        //decrementAttribute.setOnClickListener(this);

        // Dexterity Attribute view
        mDexterityView = (View) view.findViewById(R.id.dexterity);
        abbrText = (TextView) mDexterityView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mDexterityView.findViewById(R.id.attribute_label_textview);
        abbrText.setText(R.string.dexterity_abbr_label);
        labelText.setText(R.string.dexterity_label);

        // Constitution Attribute view
        mConstitutionView = (View) view.findViewById(R.id.constitution);
        abbrText = (TextView) mConstitutionView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mConstitutionView.findViewById(R.id.attribute_label_textview);
        abbrText.setText(R.string.constitution_abbr_label);
        labelText.setText(R.string.constitution_label);

        // Intelligence Attribute view
        mIntelligenceView = (View) view.findViewById(R.id.intelligence);
        abbrText = (TextView) mIntelligenceView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mIntelligenceView.findViewById(R.id.attribute_label_textview);
        abbrText.setText(R.string.intelligence_abbr_label);
        labelText.setText(R.string.intelligence_label);

        // Wisdom Attribute view
        mWisdomView = (View) view.findViewById(R.id.wisdom);
        abbrText = (TextView) mWisdomView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mWisdomView.findViewById(R.id.attribute_label_textview);
        abbrText.setText(R.string.wisdom_abbr_label);
        labelText.setText(R.string.wisdom_label);

        // Charisma Attribute view
        mCharismaView = (View) view.findViewById(R.id.charisma);
        abbrText = (TextView) mCharismaView.findViewById(R.id.attribute_abbr_textview);
        labelText = (TextView) mCharismaView.findViewById(R.id.attribute_label_textview);
        abbrText.setText(R.string.charisma_abbr_label);
        labelText.setText(R.string.charisma_label);
    }

    /** Click Handler **/

    @Override
    public void onClick(View v){

    }

    /** Attribute Methods **/
    public void incrementAttribute(int attributeID){

    }

    /** Attribute Update Callback Interface **/

    public interface OnAttributeUpdatedListener{
        public void onAttributeDecremented(String Attribute);
        public void onAttributeIncremented(String Attribute);
    }
}
