package DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import Service.webInfo;


@Repository("webDAO")
public class webDAOImpl extends AbstractDAO<Integer, webInfo> implements webDAO{

	@Override
	public webInfo findByid(int id) {
		webInfo webInfo=getByKey(id);
		return webInfo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<webInfo> findAllWebpages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("page_id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<webInfo> webInfos = (List<webInfo>) criteria.list();
		return webInfos;
	}

	@Override
	public void save(webInfo webInfo) {
		persist(webInfo);
	}
	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("page_id", id));
		webInfo webInfo = (webInfo)crit.uniqueResult();
		delete(webInfo);
		
	}
	
}
