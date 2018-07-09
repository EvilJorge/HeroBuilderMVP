package com.example.android.herobuilder_mvp.PathFinder.model;

import static com.example.android.herobuilder_mvp.PathFinder.Constants.ELF;

/**
 * Created by cpalomares on 2/2/2018.
 */

public class PathfinderCharacter {
    // Container object for all attributes for Pathfinder Character

    public String characterName;

    public int characterRace;

    // Attributes
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;

    /** Constructors **/
    public PathfinderCharacter(){
        // Initialize Race
        characterRace = ELF;

        // Initialize attributes
        strength = 10;
        dexterity = 16;
        constitution = 10;
        intelligence = 10;
        wisdom = 10;
        charisma = 10;
    }

    public PathfinderCharacter(String name){
        this();
        characterName = name;
    }
}
