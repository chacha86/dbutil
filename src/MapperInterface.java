import java.sql.ResultSet;

public interface MapperInterface<T> {

	public T getRow(ResultSet rs);
//	public void close(ResultSet rs);
}
