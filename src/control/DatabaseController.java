package control;

import databaseINIT.Provider;
import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author dandi
 */

abstract class DatabaseController {

    private static Provider provider = new Provider();

    ArrayList<Article> searchArticle(String sql) throws SQLException {

        Article nuovoArticolo;
        ArrayList<Article> array = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stmt = provider.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (rs != null) {
            while(rs.next())
            {
                nuovoArticolo = new Article();
                nuovoArticolo.setNome(rs.getString("NOME"));
                array.add(nuovoArticolo);
            }
        }
        return array;
    }

    Article extendedQuery(String sql, String kind) throws SQLException {
        ResultSet rs = null;
        Article nuovoArticolo = null;
        try {
            Statement stmt = provider.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            if(rs.next())
            {
                switch (kind) {
                    case "Book":
                        nuovoArticolo = new Book();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                        ((Book)nuovoArticolo).setTitolo(rs.getString("TITOLO"));
                        ((Book)nuovoArticolo).setAutore(rs.getString("AUTORE"));
                        ((Book)nuovoArticolo).setEditore(rs.getString("CASA"));
                        ((Book)nuovoArticolo).setPagine(rs.getInt("PAGINE"));
                        break;
                    case "Electronics":
                        nuovoArticolo = new Electronics();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                        ((Electronics) nuovoArticolo).setModello(rs.getString("MODELLO"));
                        ((Electronics) nuovoArticolo).setMarca(rs.getString("MARCA"));
                        break;
                    case "Clothing":
                        nuovoArticolo = new Clothing();
                        ((Clothing) nuovoArticolo).setTaglia(rs.getInt("TAGLIA"));
                        ((Clothing) nuovoArticolo).setTipo(rs.getString("TIPO"));
                        ((Clothing) nuovoArticolo).setMarca(rs.getString("MARCA"));
                        break;
                    case "TextBook":
                        nuovoArticolo = new TextBook();
                        ((TextBook) nuovoArticolo).setEdizione(rs.getInt("EDIZIONE"));
                        ((TextBook) nuovoArticolo).setMateria(rs.getString("MATERIA"));
                        break;
                    default:
                        return null;
                }
            }
        }
        return nuovoArticolo;
    }
}
