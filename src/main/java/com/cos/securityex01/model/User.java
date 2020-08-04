package com.cos.securityex01.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM - Object Relation Mapping

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id //pk걸어주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Column(nullable =false,unique = true, length =30) -> JPA에 가능한것
	private String username;
	private String password;
	private String email;
	private String role; //ROLE_USER , ROLE_ADMIN
	//OAuth를 위해 구성한 추가 필드 2개 
	private String provider;
	private String providerId;
	@CreationTimestamp //localdate가 나중에편함
	private Timestamp timestamp;
}
