package kr.or.nextit.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.nextit.attach.service.IAttachService;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.exception.BizNotEffectedException;

@Controller
public class NextITImageLoader {

	@Autowired
	private IAttachService attachService;
	
	@RequestMapping(value = "/image/{atchNo:^[0-9]+$}"
			, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImageByteArray(@PathVariable("atchNo") int atchNo) 
			throws BizNotEffectedException {
		
		AttachVO attach;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		byte[] byteData = null;
		try {
			attach = attachService.getAttach(atchNo);
			String filePath = attach.getAtchPath();
			String fileName = attach.getAtchFileName();
		 
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(filePath + File.separator + fileName);
		
			byte[] readBytes = new byte[1024];
			while( fis.read(readBytes) != -1) {
				baos.write(readBytes);
			}
			byteData = baos.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {fis.close();}catch(IOException e) {e.printStackTrace();}
			try {baos.close();}catch(IOException e) {e.printStackTrace();}
		}
		return byteData;
	}
	
	
	
}
