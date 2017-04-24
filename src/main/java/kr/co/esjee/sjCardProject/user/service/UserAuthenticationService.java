package kr.co.esjee.sjCardProject.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.esjee.sjCardProject.user.dao.UserDaoService;
import kr.co.esjee.sjCardProject.user.vo.UserDetailsVO;

public class UserAuthenticationService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
	
	private SqlSessionTemplate sqlSession;
	
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		  // TODO Auto-generated constructor stub
		  this.sqlSession = sqlSession;
		 }

	
	@Resource(name="userDaoService")
	private UserDaoService dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.debug("loginPage() debug message");
		logger.info("loginPage() info message" + " ///// username:" + username);
		
		Map<String, Object> user = sqlSession.selectOne("user.selectUser", username);
		
		String user_name = (String)user.get("USERNAME");
		String user_pass = (String)user.get("PASSWD");
		String auth = (String)user.get("AUTHORITY");
		System.out.println(auth);
		
		List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
		grantedAuth.add(new SimpleGrantedAuthority(auth));
		
		UserDetailsVO detail = new UserDetailsVO();
		detail.setAuthorities(grantedAuth);
		detail.setUsername(user_name);
		detail.setPassword(user_pass);
		
		return detail;
	
	}

}
