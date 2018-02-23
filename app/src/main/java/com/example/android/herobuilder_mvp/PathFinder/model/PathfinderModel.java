package com.example.android.herobuilder_mvp.PathFinder.model;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.CHARISMA;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.CONSTITUTION;
import static com.example.android.herobuilder_mvp.PathFinder.Constants.DEXTERITY;
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
     * Gets the value of an ability modifier.
     * @param ability Name of ability.
     */
    public int getAbilityModifier(int ability){
        int abilityValue;

        switch(ability){
            case STRENGTH:
                abilityValue = mPathfinderCharacter.strength;
                break;
            case DEXTERITY:
                abilityValue = mPathfinderCharacter.dexterity;
                break;
            case CONSTITUTION:
                abilityValue = mPathfinderCharacter.constitution;
                break;
            case INTELLIGENCE:
                abilityValue = mPathfinderCharacter.intelligence;
                break;
            case WISDOM:
                abilityValue = mPathfinderCharacter.wisdom;
                break;
            case CHARISMA:
                abilityValue = mPathfinderCharacter.charisma;
                break;
            default:
                return 0;
        }

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
