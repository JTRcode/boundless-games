package com.example.boundless.games.gpa_catcher_game.falling_objects;

import com.example.boundless.games.gpa_catcher_game.FallingObjectsEnum;

/**
 * Creates new falling objects
 */
public class FallingObjectFactory {

    /**
     *  those zones decides the chance of adding the corresponding objects
     *  only change the zones when having a different level of difficulty
     */
    private static double assignmentZone;
    private static double bombZone ;
    private static double sleepZone ;
    private static double clockZone;
    private static double generalZone;


    public FallingObjectFactory() {
    }

    static void setZones(double generalChance, double assignmentChance,
                  double bombChance, double sleepChance, double clockChance){
        generalZone = generalChance;
        assignmentZone = assignmentChance*generalZone;
        bombZone = assignmentZone+bombChance*generalZone;
        sleepZone = bombZone+sleepChance*generalZone;
        clockZone = sleepZone+clockChance*generalZone;
    }

    /**
     * Creates a new falling object
     *
     * @param objectEnum The enum of the falling object to create
     * @return The new falling object
     */
    public static FallingObject createFallingObject(FallingObjectsEnum objectEnum) {
        switch (objectEnum) {
            case BOMB:
                return new Bomb();
            case CLOCK:
                return new Clock();
            case ASSIGNMENT:
                return new Assignment();
            case SLEEP:
                return new Sleep();
            default:
                return null;
        }
    }
//    public FallingObject createFallingObject(int numItems, int maxItems) {
//
//        double addChance = 0;
//
//        if (numItems >= maxItems) return null;
//        else if (numItems==0)
//            addChance = 0.1;
//        else if (numItems==1)
//            addChance = 0.05;
//        else if (numItems==2)
//            addChance = 0.02;
//        else if(numItems==3)
//            addChance = 0.01;
//
//
//        double d = Math.random();
//
//        setZones(0.027,0.74,0.18,0.04,0.04);
////        if (d < 0.02 + addChance) return new Assignment();
////        else if (d < 0.025 + addChance) return new Bomb();
////        else if (d < 0.026 + addChance) return new Sleep();
////        else if(d < 0.027 + addChance) return new Clock();
//
//        if (d < assignmentZone+ addChance) return new Assignment();
//        else if (d < bombZone + addChance) return new Bomb();
//        else if (d < sleepZone + addChance) return new Sleep();
//        else if(d < clockZone + addChance) return new Clock();


      //  return null;
  //  }
}
