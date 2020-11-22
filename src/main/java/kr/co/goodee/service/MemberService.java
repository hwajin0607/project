package kr.co.goodee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.MemberDAO;
import kr.co.goodee.dto.MemberDTO;

@Service
public class MemberService {

private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberDAO dao;
	
	//로그인
	public int login(String id, String pw) {
		logger.info("서비스에서 param: "+id+" / "+pw);
		int cnt= dao.login(id,pw);
		logger.info("로그인 성공 : "+cnt);
		return cnt;
	}

	public int fulldelete(String id) {
		int success = dao.fulldelete(id);
		logger.info("회원탈퇴 성공 : "+success);
		return success;
	}

	public int join(MemberDTO dto) {
		int success = dao.join(dto);
		logger.info("회원가입 성공 : "+success);
		return success;
	}

	public int over(String id) {
		int success = dao.over(id);
		logger.info("중복이 되나? : "+success);
		return success;
	}



}
