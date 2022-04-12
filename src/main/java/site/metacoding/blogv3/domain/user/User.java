package site.metacoding.blogv3.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    // private String role; // admin, manager, guest, user 와 같이 권한을 준다.

    @Column(length = 12, nullable = false, unique = true)
    private String username;

    // 그대로 넣으면 안되니까 SHA256(해쉬 알고리즘)을 사용 = > 이렇게 안하면 시큐리티가 거부
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String email;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}
