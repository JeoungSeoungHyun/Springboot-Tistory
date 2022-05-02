package site.metacoding.blogv3.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.config.auth.LoginUser;
import site.metacoding.blogv3.service.PostService;
import site.metacoding.blogv3.web.dto.post.PostRespDto;

@RequiredArgsConstructor
@Controller
public class PostController {

    // 카테고리와 관련된 게시글의 정보도 같이 들고 가야하기 때문에 CategorySerivce가 아닌 PostService에서 처리
    private final PostService postService;

    @GetMapping("/user/{id}/post")
    public String postList(@PathVariable Integer id, @AuthenticationPrincipal LoginUser loginUser, Model model) {

        PostRespDto postRespDto = postService.게시글목록보기(id);
        model.addAttribute("postRespDto", postRespDto);
        return "post/list";
    }
}
