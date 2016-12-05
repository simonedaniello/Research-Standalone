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

    private CatalogueController() {
    }

    public static CatalogueController getInstance(){
        return instance;
    }


    public void createCatalogue(String nome, float prezzo, String proprietario,
                                String tipoArticolo,
                                     String editore, String autore, String titolo,
                                     String tipo, String marca, int taglia, String materia,
                                     int edizione, String modello, JList list1, JFrame frame) throws SQLException {

        DefaultListModel<String> model = new DefaultListModel<>();

        Article rq = null;

        System.out.println(prezzo);
        switch (tipoArticolo) {
            case "Book":
                rq = ArticleFactory.getInstance().getBook();
                rq.setNome(nome);
                rq.setPrezzo(prezzo);
                rq.setProprietario(proprietario);
                ((Book) rq).setEditore(editore);
                ((Book) rq).setAutore(autore);
                ((Book) rq).setTitolo(titolo);
                break;
            case "Electronics":
                rq = ArticleFactory.getInstance().getElectronics();
                rq.setNome(nome);
                rq.setPrezzo(prezzo);
                rq.setProprietario(proprietario);
                ((Electronics) rq).setTipo(tipo);
                ((Electronics) rq).setMarca(marca);
                ((Electronics) rq).setModello(modello);
                break;
            case "Clothing":
                rq = ArticleFactory.getInstance().getClothing();
                rq.setNome(nome);
                rq.setPrezzo(prezzo);
                rq.setProprietario(proprietario);
                ((Clothing) rq).setTipo(tipo);
                ((Clothing) rq).setTaglia(taglia);
                ((Clothing) rq).setMarca(marca);
                break;
            case "TextBook":
                rq = ArticleFactory.getInstance().getTextBook();
                rq.setNome(nome);
                rq.setPrezzo(prezzo);
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


        ArrayList<Article> articoli = getCatalogue(rq);
        System.out.println("il numero di articoli è : " + articoli.size());

        int i = 0;
        while (i<articoli.size()) {
            JLabel ciao = new JLabel();
            ciao.setText(articoli.get(i).getNome());
            System.out.println(articoli.get(i).getNome());
            model.addElement(articoli.get(i).getNome());
            i++;
        }
        list1.setModel(model);
        frame.setVisible(false);
        frame.setVisible(true);
    }

    private ArrayList<Article> getCatalogue(Article rq) throws SQLException {

        String sql = "";
        int isItTheFirst = 0;
        if (rq.getClass().equals(Book.class)) {

            sql = "SELECT * FROM ARTICLES.libro WHERE ";
            if (!((Book) rq).getTitolo().equals("")) {
                sql = sql + "TITOLO ='" + rq.getNome().replace("'", "'' ") + "' ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PROPRIETARIO ='" + rq.getProprietario().replace("'", "'' ") + "' ";
            }
            if (! rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "NOME ='" + rq.getNome().replace("'", "'' ") + "' ";
            }
            if (!((Book) rq).getAutore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "AUTORE ='" + ((Book) rq).getAutore().replace("'", "'' ") + "' ";
            }
            if (!((Book) rq).getEditore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "CASA ='" + ((Book) rq).getEditore().replace("'", "'' ") + "' ";
            }
            if(rq.getPrezzo() != 0){
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PREZZO <'" + rq.getPrezzo() + "' ";
            }
            /*if (((Book) rq).getPagine() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PAGINE ='" + rq.getPagine() + "' ";
            }*/

        } else if (rq.getClass().equals(Clothing.class)) {
            sql = "SELECT * FROM ARTICLES.Abbigliamento WHERE ";
            if (!((Clothing) rq).getTipo().equals("")) {
                sql = sql + "TIPO ='" + ((Clothing) rq).getTipo().replace("'", "'' ") + "' ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PROPRIETARIO ='" + rq.getProprietario().replace("'", "'' ") + "' ";
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "NOME ='" + rq.getNome().replace("'", "'' ") + "' ";
            }
            if (((Clothing) rq).getTaglia() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "TAGLIA ='" + ((Clothing) rq).getTaglia() + "' ";
            }
            if (!((Clothing) rq).getMarca().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "MARCA ='" + ((Clothing) rq).getMarca().replace("'", "'' ") + "' ";
            }
            if(rq.getPrezzo() != 0){
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PREZZO < '" + rq.getPrezzo() + "' ";
            }

        } else if (rq.getClass().equals(Electronics.class)) {
            sql = "SELECT * FROM ARTICLES.informatica WHERE ";
            System.out.println(((Electronics) rq).getModello());
            if (!((Electronics) rq).getTipo().equals("")) {
                sql = sql + "TIPO ='" + ((Electronics) rq).getTipo().replace("'", "'' ") + "' ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PROPRIETARIO ='" + rq.getProprietario().replace("'", "'' ") + "' ";
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "NOME ='" + rq.getNome().replace("'", "'' ") + "' ";
            }
            if (!((Electronics) rq).getModello().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "MODELLO ='" + ((Electronics) rq).getModello().replace("'", "'' ") + "' ";
            }
            if (!((Electronics) rq).getMarca().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "MARCA ='" + ((Electronics) rq).getMarca().replace("'", "'' ") + "' ";
            }
            if(rq.getPrezzo() != 0){
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PREZZO < '" + rq.getPrezzo() + "' ";
            }

        } else if (rq.getClass().equals(TextBook.class)) {
            sql = "SELECT * FROM ARTICLES.Scolastico WHERE ";
            if (!((TextBook) rq).getMateria().equals("")) {
                sql = sql + "MATERIA ='" + ((TextBook) rq).getMateria().replace("'", "'' ") + "' ";
                isItTheFirst++;
            }
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PROPRIETARIO ='" + rq.getProprietario().replace("'", "'' ") + "' ";
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "NOME ='" + rq.getNome() + "' ";
            }
            if (((TextBook) rq).getEdizione() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "EDIZIONE ='" + ((TextBook) rq).getEdizione() + "' ";
            }
            if(rq.getPrezzo() != 0){
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "PREZZO < '" + rq.getPrezzo() + "' ";
            }

        } else if (rq.getClass().equals(Article.class)) {
            sql = "SELECT * FROM ARTICLES.articolo WHERE NOME = '" + rq.getNome().replace("'", "'' ") + "' ";
            if(rq.getPrezzo() != 0){
                sql = sql + "AND PREZZO < '" + rq.getPrezzo() + "' ";
            }
        }

        System.out.println(sql);
        ArrayList<Article> articoli =  UserDatabase.getInstance().searchArticle(sql);

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
}
