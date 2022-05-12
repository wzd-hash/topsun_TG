package com.topsun.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
 
 
public class FtpUtils{
	
	private static FTPClient client = new FTPClient();
	private static final int BUFFER_SIZE = 1024000;
 
	 
	public static FTPClient isConnection() throws IOException{
		try
		{
			// FTPaddress
			client.connect("117.78.47.247");
			// name password
			client.login("topsun", "topsun2019");
			client.setConnectTimeout(600000);
			client.enterLocalPassiveMode();
			int reply = client.getReplyCode();
			if((!FTPReply.isPositiveCompletion(reply)))
			{
				client.disconnect();
				throw new IOException("登录ftp服务器失败,请检查ftp配置是否正确!");
			}
		}
		catch(IOException e)
		{
			throw new IOException(e);
		}
		
		return client;
	}
	 
	 
	/**
	 * 关闭ftp连接
	 */
	public static void closeConnection()
	{
		try
		{
			if(client.isConnected())
			{
				client.logout();
				client.disconnect();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	 
	public static long getFileSize(FTPClient client,String fileName){
		FTPFile[] files = null;
		Long fileSize = -1L;
		client.setControlEncoding("utf-8");

		try {
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.setBufferSize(BUFFER_SIZE);
	 
			files = client.listFiles(fileName);
	 
			if(null != files && files.length > 0)
			{
				fileSize = files[0].getSize();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.err.println("文件大小:"+fileSize);
		return fileSize;
	}
	
	
	/**
     * 下载ftp服务器文件方法
     * @param ftpClient FTPClient对象
     * @param newFileName 新文件名
     * @param fileName 原文件（路径＋文件名）
     * @param downUrl  下载路径
     * @return
     * @throws IOException
     */
    public static boolean downFile(String newFileName, String fileName,ServletOutputStream outputStream) throws IOException {
        boolean isTrue = false;
        isTrue = client.retrieveFile(fileName, outputStream);
        outputStream.flush();
//        os.close();
        return isTrue;
    }
    
}