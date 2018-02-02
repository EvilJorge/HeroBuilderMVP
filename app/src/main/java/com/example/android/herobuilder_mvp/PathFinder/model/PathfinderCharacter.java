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
        // Basic blank constructor
    }

    public PathfinderCharacter(String name){
        characterName = name;
    }
}
