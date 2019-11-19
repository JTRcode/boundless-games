package com.example.boundless.users;

import com.example.boundless.games.GamesEnum;

/**
 * An account for a user
 */
public class UserAccount {
    private String name;
    private String password;
    private int pixelUnlocked = 0;
    private int tileUnlocked = 0;

    UserAccount(String name, String password) {
        this(name, password, 0, 0);
    }

    UserAccount(String name, String password, int pixelUnlocked, int tileUnlocked) {
        this.name = name;
        this.password = password;
        this.pixelUnlocked = pixelUnlocked;
        this.tileUnlocked = tileUnlocked;
    }

    /**
     * Check if the credentials match this user
     */
    boolean checkCreds(String name, String password) {
        return (this.name.equals(name) && this.password.equals(password));
    }

    /**
     * Check if the user has the same username as another user
     *
     * @param name The name to check if they are equal to
     * @return If the username is the same
     */
    boolean sameUsername(String name) {
        //TODO: show a toast saying that someone already has this username
        return (this.name.equals(name));
    }

    public int addUnlocked(GamesEnum game) {
        switch (game) {
            case PIXELS:
                pixelUnlocked++;
                break;
            case ROTATETILE:
                tileUnlocked++;
                break;
            default:
                break;
        }
        return getUnlocked(game);
    }

    public int getUnlocked(GamesEnum game) {
        switch (game) {
            case PIXELS:
                return pixelUnlocked;
            case ROTATETILE:
                return tileUnlocked;
            default:
                return 0;
        }
    }

    public String getName() {
        return name;
    }
}
