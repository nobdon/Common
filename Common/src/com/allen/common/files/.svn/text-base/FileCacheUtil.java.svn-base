package com.allen.common.files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

/**
 * 缓存工具类
 * 
 * @author Allen
 */
public class FileCacheUtil {

	public static final String SDCARD_PATH = Environment
			.getExternalStorageDirectory() + "/";
	public static final String CACHE_PATH_ROOT = SDCARD_PATH + "WTSIM/";
	private Context ctx;
	
	// 判断SD卡是否存在
	public static boolean sdCardIsExist() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	public static boolean isFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static void createDir(String filePath) {
		File dir = new File(filePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public static File createSDDir(String dirPath) {
		File dir = new File(CACHE_PATH_ROOT);
		dir.mkdirs();

		return dir;
	}

	public static File createSDFile(String filePath) throws IOException {
		String path = filePath.substring(0, filePath.lastIndexOf("/"));
		if (!isFileExist(path)) {
			createSDDir(path);
		}
		File file = new File(filePath);
		file.createNewFile();

		return file;
	}

	/**
	 * 图片缓存写入
	 * 
	 * @author ysj
	 */
	public static void savePicture(Bitmap bitmap, int resourcesId) {
		String pictureName = FileCacheUtil.CACHE_PATH_ROOT + resourcesId
				+ ".png";
		File file = new File(pictureName);
		FileOutputStream out;
		try {
			createDir(CACHE_PATH_ROOT);
			out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * json文件 写入
	 * 
	 * @author Allen
	 */
	public static File writeContactCache(StringBuffer sb,String fileName) {
		
		File file = null;
		OutputStream outputStream = null;
		try {
			createDir(CACHE_PATH_ROOT);
			file = createSDFile(CACHE_PATH_ROOT + fileName + ".json");
			outputStream = new FileOutputStream(file);
			outputStream.write(sb.toString().getBytes("UTF-8"));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
     * 读取文件中的内容
     * @param fileName
     * @return
     * @throws IOException 
     */
    public static String readFile(String fileName) throws IOException{
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        FileInputStream inputStream = new FileInputStream(fileName);
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        byte[] data = outputStream.toByteArray();
        return new String(data);
    }
	
	public static void savecompanyPicture(Bitmap bitmap, String imgName) {
		String pictureName = FileCacheUtil.CACHE_PATH_ROOT + imgName
				+ ".png";
		File file = new File(pictureName);
		FileOutputStream out;
		try {
			createDir(CACHE_PATH_ROOT);
			out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 清空缓存
	 * 
	 * @author ysj
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 文件下载和保存
	 * @param _url 下载地址
	 * @param fileName 文件保存的名称
	 */
	public static void downloadFile(String _url,String fileName) {
		try {
			URL url = new URL(_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			// int length = conn.getContentLength(); //获取文件大小
			InputStream is = conn.getInputStream();
			File file = new File(CACHE_PATH_ROOT);
			// 缓存目录不存在就创建目录
			if (!file.exists()) {
				file.mkdir();
			}
			
			String fileDir = CACHE_PATH_ROOT + fileName + ".html";
			File file1 = new File(fileDir);
			FileOutputStream fos = new FileOutputStream(file1);
			byte[] bs = new byte[1024];
			int len;
			
	        //开始读取
	        while ((len = is.read(bs)) != -1) {
	            fos.write(bs, 0, len);
	        }
	        
	        //完毕，关闭所有链接
			fos.close();
			is.close();
		} catch (Exception e) {
			System.out.println("下载html异常"+e);
		}
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

}
