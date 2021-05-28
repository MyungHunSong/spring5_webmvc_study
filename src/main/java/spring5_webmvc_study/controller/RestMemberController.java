package spring5_webmvc_study.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring5_webmvc_study.exception.DuplicateMemberException;
import spring5_webmvc_study.exception.MemberNotFoundException;

@RestController
public class RestMemberController {
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberRegisterService registerService;

	@GetMapping("/api/members")
	public List<Member> members() {
		return memberDao.selectAll();
	}

	@GetMapping("/api/members/{id}")
	public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Member member = memberDao.selectById(id);
		if (member == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			throw new MemberNotFoundException();
		}
//		return member; 밑에 것을 이용해서 변환하여 처리한다.
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	

	// 추가하기 위한것.
	@PostMapping("/api/members")
	public ResponseEntity<Object> newMember(@RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response) throws IOException {
		try {
			new RegisterRequestValidator().validate(regReq, errors);
			if(errors.hasErrors()) {
//				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return ResponseEntity.badRequest().build();
			}
			
			Long newMemberId = registerService.regist(regReq);
//			response.setHeader("Location", "/api/members/" + newMemberId);
//			response.setStatus(HttpServletResponse.SC_CREATED);
			URI uri = URI.create("/api/members/"+ newMemberId);
			return ResponseEntity.created(uri).build();
		} catch (DuplicateMemberException e) {
//			response.sendError(HttpServletResponse.SC_CONFLICT);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
