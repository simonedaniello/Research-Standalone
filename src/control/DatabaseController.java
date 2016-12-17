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

    ArrayList<Article> searchArticle(String sql, String kind) throws SQLException {

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
                switch (kind) {
                    case "Book":
                        nuovoArticolo = ArticleFactory.getInstance().getBook();
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
                        nuovoArticolo = ArticleFactory.getInstance().getElectronics();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                        ((Electronics) nuovoArticolo).setModello(rs.getString("MODELLO"));
                        ((Electronics) nuovoArticolo).setMarca(rs.getString("MARCA"));
                        break;
                    case "Clothing":
                        nuovoArticolo = ArticleFactory.getInstance().getClothing();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                        ((Clothing) nuovoArticolo).setTaglia(rs.getInt("TAGLIA"));
                        ((Clothing) nuovoArticolo).setTipo(rs.getString("TIPO"));
                        ((Clothing) nuovoArticolo).setMarca(rs.getString("MARCA"));
                        break;
                    case "TextBook":
                        nuovoArticolo = ArticleFactory.getInstance().getTextBook();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                        ((TextBook) nuovoArticolo).setEdizione(rs.getInt("EDIZIONE"));
                        ((TextBook) nuovoArticolo).setMateria(rs.getString("MATERIA"));
                        break;
                    case "generic":
                        nuovoArticolo = ArticleFactory.getInstance().getArticle();
                        nuovoArticolo.setNome(rs.getString("NOME"));
                        nuovoArticolo.setProprietario(rs.getString("PROPRIETARIO"));
                        nuovoArticolo.setPrezzo(rs.getFloat("PREZZO"));
                        nuovoArticolo.setQuantità(rs.getInt("QUANTITA"));
                    default:
                        return null;
                }
                array.add(nuovoArticolo);
            }
        }
        return array;
    }

}
