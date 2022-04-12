package site.metacoding.blogv3.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;

@Data
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한들을 저장한다.
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되었는지 확인.
        // 최종 로그인한 날짜가 5년이 지나면 만료로 본다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // lock 걸렸는지 확인.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인.
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
