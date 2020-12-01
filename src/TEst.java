import java.util.ArrayList;

public class TEst {

	public static void main(String[] args) {
		test(new ArticleRowMapper());
	}

	public static <T> void test(MapperInterface<T> mapper) {
		Class<T> c = (Class<T>) mapper.getClass();
		ArrayList<T> rows = new ArrayList<>();
		try {
			T obj = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 컬럼명

		
		//System.out.println(class1.getGenericInterfaces()[1]);
		
//		//System.out.println(c.getTypeName());
//
//		Method[] methods = .getDeclaredMethods();
//		Field[] fields = .getDeclaredFields();
//		System.out.println(fields[1].getType());
//		try {
//			Method m = .getMethod("getTitle", null);
//			Object obj = .newInstance();
//			int a = (int)m.invoke(obj, null);
//			System.out.println(a);
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		for (int i = 0; i < fields.length; i++) {
//			try {
//				String fname = fields[i].getName();
//				fname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
//				System.out.println("get" + fname);
//				System.out.println(methods[i].invoke("get" + fname, null));
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
