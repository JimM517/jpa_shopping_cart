package com.magee.repository;

import com.magee.models.ApplicationUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	Optional<ApplicationUser> findByUsername(String username);


	// won't really need an update or delete function for users in this app,
	// just wanted to see if it would work

	@Modifying
	@Transactional
	int deleteApplicationUserById(Long user);



	@Modifying
	@Transactional
	@Query(
			value = "UPDATE users SET state_code = ?1 WHERE user_id = ?2",
			nativeQuery = true
	)
	int updateApplicationUserStateCodeByAppUserId(String stateCode, Long userId);



}
