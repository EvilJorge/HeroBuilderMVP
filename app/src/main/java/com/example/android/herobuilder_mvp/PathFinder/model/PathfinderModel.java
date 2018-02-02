package com.example.android.herobuilder_mvp.PathFinder.model;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;

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
    }

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
    public int getAbilityValue(String ability){
        switch(ability){
            case "strength":
                return mPathfinderCharacter.strength;
            case "dexterity":
                return mPathfinderCharacter.dexterity;
            case "constitution":
                return mPathfinderCharacter.constitution;
            case "intelligence":
                return mPathfinderCharacter.intelligence;
            case "wisdom":
                return mPathfinderCharacter.wisdom;
            case "charisma":
                return mPathfinderCharacter.charisma;
            default:
                return 0;
        }
    }

    /**
     * Sets the value of an ability.
     * @param ability Name of ability
     * @param value value of ability
     */
    public void setAbilityValue(String ability, int value){
        switch(ability){
            case "strength":
                mPathfinderCharacter.strength = value;
            case "dexterity":
                mPathfinderCharacter.dexterity = value;
            case "constitution":
                mPathfinderCharacter.constitution = value;
            case "intelligence":
                mPathfinderCharacter.intelligence = value;
            case "wisdom":
                mPathfinderCharacter.wisdom = value;
            case "charisma":
                mPathfinderCharacter.charisma = value;
        }
    }

    /**
     * Gets the value of an ability.
     * @param ability Name of ability.
     */
    public int getAbilityModifier(String ability){
        int abilityValue;

        switch(ability){
            case "strength":
                abilityValue = mPathfinderCharacter.strength;
                break;
            case "dexterity":
                abilityValue = mPathfinderCharacter.dexterity;
                break;
            case "constitution":
                abilityValue = mPathfinderCharacter.constitution;
                break;
            case "intelligence":
                abilityValue = mPathfinderCharacter.intelligence;
                break;
            case "wisdom":
                abilityValue = mPathfinderCharacter.wisdom;
                break;
            case "charisma":
                abilityValue = mPathfinderCharacter.charisma;
                break;
            default:
                return 0;
        }

        return (abilityValue - 10) / 2;
    }
}
