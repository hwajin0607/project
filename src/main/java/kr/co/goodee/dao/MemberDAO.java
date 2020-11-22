package kr.co.goodee.dao;

import kr.co.goodee.dto.MemberDTO;

public interface MemberDAO {

	int login(String id, String pw);

	int fulldelete(String id);

	int join(MemberDTO dto);

	int over(String id);

}
