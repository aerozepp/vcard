package kr.co.esjee.sjCardProject.user.dao;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UserDaoService {

	public int insertUser(Map<String, String> paramMap);

	public Map<String, Object> selectUser(String username);

	public String uploadFile (CommonsMultipartFile file, HttpSession session, String directory) throws Exception;
	
	public String readFile(String file);
	
	public void vcardParser (String line);


}
