package rent.dao;

import java.util.HashMap;

public interface RentDAO {
	public HashMap<String, Object> backMain();
	public int rentBook(int key, String userid);
}
