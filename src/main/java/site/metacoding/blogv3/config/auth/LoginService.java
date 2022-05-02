package site.metacoding.blogv3.config.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;

@RequiredArgsConstructor
@Service // IoC 컨테이너 등록됨.
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOp = userRepository.FindByUsername(username);

        if (userOp.isPresent()) {
            return new LoginUser(userOp.get());
        }
        // // 제어불가 그 전에 터진다.
        // else {
        // System.out.println("터져라 터져라 빵빵터져라");
        // throw new CustomException("유저네임이 없습니다");
        // }

        return null; // Autuentication의 principal에 null이 리턴된다.
        // throw new CustomException("유저네임이 없습니다");
    }

}
