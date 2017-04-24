package kr.co.esjee.sjCardProject.user.dao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.io.text.VCardReader;
import ezvcard.property.Birthday;
import ezvcard.property.FormattedName;

@Service("userDaoService")
public class UserDaoServiceImpl extends SqlSessionDaoSupport implements UserDaoService {

	@Override
	public int insertUser(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("user.insertUser", paramMap);
	}

	@Override
	public Map<String, Object> selectUser(String username) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("user.selectUser", username);
	}

	@Override
	public String uploadFile(CommonsMultipartFile file, HttpSession session, String directory) throws Exception {
		
		ServletContext context = session.getServletContext();
		String path = context.getRealPath(directory);
		String filename = file.getOriginalFilename();

		System.out.println("file path : " + path + " " + filename);

		byte[] bytes = file.getBytes();
		
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(path + File.separator + filename)));
		
		stream.write(bytes);		
		stream.flush();
		stream.close();
		
		return path+ "\\" + filename;
	}

	@Override
	public String readFile(String fileName) {
		
       
        String line = null;
        String vcard = "";

        try {
            
            FileReader fileReader = 
                new FileReader(fileName);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                vcard += line+"\r\n";
            }   
     
          
            bufferedReader.close();
            
            return vcard;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");    
            
            return null;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            
           
            
            return null;
        }
        
     
    }

	@Override
	public void vcardParser(String line) {
		
		VCard vcard = Ezvcard.parse(line).first();
		String fullName = vcard.getFormattedName().getValue();
		String lastName = vcard.getStructuredName().getFamily();
		
		System.out.println(fullName);
	}


	
	
}