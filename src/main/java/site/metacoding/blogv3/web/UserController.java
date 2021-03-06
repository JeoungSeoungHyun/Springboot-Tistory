package site.metacoding.blogv3.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.service.UserService;
import site.metacoding.blogv3.util.UtilValid;
import site.metacoding.blogv3.web.dto.user.JoinReqDto;
import site.metacoding.blogv3.web.dto.user.PasswordResetReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        // System.out.println("조인디티오 : " + joinReqDto.toString());

        UtilValid.요청에러처리(bindingResult);

        // 핵심로직
        userService.회원가입(joinReqDto.toEntity());

        return "redirect:/login-form";
    }

    @GetMapping("api/user/username/same-check")
    public ResponseEntity<?> usermaeSameCheck(String username) {

        // User user = userService.아이디확인(username);
        boolean isNotSame = userService.유저네임중복체크(username); // true(같지 않다.)
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);

    }

    @GetMapping("/s/user/{id}")
    public String updateForm(@PathVariable Integer id) {
        return "/user/updateForm";
    }

    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {
        return "user/passwordResetForm";
    }

    @PostMapping("/user/password-reset")
    public String passwordReset(@Valid PasswordResetReqDto passwordResetReqDto, BindingResult bindingResult) {

        UtilValid.요청에러처리(bindingResult);

        userService.패스워드초기화(passwordResetReqDto);
        return "redirect:/login-form";
    }

}
