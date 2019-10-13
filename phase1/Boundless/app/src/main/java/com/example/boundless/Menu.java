package com.example.boundless;

/**
 * The main menu, controls log in and starting games
 */
public class Menu {
    /**
     * The current user, null if not logged in
     */
    UserAccount user = null;

    /**
     * Manages sign in and sign up
     */
    UserAccountManager accountManager;

    /**
     * Manages game starting and resuming
     */
    GameDeployer gameDeployer;

    /**
     * Starts or resumes a game
     */
    public void playGame(boolean resume){
        //TODO
    }

    /**
     * Signs a user in given credentials
     *
     * @return If the sign in is successful
     */
    public boolean signIn(String name){
        //TODO
        return false;
    }

    /**
     * Signs a user up given credentials
     * @param name the username of the new account.
     */
    public void signUp(String name){
        //TODO
    }

}
