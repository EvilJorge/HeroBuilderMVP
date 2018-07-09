package com.example.android.herobuilder_mvp.PathFinder.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.android.herobuilder_mvp.R;

import java.util.ArrayList;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.DWARF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.ELF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.GNOME;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFELF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFLING;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFORC;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HUMAN;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceFragment extends Fragment
        implements AdapterView.OnItemSelectedListener {
    // Callback listener to communicate with parent
    private RacePageListener mCallback;

    // Spinner and AdapterView
    private Spinner mRaceSpinner;
    private ArrayAdapter<CharSequence> mRaceListAdapter;

    private ListView mRacialTraitsListView;
    private ArrayAdapter<String> mRacialTraitsListAdapter;

    public RaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_race, container, false);

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
            mCallback = (RacePageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RacePageListener");
        }
    }

    /**
     * Set up views.
     */

    private void setupViews(View view){
        // Initialize Spinner
        mRaceSpinner = (Spinner) view.findViewById(R.id.race_spinner);
        mRaceSpinner.setOnItemSelectedListener(this);

        // Initialize ArrayAdapter with race options
        mRaceListAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.race_options, android.R.layout.simple_spinner_item);
        mRaceSpinner.setAdapter(mRaceListAdapter);

        // Set OnItemSelectedListener
        mRaceSpinner.setOnItemSelectedListener(this);

        mRacialTraitsListView = view.findViewById(R.id.racial_traits_listview);

        loadRacialTraits();
    }

    /**
     * Populate Racial Traits List
     */

    public void loadRacialTraits(){
        ArrayList<String> traitsList = mCallback.getRacialTraits();
        int raceSpinnerPosition = mCallback.getRace();

        mRaceSpinner.setSelection(raceSpinnerPosition);

        if(mRacialTraitsListAdapter == null){
            mRacialTraitsListAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_racial_trait, traitsList);
        } else {
            mRacialTraitsListAdapter.clear();
            mRacialTraitsListAdapter.addAll(traitsList);
            mRacialTraitsListAdapter.notifyDataSetChanged();
        }
        mRacialTraitsListView.setAdapter(mRacialTraitsListAdapter);
    }

    /**
     *  Item Selected Handler
     */

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
//        ArrayList<String> traitsList;

        // Populate Racial Trait list based on race selected.
        switch(position){
            case 0: mCallback.onRaceSelected(DWARF);
                break;
            case 1: mCallback.onRaceSelected(ELF);
                break;
            case 2: mCallback.onRaceSelected(GNOME);
                break;
            case 3: mCallback.onRaceSelected(HALFELF);
                break;
            case 4: mCallback.onRaceSelected(HALFORC);
                break;
            case 5: mCallback.onRaceSelected(HALFLING);
                break;
            default: mCallback.onRaceSelected(HUMAN);
        }

        loadRacialTraits();
//        traitsList = mCallback.getRacialTraits();
//
//        if(mRacialTraitsListAdapter == null){
//            mRacialTraitsListAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_racial_trait, traitsList);
//            mRacialTraitsListView.setAdapter(mRacialTraitsListAdapter);
//        } else {
//            mRacialTraitsListAdapter.clear();
//            mRacialTraitsListAdapter.addAll(traitsList);
//            mRacialTraitsListAdapter.notifyDataSetChanged();
//        }
    }

    public void onNothingSelected(AdapterView<?> parent){
        // Should never be called.
    }

    /** Race Update Callback Interface **/
    interface RacePageListener {
        // Notify parent of race selected
        void onRaceSelected(int race);

        // Return character's race
        int getRace();

        // Return list of racial abilities
        ArrayList<String> getRacialTraits();

    }
}
