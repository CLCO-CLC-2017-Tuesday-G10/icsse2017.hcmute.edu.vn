package DAO;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Service.UserRole;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	@Query("select a.role from UserRole a, User b where b.userName=?1 and a.userid=b.userid")
    public List<String> findRoleByUserName(String username);
	
}