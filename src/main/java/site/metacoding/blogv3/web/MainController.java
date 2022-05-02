package site.metacoding.blogv3.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import site.metacoding.blogv3.config.auth.LoginUser;

@Controller
public class MainController {

    @GetMapping({ "/" })
    public String main(@AuthenticationPrincipal LoginUser loginUser) {
        // 로그인시는 해당유저의 게시글목록으로 이동(SuccessfulHandler를 통해)
        // 메인페이지는 따로 존재
        return "main";
    }

}
