package DAO;
import java.util.List;
import Service.webInfo;
public interface webDAO {
	webInfo findByid(int id);
	List<webInfo> findAllWebpages();
	void save(webInfo webInfo);
	void deleteById(int id);
}
