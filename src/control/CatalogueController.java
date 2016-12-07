package control;

import entity.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dandi
 * <p>
 * Questa classe è stata sviluppata come un Singleton.
 * <p>
 */

public class CatalogueController {
    //Singleton
    private static CatalogueController instance = new CatalogueController();
    private ArrayList<Article> articoli;

    private CatalogueController() {
    }

    public static CatalogueController getInstance(){
        return instance;
    }


    public synchronized DefaultListModel<String> createCatalogue(String nome,  String proprietario,
                                String tipoArticolo,
                                     String editore, String autore, String titolo,
                                     String tipo, String marca, int taglia, String materia,
                                     int edizione, String modello, DefaultListModel<String> model) throws SQLException {

        model.removeAllElements();
        Article rq = null;
        switch (tipoArticolo) {
            case "Book":
                rq = ArticleFactory.getInstance().getBook();
                rq.setNome(nome);
                rq.setProprietario(proprietario);
                ((Book) rq).setEditore(editore);
                ((Book) rq).setAutore(autore);
                ((Book) rq).setTitolo(titolo);
                break;
            case "Electronics":
                rq = ArticleFactory.getInstance().getElectronics();
                rq.setNome(nome);
                rq.setProprietario(proprietario);
                ((Electronics) rq).setTipo(tipo);
                ((Electronics) rq).setMarca(marca);
                ((Electronics) rq).setModello(modello);
                break;
            case "Clothing":
                rq = ArticleFactory.getInstance().getClothing();
                rq.setNome(nome);
                rq.setProprietario(proprietario);
                ((Clothing) rq).setTipo(tipo);
                ((Clothing) rq).setTaglia(taglia);
                ((Clothing) rq).setMarca(marca);
                break;
            case "TextBook":
                rq = ArticleFactory.getInstance().getTextBook();
                rq.setNome(nome);
                rq.setProprietario(proprietario);
                ((Book) rq).setEditore(editore);
                ((Book) rq).setAutore(autore);
                ((Book) rq).setTitolo(titolo);
                ((TextBook) rq).setMateria(materia);
                ((TextBook) rq).setEdizione(edizione);
                break;
            case "":
                rq = ArticleFactory.getInstance().getArticle();
                rq.setNome(nome);
                break;
        }


        articoli = getCatalogue(rq);
        System.out.println("il numero di articoli è : " + articoli.size());


        int i = 0;
        while (i<articoli.size()) {
            JLabel ciao = new JLabel();
            ciao.setText(articoli.get(i).getNome());
            model.addElement(articoli.get(i).getNome());

            System.out.println(articoli.get(i).getNome());
            i++;
        }
        return model;

    }

