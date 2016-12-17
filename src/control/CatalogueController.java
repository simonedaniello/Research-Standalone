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

    public  DefaultListModel<String> createCatalogue(String nome,  String proprietario,
                                String tipoArticolo, String editore, String autore, String titolo,
                                     String tipo, String marca, int taglia, String materia,
                                     int edizione, String modello, DefaultListModel<String> model) throws SQLException {

        model.removeAllElements();
        Article rq = null;
        switch (tipoArticolo) {
            case "Book":
                rq = ArticleFactory.getInstance().getBook();

                rq.setNome(checkString(nome));
                rq.setProprietario(checkString(proprietario));
                ((Book)rq).setEditore(checkString(editore));
                ((Book)rq).setAutore(checkString(autore));
                ((Book)rq).setTitolo(checkString(titolo));

                break;
            case "Electronics":
                rq = ArticleFactory.getInstance().getElectronics();

                rq.setNome(checkString(nome));
                rq.setProprietario(checkString(proprietario));
                ((Electronics) rq).setTipo(checkString(tipo));
                ((Electronics) rq).setMarca(checkString(marca));
                ((Electronics) rq).setModello(checkString(modello));

                break;
            case "Clothing":
                rq = ArticleFactory.getInstance().getClothing();

                rq.setNome(checkString(nome));
                rq.setProprietario(checkString(proprietario));
                ((Clothing) rq).setTipo(checkString(tipo));
                ((Clothing) rq).setMarca(checkString(marca));
                if(taglia < 0)
                    ((Clothing) rq).setTaglia(0);
                else
                    ((Clothing) rq).setTaglia(taglia);

                break;
            case "TextBook":
                rq = ArticleFactory.getInstance().getTextBook();

                rq.setNome(checkString(nome));
                rq.setProprietario(checkString(proprietario));
                ((Book)rq).setEditore(checkString(editore));
                ((Book)rq).setAutore(checkString(autore));
                ((Book)rq).setTitolo(checkString(titolo));
                ((TextBook)rq).setMateria(checkString(materia));
                if(edizione < 0)
                    ((TextBook)rq).setEdizione(0);
                else
                    ((TextBook) rq).setEdizione(edizione);
                break;

            case "":
                rq = ArticleFactory.getInstance().getArticle();
                rq.setNome(checkString(nome));
                break;
        }


        articoli = getCatalogue(rq);
        System.out.println("il numero di articoli è : " + articoli.size());


        int i = 0;
        while (i<articoli.size()) {
            JLabel row = new JLabel();
            row.setText(articoli.get(i).getNome());
            model.addElement(articoli.get(i).getNome());

            System.out.println(articoli.get(i).getNome());
            i++;
        }
        return model;

    }

    private ArrayList<Article> getCatalogue(Article rq) throws SQLException {

        String sql = sqlCreator(rq);
        ArrayList<Article> articoli;
        if(!sqlCheck(sql)){
            articoli = new ArrayList<>();
            return articoli;
        }
        System.out.println(sql);
        if (rq.getClass().equals(Book.class)) {
            articoli = UserDatabase.getInstance().searchArticle(sql, "Book");
        } else if (rq.getClass().equals(Electronics.class)) {
            articoli = UserDatabase.getInstance().searchArticle(sql, "Electronics");
        } else if (rq.getClass().equals(Clothing.class)) {
            articoli = UserDatabase.getInstance().searchArticle(sql, "Clothing");
        } else if (rq.getClass().equals(TextBook.class)) {
            articoli = UserDatabase.getInstance().searchArticle(sql, "TextBook");
        }
        else {
            articoli = UserDatabase.getInstance().searchArticle(sql, "generic");
        }

        if (articoli.size() != 0){
            return articoli;
        }
        else{
            if(!rq.getNome().equals("")){
                return levenshteinCheck(rq.getNome());
            }
            else
                return articoli;
        }
    }

    public DefaultListModel<String> getArticleByPrice(int price, DefaultListModel<String> model) {
        model.removeAllElements();
        for (Article a : articoli) {
            if (a.getPrezzo() < price) {
                model.addElement(a.getNome());
                System.out.println("rimasto : " + a.getNome() + " con prezzo : " + a.getPrezzo());
            }
            else
                System.out.println("eliminato : " + a.getNome() + " con prezzo : " + a.getPrezzo());
        }
        return model;
    }

    private String checkString(String string) {
        String s;
        if(string.contains("''")){
            s = "";
            return s;
        }
        else
            return string.replace("'", "''");
    }

    private String sqlCreator(Article rq){
        int proprietario = 0;
        String sql = "";
        int isItTheFirst = 0;
        if (rq.getClass().equals(Book.class)) {

            sql = "SELECT * FROM ARTICLES.libro, ARTICLES.articolo WHERE ARTICLES.libro.NOME = ARTICLES.articolo.NOME AND ARTICLES.libro.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ";
            if (!((Book) rq).getTitolo().equals("")) {
                sql = sql + "UPPER(TITOLO) LIKE UPPER('%" + rq.getNome() + "%') ";
                isItTheFirst++;
            }
            if (!((Book) rq).getAutore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(AUTORE) LIKE UPPER('%" + ((Book) rq).getAutore() + "%') ";
                isItTheFirst++;
            }
            if (!((Book) rq).getEditore().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(CASA) LIKE UPPER('%" + ((Book) rq).getEditore() + "%') ";
                isItTheFirst++;
            }
            proprietario++;


        } else if (rq.getClass().equals(Clothing.class)) {
            sql = "SELECT * FROM ARTICLES.Abbigliamento, ARTICLES.articolo WHERE ARTICLES.Abbigliamento.NOME = ARTICLES.articolo.NOME AND ARTICLES.Abbigliamento.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ";
            if (!((Clothing) rq).getTipo().equals("")) {
                sql = sql + "UPPER(TIPO) LIKE UPPER('%" + ((Clothing) rq).getTipo() + "%') ";
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
                sql = sql + "UPPER(MARCA) LIKE UPPER('%" + ((Clothing) rq).getMarca()+ "%') ";
                isItTheFirst++;
            }
            proprietario++;

        } else if (rq.getClass().equals(Electronics.class)) {
            sql = "SELECT * FROM ARTICLES.informatica, ARTICLES.articolo WHERE ARTICLES.informatica.NOME = ARTICLES.articolo.NOME AND ARTICLES.informatica.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ";
            System.out.println(((Electronics) rq).getModello());
            if (!((Electronics) rq).getTipo().equals("")) {
                sql = sql + "UPPER(TIPO) LIKE UPPER('%" + ((Electronics) rq).getTipo() + "%') ";
                isItTheFirst++;
            }
            if (!((Electronics) rq).getModello().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(MODELLO) LIKE UPPER('%" + ((Electronics) rq).getModello() + "%') ";
                isItTheFirst++;
            }
            if (!((Electronics) rq).getMarca().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(MARCA) LIKE UPPER('%" + ((Electronics) rq).getMarca() + "%') ";
                isItTheFirst++;
            }
            proprietario++;

        } else if (rq.getClass().equals(TextBook.class)) {
            sql = "SELECT * FROM ARTICLES.Scolastico, ARTICLES.articolo WHERE ARTICLES.Scolastico.NOME = ARTICLES.articolo.NOME AND ARTICLES.Scolastico.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ";
            if (!((TextBook) rq).getMateria().equals("")) {
                sql = sql + "UPPER(MATERIA) LIKE UPPER('%" + ((TextBook) rq).getMateria()+ "%') ";
                isItTheFirst++;
            }

            if (((TextBook) rq).getEdizione() != 0) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "EDIZIONE ='" + ((TextBook) rq).getEdizione() + "' ";
                isItTheFirst++;
            }
            proprietario++;

        } else if (rq.getClass().equals(Article.class)) {
            sql = "SELECT * FROM ARTICLES.articolo WHERE UPPER(NOME) LIKE UPPER('%" + rq.getNome() + "%') ";
        }


        if (proprietario == 1){
            if (!rq.getProprietario().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(PROPRIETARIO) LIKE UPPER('%" + rq.getProprietario()+ "%') ";
                isItTheFirst++;
            }
            if (!rq.getNome().equals("")) {
                if (isItTheFirst != 0) {
                    sql = sql + "AND ";
                }
                sql = sql + "UPPER(NOME) LIKE UPPER('%" + rq.getNome() + "%') ";
            }
        }
        return sql;
    }

    private boolean sqlCheck(String sql){
        return !(sql.equals("SELECT * FROM ARTICLES.libro, ARTICLES.articolo WHERE ARTICLES.libro.NOME = ARTICLES.articolo.NOME AND ARTICLES.libro.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ") ||
                sql.equals("SELECT * FROM ARTICLES.Abbigliamento, ARTICLES.articolo WHERE ARTICLES.Abbigliamento.NOME = ARTICLES.articolo.NOME AND ARTICLES.Abbigliamento.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ") ||
                sql.equals("SELECT * FROM ARTICLES.informatica, ARTICLES.articolo WHERE ARTICLES.informatica.NOME = ARTICLES.articolo.NOME AND ARTICLES.informatica.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND ") ||
                sql.equals("SELECT * FROM ARTICLES.Scolastico, ARTICLES.articolo WHERE ARTICLES.Scolastico.NOME = ARTICLES.articolo.NOME AND ARTICLES.Scolastico.PROPRIETARIO = ARTICLES.articolo.PROPRIETARIO AND "));
    }

    private int levenshtein (CharSequence stringa1, CharSequence stringa2) {
        int len0 = stringa1.length() + 1;
        int len1 = stringa2.length() + 1;

        // array di distanze
        int[] valore1 = new int[len0];
        int[] valore2 = new int[len0];

        for (int i = 0; i < len0; i++) valore1[i] = i;

        for (int j = 1; j < len1; j++) {
            valore2[0] = j;

            for(int i = 1; i < len0; i++) {
                int match = (stringa1.charAt(i - 1) == stringa2.charAt(j - 1)) ? 0 : 1;

                int cost_replace = valore1[i - 1] + match;
                int cost_insert  = valore1[i] + 1;
                int cost_delete  = valore2[i - 1] + 1;

                valore2[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            int[] swap = valore1; valore1 = valore2; valore2 = swap;
        }

        return valore1[len0 - 1];
    }

    private ArrayList<Article> levenshteinCheck(String nome) throws SQLException {
        ArrayList<Article> articoliVicini = new ArrayList<>();
        String sql = "SELECT * FROM ARTICLES.articolo";
        articoli = UserDatabase.getInstance().searchArticle(sql, "generic");
        if(articoli.size() != 0){
            for (Article a : articoli) {
                if (levenshtein(a.getNome(), nome) < 4) {
                    articoliVicini.add(a);
                }
            }
            return articoliVicini;
        }
        else
            return articoli;
    }

}
