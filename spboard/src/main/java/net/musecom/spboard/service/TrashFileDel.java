package net.musecom.spboard.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.musecom.spboard.dao.UploadDao;
import net.musecom.spboard.dto.UploadFileDto;

@Repository
public class TrashFileDel {

	@Autowired
	private UploadDao uDao;
	
	@Autowired
	private ServletContext servletContext;
	
	public void delCom() {
		
		List<UploadFileDto> udaos = uDao.selectFileByBoardId(0);
		
		for(UploadFileDto fdao : udaos) { //uploadfiledto 타입을 udaos로 for문을 돌리며 뽑는다.
			String delFileName = fdao.getNfilename();
			try {
				//파일삭제
				String path = servletContext.getRealPath("/resources/upload/");
				File file = new File(path + delFileName);
				if(file.exists()) { //파일이 있을 때만 삭제
					file.delete();
					}
				//db 레코드 삭제
				uDao.deleteFile(fdao.getId());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
