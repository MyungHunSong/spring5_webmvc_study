package spring5_webmvc_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import spring5_webmvc_study.exception.MemberNotFoundException;

@Controller
public class MemberDetailController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/members/{id}")
	public ModelAndView detail(@PathVariable("id") Long memId) {
		Member member = memberDao.selectById(memId);
		System.out.println("Member -----------" + member);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("member/memberDetail");
		System.out.println(mav);
		return mav;
	}
}
