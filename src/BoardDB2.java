import java.util.ArrayList;
import java.util.Scanner;

public class BoardDB2 {

	public static void main(String[] args) {
		ArticleDao2 articleDao = new ArticleDao2();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			if (cmd.equals("list")) {
				try {
					ArrayList<Article> articles = articleDao.getArticles();
					for (int i = 0; i < articles.size(); i++) {
						System.out.println(articles.get(i).getTitle());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
