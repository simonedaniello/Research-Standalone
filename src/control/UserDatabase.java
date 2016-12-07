package control;


/**
 * @author dandi
 * <p>
 * Questa classe è stata sviluppata come un Singleton.
 * <p>
 */

class UserDatabase extends DatabaseController {

    //Singleton
    private static UserDatabase instance = new UserDatabase();

    private UserDatabase() {
    }

    static UserDatabase getInstance(){
        return instance;
    }


}
