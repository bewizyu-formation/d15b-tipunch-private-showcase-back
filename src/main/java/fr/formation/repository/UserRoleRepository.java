package fr.formation.repository;

import java.util.List;

import fr.formation.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	@Query("select a.role from UserRole a, User b where b.login=?1 and a.userId=b.id")
	public List<String> findRoleByUserLogin(String login);

    @Transactional
    @Modifying
	void deleteByUserId(Long userId);
}
