package spring5_webmvc_study.controller;

import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegisterService {
	
	@Autowired
	private MemberDao memberDao;
		
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateFormatFlagsException("dup email" + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(),req.getName(), req.getPassword(),  LocalDateTime.now()); 
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
