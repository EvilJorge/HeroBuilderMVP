package com.example.android.herobuilder_mvp.PathFinder.model;

import com.example.android.herobuilder_mvp.PathFinder.MVP_PathFinder;

/**
 * Created by cpalomares on 2/2/2018.
 */

public class PathfinderModel
        implements MVP_PathFinder.ProvidedModelOps {

    // Attribute Constants
    private final static int STRENGTH = 1;
    private final static int DEXTERITY = 2;
    private final static int CONSTITUTION = 3;
    private final static int INTELLIGENCE = 4;
    private final static int WISDOM = 5;
    private final static int CHARISMA = 6;

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
        switch(ability){
            case STRENGTH:
                return mPathfinderCharacter.strength;
            case DEXTERITY:
                return mPathfinderCharacter.dexterity;
            case CONSTITUTION:
                return mPathfinderCharacter.constitution;
            case INTELLIGENCE:
                return mPathfinderCharacter.intelligence;
            case WISDOM:
                return mPathfinderCharacter.wisdom;
            case CHARISMA:
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
    public void setAbilityValue(int ability, int value){
        switch(ability){
            case STRENGTH:
                mPathfinderCharacter.strength = value;
            case DEXTERITY:
                mPathfinderCharacter.dexterity = value;
            case CONSTITUTION:
                mPathfinderCharacter.constitution = value;
            case INTELLIGENCE:
                mPathfinderCharacter.intelligence = value;
            case WISDOM:
                mPathfinderCharacter.wisdom = value;
            case CHARISMA:
                mPathfinderCharacter.charisma = value;
        }
    }

    /**
     * Gets the value of an ability.
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
