package site.metacoding.blogv3.domain.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // Love 기능 구현되면 쿼리를 변경 할 예정.
    @Query(value = "SELECT * FROM post ORDER BY id DESC LIMIT 0,9", nativeQuery = true)
    List<Post> mFindByPopular();

    @Query(value = "SELECT * FROM post WHERE userId = :userId AND categoryId = :categoryId ORDER BY id DESC", nativeQuery = true)
    Page<Post> findByUserIdAndCategoryId(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId,
            Pageable pageable);

    @Query(value = "SELECT * FROM post WHERE userId = :userId ORDER BY id DESC", nativeQuery = true)
    Page<Post> findByUserId(@Param("userId") Integer userId, Pageable pageable);

    // INSERT, UPDATE, DELETE 와 같이 DB의 변화가 필요한 쿼리를 직접 작성할 때에는
    // Modifying 어노테이션을 붙여줘야한다.
    @Modifying
    @Query(value = "INSERT INTO post(categoryId, title, content, userId, thumnail, createDate, updateDate) VALUES(:categoryId, :title, :content, :userId, :thumnail, now(), now())", nativeQuery = true)
    void mSave(Integer categoryId, Integer userId, String title, String content, String thumnail);
}