package control;

import entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dandi
 * <p>
 * Questa classe è stata sviluppata come un Singleton.
 * Si occupa solamente di creare una interfaccia grafica per l'utente, prendere i dati inseriti
 * e passarli a un controller
 * <p>
 */

class ArticleFactory {

    //Singleton
    private static ArticleFactory instance = new ArticleFactory();

    private ArticleFactory() {
    }

    static ArticleFactory getInstance(){
        return instance;
    }

    Article getArticolo(String kind){
        switch (kind) {
            case "article":
                return getArticle();
            case "book":
                return getBook();
            case "clothing":
                return getClothing();
            case "electronics":
                return getElectronics();
            case "textBook" :
                return getTextBook();
            default:
                return null;
        }
    }

    private Article getArticle(){
        return new Article();
    }
    private Book getBook(){
        return new Book();
    }
    private Clothing getClothing(){
        return new Clothing();
    }
    private Electronics getElectronics(){
        return new Electronics();
    }
    private TextBook getTextBook(){
        return new TextBook();
    }

}
