package com.api.cossc.repository;

import com.api.cossc.domain.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  @Query("SELECT u.refreshToken FROM User u WHERE u.id=:id")
  String getRefreshTokenById(@Param("id") Long id);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.refreshToken=:token WHERE u.id=:id")
  void updateRefreshToken(@Param("id") Long id, @Param("token") String token);
}
