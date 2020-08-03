package com.cos.securityex01.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	//여러 OAuth가 있지만 이 Default가 편함
	//일반적으로 로그인할때에 세션이 만들어짐 , OAuth2User 로그인하면 세션이 만들어지고
	
	// userRequest는 code를 받아서 accessToken을 응답 받은 객체
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//OAuth2UserRequest userRequest여기에 토큰이 있다.
		
		//커스터마이징
		//OAuth2User에는 이미 회원정보가 담겨있다. 
		//loadUser는 로그인 버튼 누르면 코드를 요청하고 토큰을 받는것까지 알아서 해준다.
		
		OAuth2User oAuth2User = super.loadUser(userRequest); //google의 회원 프로필 조회 
		
		
		// oAuth2User 정보를 어디에 담아서 무엇을 리턴하면 될까요?
		// principalDetails를 구성 해주면 끝임.
		
		// 1번 : PrincipalDetails에 OAuth2User 정보를 집어 넣어 준다.
		// 2번 : PrincipalDetails를 리턴한다.
		System.out.println("oAuth2User : "+oAuth2User); //토큰을 통해 응답받은 회원정보
		System.out.println("userRequest token : "+userRequest.getAccessToken().getTokenValue());
		System.out.println("userRequestClientRegistration : " +userRequest.getClientRegistration());//??
		try {
			
		} catch (Exception e) {
			
		}
		
		return super.loadUser(userRequest); //여기서 session에 등록됨
	}
	
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		//일반적으로는 로그인할 때 유저 정보 User
		//OAuth2로 로그인할 때 유저 정보 attributes <- 이거 구성해야함  , 파싱해서 오브젝트에 넣어도됨, 현상태 :귀찮아서 그냥 통으로 씀
		
		
		//1.OAuth2로 로그인 할때 유저정보 attributes
		
		//2. DB에 이사람 있나?
		
		//있으면?
		//--> 있으면 update 해야함. 구글에서 바뀐걸 알 수가 없음
		
		//없으면?
		//--> 없으면 insert 해야함.
		
		//return을 PrincipalDetails() Map안에 attributes가 들어가 있으니까!
		return null;
	}
}
