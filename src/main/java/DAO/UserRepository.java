package DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Service.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String username);
    
}