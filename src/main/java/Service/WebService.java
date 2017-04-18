package Service;

import Service.webInfo;

import java.util.List;

public interface WebService {
	webInfo findById(int id);
	void saveWebpage(webInfo webInfo);
	void updateWebpage(webInfo webInfo);
	void deleteUserById(int id);
	List<webInfo> findAllWebPages();
}
