package edu.ilstu.lai.RegistrationDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ilstu.lai.RegistrationDemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
}
