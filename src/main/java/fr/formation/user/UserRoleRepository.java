package fr.formation.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	@Query("select a.role from UserRole a, User b where b.username=?1 and a.userId=b.id")
	public List<String> findRoleByUserName(String username);

}
