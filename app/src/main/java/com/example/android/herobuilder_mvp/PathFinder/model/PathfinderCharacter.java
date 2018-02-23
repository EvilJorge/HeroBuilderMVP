package com.example.android.herobuilder_mvp.PathFinder.model;

/**
 * Created by cpalomares on 2/2/2018.
 */

public class PathfinderCharacter {
    // Container object for all attributes for Pathfinder Character

    public String characterName;

    // Attributes
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;

    /** Constructors **/
    public PathfinderCharacter(){
        // Initialize attributes
        strength = 10;
        dexterity = 12;
        constitution = 13;
        intelligence = 14;
        wisdom = 15;
        charisma = 16;
    }

    public PathfinderCharacter(String name){
        this();
        characterName = name;
    }
}
