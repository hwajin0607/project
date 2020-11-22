package kr.co.goodee.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;

@Service
public class BoardService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardDAO dao;

	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = dao.list();
		logger.info("list : "+list);
		return list;
	}

	public BoardDTO detail(String idx) {
		BoardDTO dto = dao.detail(idx);
		logger.info("상세보기 : "+dto);
		return dto;
	}

	public int write(HashMap<String, String> params) {
		logger.info("서비스 글쓰기 요청"+params);
		int success = dao.write(params);
		logger.info("글쓰기 성공했나 ? : "+success);
		return success;
	}

	public int delete(String idx) {
		int success = dao.delete(idx);
		logger.info("삭제 성공했나? : "+success);
		return success;
	}

	public void bhit(String idx) {
		int success = dao.bhit(idx);
		logger.info("조회수 올리기 : "+success);
	}

	public int update(HashMap<String, Object> params) {
		int success = dao.update(params);
		logger.info("수정 성공했나? : "+success);
		return success;
	}
}
