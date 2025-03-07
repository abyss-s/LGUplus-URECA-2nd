package com.uplus.eureka.member.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.uplus.eureka.member.model.dao.MemberDao;
import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.dto.MemberException;

@Service
public class MemberServiceImp implements MemberService {
	
	private MemberDao dao ;
	
	public MemberServiceImp(MemberDao dao) {
		super();
		this.dao = dao;
	}
	public Member login(String id, String pass) {
		try {
			Member user = dao.search(id);
			System.out.println(user);
			if(user == null) throw new MemberException("등록되지 않은 아이디입니다.");
			
			if(!pass.equals(user.getPassword()))
				throw new MemberException("비밀 번호 오류 발생!!!!");
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("로그인 처리 중 오류 발생");
		}
	}
	@Override
	public Member search(String id) {
		// TODO 6. id에 해당하는 Member 정보를 dao를 통해 조회하고 리턴한다.  
		return null;
	}

	@Override
	public void regist(Member user) {
		try {
			Member find = dao.search(user.getId());
			if(find!=null) {
				throw new MemberException("이미 등록된 아이디 입니다.");
			}
			dao.regist(user);
		} catch (SQLException e) {
			throw new MemberException("회원 정보 처리 중 오류 발생!!!");
		}
	}

	@Override
	public void update(Member user) {
		// TODO 9. Member 정보 중 id로 회원 정보를 조회하고 없는 경우 예외 발생하고 있으면 update한다.   

	}

	@Override
	public void remove(String id) {
		// TODO 11. Member 정보 중 id로 회원 정보를 조회하고 없는 경우 예외 발생하고 있으면 remove한다.

	}

}
