package site.metacoding.blogv3.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE username=:username", nativeQuery = true)
    // null일 수도 있기 때문에 Optional에 담자
    Optional<User> FindByUsername(@Param("username") String username);
}
