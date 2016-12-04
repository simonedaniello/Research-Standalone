package control;

import entity.Article;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dandi
 */

abstract class DatabaseController {
    public abstract ArrayList<Article> searchArticle(String sql) throws SQLException;
}
