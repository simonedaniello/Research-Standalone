package control;

/**
 * Created by dandi on 08/02/17.
 */
public class DatabaseFactory {

    public static DatabaseFactory instance = new DatabaseFactory();

    private DatabaseFactory(){}

    public static DatabaseFactory getInstance(){
        return instance;
    }

    public DatabaseController getDatabase(String kind){

        switch (kind) {
            case "user":
                return new UserDatabase();
            case "corp":
                return new CorporateDB();
            default:
                return null;
        }
    }
}
