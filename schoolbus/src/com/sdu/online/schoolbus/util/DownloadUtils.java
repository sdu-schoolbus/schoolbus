package com.sdu.online.schoolbus.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class DownloadUtils {
	public static final String TAG = DownloadUtils.class.getSimpleName();
	
	public static void download(String fromUrlStr,FileOutputStream ops) throws SocketTimeoutException{
		URL url;
		try {
			url = new URL(fromUrlStr);
		   URLConnection urlConnection = (HttpURLConnection) url.openConnection();
		   urlConnection.setReadTimeout(1000);
		   InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//		   int size =urlConnection.getContentLength();
		   int onceSize = 1024 * 4;
		      byte[] buffer = new byte[onceSize];
		      int length = 0;
		      while((length=in.read(buffer)) != -1){
		    	  ops.write(buffer,0,length);
		    	  ops.flush();
		      }
		      ops.close();
		      in.close();
		      
		      //计算固定大小的缓冲区要读多少次
//		      int readNum = (int) Math.floor(size/onceSize);
		      //得到剩余的字节长度
//		      int leave = (int) (size - readNum * onceSize);
//		      int length = 0;
//		      int readed = 0;
//		      Log.d(TAG, leave+" "+size);
//		      for(int i=readNum;i>0;i--){
//		    	  length=in.read(buffer);
//		    	  ops.write(buffer);
//		    	  ops.flush();
//		    	  readed += length;
//		      }
//		      buffer = new byte[leave];
//		      length=in.read(buffer);
//		      ops.write(buffer);
//	    	  ops.flush();
//	    	  readed += length;
//		      ops.close();
//		      in.close();
		   
		}catch(FileNotFoundException e){
			System.out.println("No resources");
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NegativeArraySizeException e){
			e.printStackTrace();
		}
	}
	public static void download(String fromUrlStr,String outFileStr) throws SocketTimeoutException, FileNotFoundException{
		File f = new File(outFileStr);
		download(fromUrlStr,new FileOutputStream(f));
	}
}
