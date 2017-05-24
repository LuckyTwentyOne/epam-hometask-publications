package ua.kh.butov.testenv;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestDataGenerator {

	// JDBC setting for database
	private static final String JDBC_URL = "jdbc:postgresql://localhost/subpub";
	private static final String JDBC_USERNAME = "subpub";
	private static final String JDBC_PASSWORD = "password";

	private static final String IMG_PATH = "external/test-data/";
	private static final String MEDIA_DIR = "C:/Users/ASUS/workspace/subpub/src/main/webapp/media";
	private static final String[] NAMES = { "GQ(Россия)", "DFOTO", "Forbes", "За рулем", "Joy", "Телепрограмма", "Viva", "Cosmopolitan", "IN STYLE", "Men's Health", "Tatler", "Burda", "GALA", "Glamour", "Hi-TECH PRO", "Hair's Now", "Maxim", "Pink"};
	private static final String[] CATEGORIES = { "Кулинария", "Кино и Телевидение", "Для молодежи", "Мода и стиль", "Медицина и здоровье", "Образование", "Спорт", "Путешествия и отпуск", "Для мужчин", "Общество. Политика", "Мир животных и растений", "Наука. Техника. Фото" };
	private static final String[] DESCRIPTION = { "Позитивний і яскравий журнал для впевнених в собі, амбітних дівчат з відчуттям гумору і прагненням до успіху у всьому. Присвячений жіночій сексуальності, любові, стосункам з чоловіками, самореалізації, кар’єрі. У виданні завжди є інформація про красу, моду, розваги, подорожах і про все те, що цікавить молодих та енергійних читачок. Концепція журналу єдина по всьому світу, але реалізується з врахуванням особливостей кожної конкретної країни. Сьогодні має 58 міжнародних видань, публікується на 36 мовах, поширюється в більш ніж 100 країнах і є журнальним брендом", "Мужской журнал с характером и стилем. Самое авторитетное в мире мужское издание о моде и стиле", "Представляет мировых знаменитостей, демонстрируя удачные стилистические решения на примере звезд. Самые известные и красивые люди планеты рассказывают читательницам о том, как со вкусом обставить дом, ухаживать за своей внешностью, одеться в офис, на деловой обед или на праздничный вечер. Cлавится элегантным и не лишенным чувства юмора подходом к самым разным проявлениям стиля. Это необычный и яркий журнал о красивых звездах, красивых вещах и красивой жизни. За 11 лет, прошедших с появления первого номера журнала в Америке, задал новые стандарты в модной журналистике, обратившись к читательницам открыто и дружелюбно. Ему доверяют около 10 миллионов читательниц во всем мире", "Мода, медицина, косметологія, спорт, розваги, подорожі", "Информационный журнал о стиле жизни, новостях светской жизни, знаменитостях. Призван занять пустующую нишу профессионально выполненного журнала для светского общества. Модному городу нужен модный журнал" };
	private static final String[] CONDITONS = {"Оформити передплату на видання можуть тільки жителі України, крім Криму (тимчасово). Доставка здійснюється поштовим відправленням, по Києву можлива кур’єрська доставка. Вартість передплати від способу доставки не змінюється. Всю відповідальність за доставку видань несе відділ передплати: (044) 111–1111. Для успішної доставки кур’єром і поштою прохання вказувати код на вхідних дверях, особливості адреси проживання, контактну особу, індекс міста."};

	private static final Random r = new Random();

	public static void main(String[] args) throws Exception {
		clearMedia();
		try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
			c.setAutoCommit(false);
			clearDb(c);
			insertPublications(c);
			c.commit();
			System.out.println("Data generated successful");
		}
	}

	private static void clearMedia() throws IOException {
		if (Files.exists(Paths.get(MEDIA_DIR))) {
			Files.walkFileTree(Paths.get(MEDIA_DIR), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		}
		System.out.println("Media dir cleared");
	}

	private static void clearDb(Connection c) throws SQLException {
		Statement st = c.createStatement();
		st.executeUpdate("delete from account");
		st.executeUpdate("delete from publication");
		st.executeQuery("select setval('account_seq', 1, false)");
		st.executeQuery("select setval('publication_seq', 1, false)");
		st.executeQuery("select setval('category_seq', 13, false)");
		System.out.println("Db cleared");
	}
	
	private static void insertPublications(Connection c) throws IOException, SQLException{
		try (PreparedStatement ps = c.prepareStatement("insert into publication values (?,?,?,?,?,?,?)")) {
			List<Publication> publications = generatePublications();
			int i = 278009;
			for (Publication publication : publications) {
				ps.setInt(1, i);
				ps.setString(2, publication.name);
				ps.setString(3, DESCRIPTION[r.nextInt(DESCRIPTION.length)]);
				ps.setString(4, CONDITONS[0]);
				ps.setString(5, publication.imageLink);
				ps.setInt(6, r.nextInt(30));
				ps.setInt(7, publication.idCategory);
				ps.addBatch();
				i += r.nextInt(10) + 1;
			}
			ps.executeBatch();
		}
		System.out.println("Success Insert");
	}
	
	private static List<Publication> generatePublications() throws IOException {
		List<Publication> publications = new ArrayList<>();
		for (String category : CATEGORIES) {
			for (int i = 0;i<15;i++) {
					String imageLink = getProductImageLink();
					publications.add(new Publication(r.nextInt(12),NAMES[r.nextInt(NAMES.length)], imageLink));
			}
		}
		Collections.shuffle(publications);
		return publications;
	}
	
	private static String getProductImageLink() throws IOException {
		File[] photos = new File(IMG_PATH).listFiles();
		String uid = UUID.randomUUID().toString() + ".jpg";
		File photo = new File(MEDIA_DIR + "/img/" + uid);
		if (!photo.getParentFile().exists()) {
			photo.getParentFile().mkdirs();
		}
		Files.copy(Paths.get(photos[r.nextInt(photos.length)].getAbsolutePath()), Paths.get(photo.getAbsolutePath()));
		return "/subpub/media/img/" + uid;
	}
	

	private static class Publication {
		final int idCategory;
		final String name;
		final String imageLink;
		public Publication(int idCategory, String name, String imageLink) {
			this.idCategory = idCategory;
			this.name = name;
			this.imageLink = imageLink;
		}
		 
	}
}
