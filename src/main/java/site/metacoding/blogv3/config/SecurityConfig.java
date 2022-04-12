package site.metacoding.blogv3.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity // 해당 파일로 시큐리티가 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    // 인증 설정하는 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); => 기본 시큐리티 활성화
        http.csrf().disable(); // postman으로 테스트하기 위해 사용
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                // 스프링 시큐리티가 로그인시 확인하는 키(name)값 커스터마이징하기
                // .usernameParameter("user")
                // .passwordParameter("pwd")
                .loginPage("/login-form")
                .loginProcessingUrl("/login") // login 프로세스를 탄다.
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                            AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("이걸로 제어된다...");
                        response.sendRedirect("/");
                    }

                })
                // .successHandler(null)
                .defaultSuccessUrl("/");
    }
}
