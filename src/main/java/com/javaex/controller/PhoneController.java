package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

//***controller임을 명시하기(어노테이션)***
//컨트롤러는 주소임. 메소드 위에 주소인걸 @문법으로 붙임.
@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	// 메소드 단위로 기능 정리 --> 전에는 doGet메소드 하나에 파라미터로 기능을 구분했는데 이번에는 메소드 단위로 나눔.

	// 필드
	// 생성자
	// 메소드 겟셋
	/* 메소드 일반 (기능 1개씩 --> 기능마다 url 부여) */
	
	// 테스트 : 주소 부여하면서 get, post 방식도 정함. 외부에서 접근할 때 메소드명과 상관없이 맵핑의 value로만 함.
	// --> 이렇게 하면 핸들러맵핑에 주소가 정리됨.
	// 주소창에 직접 넣는 게 GET이라 POST는 이렇게 하면 안 됨.
	// 2가지 방식 다 할 거면 다 생략하고 "주소"만 쓸 수도 있음.
	// 주소는 "writeForm"도 되고 "/phone/writeForm"해서 분류를 추가해도 되고
	/*
	@RequestMapping(value="/writeForm", method= {RequestMethod.POST, RequestMethod.GET}) 
	public String writeForm() {
		System.out.println("글쓰기폼");
		return "/WEB-INF/views/writeForm.jsp"; //문자열을 dispatcherServlet에 보내면 얘네가 view로 포워드한다고 보면 됨
	}
	
	@RequestMapping(value="/phone/list", method= {RequestMethod.GET, RequestMethod.POST}) //이미 phone 하위에 있는데 또 /phone 해서 오류남.
	public String list() {
		System.out.println("리스트");
		return "/WEB-INF/views/list.jsp";
	}
	*/
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		//dao를 통해 리스트 가져옴
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> phoneList = phoneDao.getList();
		System.out.println(phoneList.toString());
		
		//*****view는 포워드시키려고 return시키고 model(데이터)은 attribute해서 넘김.
		//model --> data를 보내는 법 --> 담아 놓으면 됨 
		model.addAttribute("pList", phoneList); //"이름", 실제데이터(주소값)
		
		return "/WEB-INF/views/list.jsp";
	}
	
	
	//등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("writeForm");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	//등록 (각 파라미터 꺼내기)
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	//(("파라미터") 쓸 이름)
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) { 
		System.out.println("write");
		
		PhoneVo pVo = new PhoneVo(name, hp, company);
		System.out.println(pVo.toString());
		
		PhoneDao pDao = new PhoneDao();
		pDao.phoneInsert(pVo);
		
		//리다이렉트 (view로 전달하고 ""안에는 url 씀)
		return "redirect:/phone/list";
	}
	
	//삭제 delete
	
	//수정폼 modifyForm
	//수정 modify
	
	

}
