package com.example.android.herobuilder_mvp.PathFinder.model;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;
import com.example.android.herobuilder_mvp.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.CHARISMA;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.CONSTITUTION;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.DEXTERITY;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.DWARF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.ELF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.GNOME;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFELF;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFLING;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HALFORC;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.HUMAN;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.INTELLIGENCE;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.STRENGTH;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.WISDOM;

/**
 * Created by cpalomares on 2/2/2018.
 */

public class PathfinderModel
        implements MVP_PathFinder.ProvidedModelOps {

    // Presenter reference
    private MVP_PathFinder.RequiredPresenterOps mPresenter;

    // Character object
    private PathfinderCharacter mPathfinderCharacter;

    /**
     * Main constructor, called by Activity during MVP setup
     * @param presenter Presenter instance
     */
    public PathfinderModel(MVP_PathFinder.RequiredPresenterOps presenter){
        this.mPresenter = presenter;
        mPathfinderCharacter = new PathfinderCharacter();
    }

    /** Provided Model Ops **/

    /**
     * Gets the name of the character
     */
    public String getCharacterName(){
        return mPathfinderCharacter.characterName;
    }

    /**
     * Set the name of the character
     * @param name Character's name.
     */
    public void setCharacterName(String name){
        mPathfinderCharacter.characterName = name;
    }

    /** Race Operations **/

    /**
     * Sets the character's race
     * @param race Selected race
     */
    public void setRace(int race){
        mPathfinderCharacter.characterRace = race;
    }

    /**
     * Return the character's race
     * @return Race value.
     */
    public int getRace(){
        return mPathfinderCharacter.characterRace;
    }

    /**
     * Returns the list of Racial Traits based on the race of the character.
     * @return Racial Traits List
     */
    public ArrayList<String> getRacialTraits(){
        ArrayList<String> traitsList;

        switch(mPathfinderCharacter.characterRace){
            case DWARF: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.dwarf_racial_traits)));
                break;
            case ELF: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.elf_racial_traits)));
                break;
            case GNOME: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.gnome_racial_traits)));
                break;
            case HALFELF: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.halfelf_racial_traits)));
                break;
            case HALFORC: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.halforc_racial_traits)));
                break;
            case HALFLING: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.halfling_racial_traits)));
                break;
            case HUMAN:
                default: traitsList = new ArrayList<String>
                    (Arrays.asList(mPresenter.getAppContext().getResources().getStringArray(R.array.human_racial_traits)));
        }

        return traitsList;
    }

    /** Ability Operations **/

    /**
     * Gets the value of an ability.
     * @param ability Name of ability.
     */
    public int getAbilityValue(int ability){
        int value;

        switch(ability){
            case STRENGTH:
                value = mPathfinderCharacter.strength;
                break;
            case DEXTERITY:
                value = mPathfinderCharacter.dexterity;
                break;
            case CONSTITUTION:
                value = mPathfinderCharacter.constitution;
                break;
            case INTELLIGENCE:
                value = mPathfinderCharacter.intelligence;
                break;
            case WISDOM:
                value = mPathfinderCharacter.wisdom;
                break;
            case CHARISMA:
                value = mPathfinderCharacter.charisma;
                break;
            default:
                value = 0;
        }

        return value;
    }

    /**
     * Sets the value of an ability.
     * @param ability Name of ability
     * @param value value of ability
     */
    public void setAbilityValue(int ability, int value){
        switch(ability){
            case STRENGTH:
                mPathfinderCharacter.strength = value;
                break;
            case DEXTERITY:
                mPathfinderCharacter.dexterity = value;
                break;
            case CONSTITUTION:
                mPathfinderCharacter.constitution = value;
                break;
            case INTELLIGENCE:
                mPathfinderCharacter.intelligence = value;
                break;
            case WISDOM:
                mPathfinderCharacter.wisdom = value;
                break;
            case CHARISMA:
                mPathfinderCharacter.charisma = value;
        }
    }

    /**
     * Increments the value of an ability by 1.
     * @param ability Ability to increment.
     */
    public void incrementAbilityValue(int ability){
        switch(ability){
            case STRENGTH:
                mPathfinderCharacter.strength++;
                break;
            case DEXTERITY:
                mPathfinderCharacter.dexterity++;
                break;
            case CONSTITUTION:
                mPathfinderCharacter.constitution++;
                break;
            case INTELLIGENCE:
                mPathfinderCharacter.intelligence++;
                break;
            case WISDOM:
                mPathfinderCharacter.wisdom++;
                break;
            case CHARISMA:
                mPathfinderCharacter.charisma++;
        }
    }

    /**
     * Decrements the value of an ability by 1.
     * @param ability Ability to decrement.
     */
    public void decrementAbilityValue(int ability){
        switch(ability){
            case STRENGTH:
                mPathfinderCharacter.strength--;
                break;
            case DEXTERITY:
                mPathfinderCharacter.dexterity--;
                break;
            case CONSTITUTION:
                mPathfinderCharacter.constitution--;
                break;
            case INTELLIGENCE:
                mPathfinderCharacter.intelligence--;
                break;
            case WISDOM:
                mPathfinderCharacter.wisdom--;
                break;
            case CHARISMA:
                mPathfinderCharacter.charisma--;
        }
    }

    /**
     * Gets the value of an ability modifier.
     * @param ability Name of ability.
     */
    public int getAbilityModifier(int ability){
        int abilityValue;

        abilityValue = getAbilityValue(ability);

        return (abilityValue - 10) / 2;
    }

    /**
     * Loads character data from data storage
     * @return true with success
     */
    @Override
    public boolean loadData(){
        // This does nothing for now.
        return true;
    }
}
