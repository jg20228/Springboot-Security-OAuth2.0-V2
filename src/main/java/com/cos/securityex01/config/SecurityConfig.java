package com.cos.securityex01.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.securityex01.config.oauth.PrincipalOauth2UserService;

//설정파일
@Configuration //IoC 빈(bean)객체 등록
@EnableWebSecurity //필터 체인 관리 시작 어노테이션 오버라이딩해서 체인 하나하나를 직접적을 관리가 가능하다.
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean //여기다가 적어두면 IoC가 되어서 쓰고 싶은곳에서 AutoWired하면 됨
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 비활성화, form에서 post요청시 주고 받는 토큰
		http.authorizeRequests()
			//user로 가는것만 가능하게 바꿈
			.antMatchers("/user/**").authenticated()
			//access를 쓰는 이유 : .hasAnyRole를 써도 되지만
			//'ROLE_'을 붙이는건 security를 쓸때 약속임
			//DB에는 user로 넣어도 꺼낼때는 ROLE_을 붙여야한다.
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
			.anyRequest().permitAll()
		.and()
			.formLogin() //기본적으로 userDetailService를 탄다.
			.loginPage("/login")
			.loginProcessingUrl("/loginProc")
			.defaultSuccessUrl("/")
		.and()//formLogin도 쓰고 oauth2Login도 쓰겠다
			.oauth2Login() ///oauth2/authorization/google로 올때 낚아채고나서 어디로 갈지 정해줘야함(Service)
			.loginPage("/login")
			.userInfoEndpoint()
			.userService(new PrincipalOauth2UserService())//만드는게 핵심
		;
	}
}