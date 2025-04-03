package poly.java5.service;

import poly.java5.entity.User;
import poly.java5.test.UserTest;

public interface AccountService {

	/**
	 * @param Hàm tìm user bằng username, với password không để đăng nhập
	 * @return 1 user nếu thấy
	 * */
	User findByUsernameAndPassword(String un, String pw);

}
