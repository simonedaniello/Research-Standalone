package control;

import databaseINIT.Provider;
import entity.Article;

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
}
