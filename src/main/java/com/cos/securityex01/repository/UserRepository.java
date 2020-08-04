package com.cos.securityex01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.securityex01.model.User;

// JpaRepository를 상속하면 자동으로 컴포넌트 스캔 됨.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// Jpa Naming 전략 - 함수이름을 만들면 함수이름에 맞게 쿼리가 작동됨
	// SELECT * FROM user WHERE username = 1?
	//첫번째 파라미터라서 1이 붙음, 대문자 필수! 
	//SELECT * FROM user WHERE username = 1? And Password = 2? (String username,String password)
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	@Query(value="select * from user where email = 1?", nativeQuery = true)
	Optional<User> mFindEmail(String email); //네이밍 쿼리보다는 직접 짜는걸 추천
	
	//네이티브 (내가 원하는 쿼리문을 만들어서 쓸려면
	//@Query(value="select * from user",nativeQuery = true)
	//User find마음대로();
	
	//자세한건 Jpa Query Creation 검색
}
