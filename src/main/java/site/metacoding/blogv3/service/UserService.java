package site.metacoding.blogv3.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;
import site.metacoding.blogv3.handler.ex.CustomException;
import site.metacoding.blogv3.util.email.EmailUtil;
import site.metacoding.blogv3.web.user.PasswordResetReqDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailUtil emailUtil;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해쉬 알고리즘
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    public User 아이디확인(String username) {
        Optional<User> userOp = userRepository.FindByUsername(username);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            return null;
        }
    }

    public boolean 유저네임중복체크(String username) {
        Optional<User> userOp = userRepository.FindByUsername(username);

        if (userOp.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public void 패스워드초기화(PasswordResetReqDto passwordResetReqDto) {

        // 1. username, email 이 같은 것이 있는지 체크 (DB)

        // 2. 같은 것이 있다면 DB password 초기화 - BCrypt 해시 - update 하기 (DB)

        Optional<User> userOp = userRepository.FindByUsernameAndEmail(
                passwordResetReqDto.getUsername(),
                passwordResetReqDto.getEmail());

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            String encPassword = bCryptPasswordEncoder.encode("9999"); // 해쉬 알고리즘
            userEntity.setPassword(encPassword);
            // 3. 초기화된 비밀번호 이메일로 전송
            emailUtil.sendEmail("이메일", "비밀번호 초기화", "초기화된 비밀번호 : 9999");

        } else {
            throw new CustomException("해당 계정이 존재하지 않습니다.");
        }
    }
}
