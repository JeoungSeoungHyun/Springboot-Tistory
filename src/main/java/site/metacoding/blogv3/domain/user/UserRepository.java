package site.metacoding.blogv3.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE username=:username", nativeQuery = true)
    // null일 수도 있기 때문에 Optional에 담자
    Optional<User> FindByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM user WHERE email=:email", nativeQuery = true)
    // null일 수도 있기 때문에 Optional에 담자
    Optional<User> FindByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user WHERE username = :username AND email = :email", nativeQuery = true)
    Optional<User> FindByUsernameAndEmail(@Param("username") String username, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE user SET password = :password WHERE username = :username", nativeQuery = true)
    // null일 수도 있기 때문에 Optional에 담자
    void 비밀번호변경(@Param("password") String password, @Param("username") String username);
}
