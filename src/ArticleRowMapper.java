import java.sql.ResultSet;

public class ArticleRowMapper implements MapperInterface<Article> {

	@Override
	public Article getRow(ResultSet rs) {
		
		Article article = new Article();
		article.setId(1);
		article.setTitle("aaa");
		article.setBody("bbb");
		
		return article;
	}
	
}
