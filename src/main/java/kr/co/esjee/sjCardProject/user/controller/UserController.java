package kr.co.esjee.sjCardProject.user.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Birthday;
import ezvcard.property.FormattedName;
import kr.co.esjee.sjCardProject.user.dao.UserDaoService;
import kr.co.esjee.sjCardProject.util.ShaEncoder;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String UPLOAD_DIRECTORY = "/vcf";

	@Resource(name = "shaEncoder")
	private ShaEncoder encoder;

	@Resource(name = "userDaoService")
	private UserDaoService dao;

	@RequestMapping("/user/loginPage")
	public String loginPage() {
		return "/user/loginPage";
	}

	@RequestMapping("/user/denied")
	public String denied(Model model, Authentication auth, HttpServletRequest req) {
		AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		logger.info("ex : {}", ade);
		model.addAttribute("auth", auth);
		model.addAttribute("errMsg", ade);
		return "/user/denied";
	}

	@ResponseBody
	@RequestMapping(value = "/user/insertUser")
	public String insertUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("file") CommonsMultipartFile file, HttpSession session) throws Exception {

		System.out.println("filename: " + file.getOriginalFilename());
		logger.info(file.getOriginalFilename());

		String filename = dao.uploadFile(file, session, UPLOAD_DIRECTORY);
		String line = dao.readFile(filename);
		
		dao.vcardParser(line);

		System.out.println("insertUser");
		logger.debug("insertUser() username: " + username + " password : " + password);

		String dbpw = encoder.saltEncoding(password, username);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		paramMap.put("passwd", dbpw);
		int result = dao.insertUser(paramMap);
		logger.info("result ===> {}", result);

		return "/user/userDetails";
	}
}
