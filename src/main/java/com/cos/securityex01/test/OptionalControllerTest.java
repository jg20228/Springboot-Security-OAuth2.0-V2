package com.cos.securityex01.test;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;

@RestController
public class OptionalControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test/user/{id}")
	public User 옵셔널_유저찾기(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id); //JPA는 기본적으로 CRUD를 들고 있음 findById 들어가면 Optional 적혀있음
		
		
		//1. .get을 쓸수있는 유일한 방법인데 쓸필요는 없다.
//		User user;
//		if(userOptional.isPresent()) {
//			user = userOptional.get();
//		}else {
//			user = new User();
//		}
		
		
		//2. orElseGet 가장 많이씀
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return User.builder().id(5).username("아무개").email("아무개@navet.com").build();
//			}
//		});
		
//		User user2 = userRepository.findById(id).orElseGet(()->{
//			//어차피 내부에 get이 실행되니까 함수명도 필요없음, 함수가 하나일때
//			return User.builder().id(4).username("4아무개").email("4아무개@navet.com").build();
//		});
		
		
		//3.orElseThrow
		User user = userRepository.findById(id).orElseThrow(()->{
			return new NullPointerException("없어 값");
		});
			
		
		
		return user;
		
		
		//Class Container<T> {
		//T data;
		//}
		//1.get, 2.orElseGet(), orElsethorw(), isPresent()
	}
}