    @SuppressWarnings("Duplicates")
    private ArrayList<Article> getCatalogue(Article rq) throws SQLException {

        String sql = "";
        int isItTheFirst = 0;
        if (rq.getClass().equals(Book.class)) {

            sql = "SELECT * FROM ARTICLES.libro WHERE ";
            if (!((Book) rq).getTitolo().equals("")) {
                sql = sql + "UPPER(TITOLO) LIKE UPPER('%" + rq.getNome().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(PROPRIETARIO) LIKE UPPER('%" + rq.getProprietario().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (! rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(NOME) LIKE UPPER('%" + rq.getNome().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!((Book) rq).getAutore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(AUTORE) LIKE UPPER('%" + ((Book) rq).getAutore().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!((Book) rq).getEditore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(CASA) LIKE UPPER('%" + ((Book) rq).getEditore().replace("'", "'' ") + "%') ";
            }


        } else if (rq.getClass().equals(Clothing.class)) {
            sql = "SELECT * FROM ARTICLES.Abbigliamento WHERE ";
            if (!((Clothing) rq).getTipo().equals("")) {
                sql = sql + "UPPER(TIPO) LIKE UPPER('%" + ((Clothing) rq).getTipo().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(PROPRIETARIO) LIKE UPPER('%" + rq.getProprietario().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(NOME) LIKE UPPER('%" + rq.getNome().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (((Clothing) rq).getTaglia() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "TAGLIA ='" + ((Clothing) rq).getTaglia() + "' ";
                isItTheFirst++;
            }
            if (!((Clothing) rq).getMarca().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(MARCA) LIKE UPPER('%" + ((Clothing) rq).getMarca().replace("'", "'' ") + "%') ";
            }

        } else if (rq.getClass().equals(Electronics.class)) {
            sql = "SELECT * FROM ARTICLES.informatica WHERE ";
            System.out.println(((Electronics) rq).getModello());
            if (!((Electronics) rq).getTipo().equals("")) {
                sql = sql + "UPPER(TIPO) LIKE UPPER('%" + ((Electronics) rq).getTipo().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(PROPRIETARIO) LIKE UPPER('%" + rq.getProprietario().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(NOME) LIKE UPPER('%" + rq.getNome().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!((Electronics) rq).getModello().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(MODELLO) LIKE UPPER('%" + ((Electronics) rq).getModello().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!((Electronics) rq).getMarca().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(MARCA) LIKE UPPER('%" + ((Electronics) rq).getMarca().replace("'", "'' ") + "%') ";
            }

        } else if (rq.getClass().equals(TextBook.class)) {
            sql = "SELECT * FROM ARTICLES.Scolastico WHERE ";
            if (!((TextBook) rq).getMateria().equals("")) {
                sql = sql + "UPPER(MATERIA) LIKE UPPER('%" + ((TextBook) rq).getMateria().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(PROPRIETARIO) LIKE UPPER('%" + rq.getProprietario().replace("'", "'' ") + "%') ";
                isItTheFirst++;
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(NOME) LIKE UPPER('%" + rq.getNome() + "%') ";
                isItTheFirst++;
            }
            if (((TextBook) rq).getEdizione() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "EDIZIONE ='" + ((TextBook) rq).getEdizione() + "' ";
            }


        } else if (rq.getClass().equals(Article.class)) {
            sql = "SELECT * FROM ARTICLES.articolo WHERE UPPER(NOME) LIKE UPPER('%" + rq.getNome().replace("'", "'' ") + "%') ";
        }

        System.out.println(sql);
        ArrayList<Article> articoli =  UserDatabase.getInstance().searchArticle(sql);
        for (int i = 0; i < articoli.size(); i++){
            if(rq.getClass().equals(Book.class)){
                sql = "SELECT * FROM ARTICLES.libro JOIN ARTICLES.articolo WHERE UPPER(ARTICLES.libro.NOME) LIKE UPPER('" + articoli.get(i).getNome() + "') AND UPPER(ARTICLES.libro.NOME) = UPPER(ARTICLES.libro.articolo)";
                UserDatabase.getInstance().extendedQuery(sql, "Book");
            }
            else if(rq.getClass().equals(Electronics.class)){

            }
            else if(rq.getClass().equals(Clothing.class)){

            }
            else if(rq.getClass().equals(TextBook.class)){

            }
        }

        if (articoli.size() != 0){
            return articoli;
        }
        else{
            if(!rq.getNome().equals("")){
                ArrayList<Article> articoliVicini = new ArrayList<>();
                sql = "SELECT * FROM ARTICLES.articolo";
                articoli = UserDatabase.getInstance().searchArticle(sql);
                if(articoli.size() != 0){
                    for (Article a : articoli) {
                        if (levenshtein(a.getNome(), rq.getNome()) < 3) {
                            articoliVicini.add(a);
                        }
                    }
                    return articoliVicini;
                }
                else
                    return articoli;
            }
            else
                return articoli;
        }
    }


    private int levenshtein (CharSequence stringa1, CharSequence stringa2) {
        int len0 = stringa1.length() + 1;
        int len1 = stringa2.length() + 1;

        // array di distanze
        int[] valore1 = new int[len0];
        int[] valore2 = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) valore1[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            valore2[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (stringa1.charAt(i - 1) == stringa2.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = valore1[i - 1] + match;
                int cost_insert  = valore1[i] + 1;
                int cost_delete  = valore2[i - 1] + 1;

                // keep minimum cost
                valore2[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = valore1; valore1 = valore2; valore2 = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return valore1[len0 - 1];
    }

    public synchronized DefaultListModel<String> getArticleByPrice(int price, DefaultListModel<String> model) {
        model.removeAllElements();
        for (Article a : articoli) {
            if (a.getPrezzo() < price) {
                model.addElement(a.getNome());
            }
        }
        return model;
    }
}
