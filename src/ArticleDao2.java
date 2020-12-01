import java.util.ArrayList;

public class ArticleDao2 {

	private DBUtil2 db = new DBUtil2();
	
	public ArrayList<Article> getArticles() {
		String sql = "select * from article";
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public Article getArticleById(int aid) {
		String sql = "select * from article where id = ?";
		return db.getRow(sql, new ArticleRowMapper(), aid);
	}
}
