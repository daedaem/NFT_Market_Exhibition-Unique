package com.ssafy.unique.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.unique.jwt.JwtAccessDeniedHandler;
import com.ssafy.unique.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.unique.jwt.JwtSecurityConfig;
import com.ssafy.unique.jwt.TokenProvider;

// jwt 패키지의 5가지 클래스를 적용한다
@EnableWebSecurity
//@PreAuthorize 어노테이션을 메소드 단위로 사용하기 위해서 추가함
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // jwt 패키지의 클래스를 생성자에서 주입받는다
    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // BCryptPasswordEncoder 를 사용해서 암호화
    // 시큐리티 프레임워크에서 제공하는 클래스
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/favicon.ico"
                        ,"/assets/**"
                );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                
                // 토큰이 없는 상태에서 할 수 있는 행동에 대해서 열어준다
                // (04.01) 개발 후에는 Swagger,login, register 빼고 막아놓자.
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/members/login").permitAll()
                .antMatchers("/members/register").permitAll()
                .antMatchers("/swagger-ui/**").permitAll() // swagger-ui 접속
                .antMatchers("/v3/**").permitAll() //swagger 설정 참조
                .antMatchers("/nft/**").permitAll() // nft 관련 데이터 사용
                .antMatchers("/market/**").permitAll()
                .anyRequest().authenticated()
                
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}