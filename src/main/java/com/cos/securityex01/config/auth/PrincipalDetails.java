package com.cos.securityex01.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.securityex01.model.User;

import lombok.Data;

//만드는 목적 : UserDetails를 return하기 위한 목적
//세션에 User가 아니라 이 클래스를 담을 예정 

//Authentication 객체에 저장할 수 있는 유일한 타입
//Authentication가 세션에 담기고
//Secuirty-Context-Holder안에 SecurityContext가 키값
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	//composition 하면 끝
	private User user;
	//8.3
	private Map<String,Object> attribute;
	
	public PrincipalDetails(User user) {
		super();
		this.user = user;
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
		//만료되었는지 물어봄 최종접속시간을 보고 true, false로 나눔
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//계정 Lock날렸는지 물어봄 비밀번호 x번 틀렸을때 false
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//비밀번호 오래 변경안해서 만료되었는지
		return true;
	}

	@Override
	public boolean isEnabled() {
		//계정 활성화 되었는지
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//권한
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add( () -> (user.getRole()));

		return authorities; //권한뭐야?
	}

	// 리소스 서버로부터 받는 회원 정보
	@Override
	public Map<String, Object> getAttributes() {
		//OAuth2User 타입에, Map으로 데이터를 넣어주기 때문에
		//Spring에서 getAttributes를 돌려줌
		//회원 정보들을 적어 두면 된다
		return attribute;
	}

	@Override
	public String getName() {
		//id 값 같은게 필요한데 아직 이해 못하므로 적어만 둠
		return "제공자  ID";
	}
}
