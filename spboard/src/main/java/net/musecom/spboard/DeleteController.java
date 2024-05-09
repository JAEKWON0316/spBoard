package net.musecom.spboard;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.musecom.spboard.dao.SpBoardDao;
import net.musecom.spboard.dao.UploadDao;
import net.musecom.spboard.dto.UploadFileDto;

@RestController   //안에 있는 내용 다 response가 가능하게 된다.
public class DeleteController {
	
	@Autowired
	UploadDao uploadDao;
	
	@Autowired
	SpBoardDao dao;
	
	@Autowired
	ServletContext servletContext;
	
	//아이디와 패스워드를 받아서
	//1. 비밀번호 검증
	//2. 만약에 틀리면
	//3. return 0
	//4. 만약에 성공했으면
	//5. 파일db를 읽어서 파일을 삭제, 파일레코드 삭제, 게시판 레코드 삭제
	//6. return 1
	@PostMapping("/del")
	public String delOk(@RequestParam("id") int id, @RequestParam("pass") String pass) {
		System.out.println("delOk 삭제로직 수행");
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			params.put("pass", pass);
			
			int result = dao.validatePass(params);
			
			if(result > 0) { //result가 1이면 true --> 파일db를 읽어서 파일삭제, 파일레코드 삭제, 게시판 레코드 삭제
				String path = servletContext.getRealPath("/resources/upload");
				//파일 db읽어서
				List<UploadFileDto> fDtos = uploadDao.selectFileByBoardId(id);
				for(UploadFileDto fDto : fDtos) { //업로드파일dto의 fDto를 fDtos로 받아서 for문을 돌린다.
					String delFileName = fDto.getNfilename();
					File file = new File(path + delFileName);
					//파일을 삭제
					if(file.exists()) {
						file.delete();
					}
					//파일 레코드 삭제
					uploadDao.deleteFile(fDto.getId()); //여기 아이디는 jboardid가 아닌 img 1개에 대한 id (jboard id로 삭제하면 여러개가 삭제되니까!)
				}
				//게시판 레코드 삭제
				dao.delete(id);
				return "1";	
			}
			else {
				return "0";
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "0";
		}
		
		
	}
}
