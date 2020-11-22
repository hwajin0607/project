package kr.co.goodee.controller;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.goodee.dto.MemberDTO;
import kr.co.goodee.service.MemberService;


@Controller
public class MemberController {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String id,@RequestParam String pw,HttpSession session,RedirectAttributes rAttr) {
		logger.info("param : "+id+" / "+pw);
		int cnt = service.login(id,pw);
		ModelAndView mav = new ModelAndView();
		String page="redirect:/";
		String msg="아이디와 패스워드를 확인해 주세요.";
		if(cnt>0) {
			session.setAttribute("loginId", id);
			page="redirect:/list";
		}else {
			rAttr.addFlashAttribute("msg",msg);
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm(Model model) {
		logger.info("회원가입 폼 요청");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public @ResponseBody int joinForm(@ModelAttribute MemberDTO dto) { 
		logger.info("회원가입 요청 : "+dto.getId()+"/"+dto.getPw()+"/"+
		dto.getName()+"/"+dto.getAge()+"/"+dto.getGender()+"/"+dto.getEmail());
		// param 가져오면 dto.setId = param.get("id") 이런식으로  넣어준 후에 service.join(dto)
		return service.join(dto);
	}
	

	@RequestMapping(value = "/over", method = RequestMethod.GET)
	public @ResponseBody int over(@RequestParam String id) {
		logger.info("중복 요청,중복 확인할 아이디 : "+id);
		return service.over(id);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loginId");
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","로그아웃 되셨습니다.");
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "/fulldelete", method = RequestMethod.GET)
	public ModelAndView fulldelete(HttpSession session) {
		String id = (String) session.getAttribute("loginId");
		logger.info("삭제할 아이디 : "+id);
		int success = service.fulldelete(id);
		ModelAndView mav = new ModelAndView();
		String page="list";
		String msg="탈퇴 실패";
		if(success>0) {
			page="login";
			msg="회원 탈퇴 되셨습니다.";
		}
		mav.addObject("msg",msg);
		mav.setViewName(page);
		return mav;
	}
	
}
