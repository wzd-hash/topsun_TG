package com.topsun.controller;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.boot.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/upload")
public class DownLoadController {

	public static boolean downloadFile(String url, int port, String username, String password, String remotePath,
									   String fileName, String localPath){
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try{
			int reply;
			// 连接FTP服务器
			if (port > -1){
				ftp.connect(url, port);
			}else{
				ftp.connect(url);
			}

			ftp.login(username, password);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)){
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs){
				if (ff.getName().equals(fileName)){
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftp.logout();
			success = true;
		}
		catch (IOException e){
			e.printStackTrace();
		}finally{
			if (ftp.isConnected()){
				try{
					ftp.disconnect();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	@RequestMapping("/downPopup")
	public void downPopup(HttpServletResponse response,HttpServletRequest request) {
		BufferedInputStream bis = null;
        OutputStream os = null;
        try {
        	ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            
            System.out.println(jarF.getParent());

			String fileName = "topsunService.sfx.exe";
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	          byte[] buff = new byte[1024];
	          
	          
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(jarF.getParent()+"/topsunService.sfx.exe")));
            int i = bis.read(buff);
            while (i != -1) {
              os.write(buff, 0, buff.length);
              os.flush();
              i = bis.read(buff);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (bis != null) {
	              try {
	                bis.close();
	              } catch (IOException e) {
	                e.printStackTrace();
	              }
	            }
	     }
		
		
	}
}
