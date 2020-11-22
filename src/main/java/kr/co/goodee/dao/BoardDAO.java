package kr.co.goodee.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.goodee.dto.BoardDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list();

	BoardDTO detail(String idx);

	int write(HashMap<String, String> params);

	int delete(String idx);

	int bhit(String idx);

	int update(HashMap<String, Object> params);

}
