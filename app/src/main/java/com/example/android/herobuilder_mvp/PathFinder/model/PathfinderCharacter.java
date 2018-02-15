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
        dexterity = 10;
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
