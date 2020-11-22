package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.service.BoardService;

@Controller
public class BoardController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		logger.info("list 요청");
		
		String id =(String) session.getAttribute("loginId");
		logger.info("세션 저장된 아이디 : "+id);
		ArrayList<BoardDTO> list = null;
		ModelAndView mav = new ModelAndView();
		String page="redirect:/";
		if(id!=null) {
			list = service.list();
			logger.info("리스트 몇개?: "+list.size());			
			mav.addObject("list",list);
			mav.addObject("listCount",list.size());
			page="list";
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam String idx,HttpSession session) {
		if(session.getAttribute("idx") == null) {
			service.bhit(idx);
		}
		session.removeAttribute("idx");
		logger.info("상세보기 요청" + idx);	
		ModelAndView mav = new ModelAndView();
		BoardDTO dto = service.detail(idx);
		mav.addObject("dto",dto);
		mav.setViewName("detail");
		return mav;
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam String idx,HttpSession session) {
		logger.info("수정하기 폼 요청 : "+idx);
		BoardDTO dto = service.detail(idx);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto",dto);
		mav.setViewName("updateForm");
		return mav;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(@RequestParam HashMap<String, String> params) {
		logger.info("글쓰기 요청"+params);
		int success = service.write(params);
		ModelAndView mav = new ModelAndView();
		String page="writeForm";
		if(success>0) {
			page="redirect:/list";
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam String idx) {
		logger.info("삭제 요청 : "+idx);
		int success= service.delete(idx);
		ModelAndView mav = new ModelAndView();
		String page="list";
		if(success>0) {
			page="redirect:/list";
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(RedirectAttributes rAttr, @RequestParam HashMap<String, Object> params,@RequestParam String idx,HttpSession session) {
		logger.info("수정할 params : "+params);
		logger.info("idx는? : "+idx);
		int success = service.update(params);
		logger.info("컨트롤러 수정 성공 ? : "+success);
		ModelAndView mav = new ModelAndView();
		String msg="수정 실패";
		mav.setViewName("redirect:/updateForm");
		if(success>0) {
			msg=" 수정 성공";
			session.setAttribute("idx", idx);
			mav.setViewName("redirect:/detail?idx="+idx);//detail로 보내니 400 에러(Required String parameter 'idx' is not present)났다.
		}
		rAttr.addFlashAttribute("msg",msg);
		return mav;
	}
	
	
	
}
