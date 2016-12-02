package com.allen.common.files;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

/**
 * 文件工具类
 * 
 * @author pl
 * 
 */
public class FileUtil {
	/**
	 * 保存文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param fileContent
	 *            文件内容
	 */
	public static void save(Context context, String fileName, String fileContent)
			throws Exception {
		// Context.MODE_PRIVATE --私有操作模式：创建出来的文件只能被本应用访问，其它应用无法访问该文件
		// 另外采用私有操作模式创建的文件，写入文件中的内容会覆盖原文件内容
		// 如果想把新写入的内容追加到原文件中，可以使用Context.MODE_APPEND
		FileOutputStream outStream = context.openFileOutput(fileName,
				Context.MODE_PRIVATE);
		outStream.write(fileContent.getBytes());
		outStream.close();
	}

	/**
	 * 
	 * @param bitmap bitmap流
	 * @param path 保存路径 包含文件名称 (etc /data/data/[包名]/best )
	 * @throws IOException
	 */
	public static void saveBitmap(Bitmap bitmap,String path) {
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 文件内容
	 * @throws Exception
	 */
	public static String read(Context context, String fileName)
			throws Exception {
		FileInputStream inStream = context.openFileInput(fileName);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		// 防止数据量过大，超过了1024
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			// 防止每次读取覆盖前一次数据
			outStream.write(buffer, 0, len);
		}

		byte[] data = outStream.toByteArray();

		return new String(data);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param context
	 * @param filename
	 * @return
	 */
	public static boolean exist(Context context, String filename) {
		File file = context.getFilesDir();
		File file2 = new File(file, filename);
		return file2.exists();
	}

	/**
	 * 获取文件夹大小
	 * 
	 * @param file
	 *            File实例
	 * @return long 单位为kb
	 * @throws Exception
	 */
	public static long getFolderSize(String path) {
		File file=new File(path);
		long size = 0;
		if (!file.exists() || !file.isDirectory())
			return 0;
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				size += getFolderSize(fileList[i].getPath());
			} else {
				size += fileList[i].length();
			}
		}
		return size / 1024;// 1048576;
	}

	/**
	 * 删除指定目录下文件及目录
	 * 
	 * @param deleteThisPath
	 * @param filepath
	 * @return
	 */
	public void deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);

			if (file.isDirectory()) {// 处理目录
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFolderFile(files[i].getAbsolutePath(), true);
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {// 如果是文件，删除
					file.delete();
				} else {// 目录
					if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
						file.delete();
					}
				}
			}
		}
	}

	/**
	 * 递归求取目录文件个数
	 */
	public static long getlist(File f) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + flist[i].listFiles().length;
				size--;
			}
		}
		return size;
	}

	/**
	 * 获取本地数据库文件大小
	 */
	public static long getDatabaseCacheSize(Context ctx) {
		long size= 0;
		String path = Environment.getDataDirectory() + "/"
				+ ctx.getPackageName() + "/database";
		size = getFolderSize(path);
		return size / 1024;
	}

	/**
	 * 获取本地文件大小
	 */
	public static long getFilesCacheSize(Context ctx) {
		long size = 0;
		String path = Environment.getDataDirectory() + "/"
				+ ctx.getPackageName() + "/files";
		size = getFolderSize(path);
		return size/1024;
	}

	/**
	 * 获取本地SharePref大小
	 */
	public static long getSharePrefCacheSize(Context ctx) {
		long size = 0;
		String path = Environment.getDataDirectory() + "/"
				+ ctx.getPackageName() + "/Shared_Pref";
		size = getFolderSize(path);
		return size/1024;
	}

	/**
	 * 获取程序本地缓存大小
	 */
	public static long getSystemCacheSize(Context ctx) {
		long size = 0;
		size = getDatabaseCacheSize(ctx) + getFilesCacheSize(ctx)
				+ getSharePrefCacheSize(ctx);
		return size;
	}

	/**
	 * 从resource中的raw文件夹中获取文件并读取数据（资源文件只能读不能写）
	 * 
	 * @param fileInRaw
	 * @return
	 */
	public static String readFromRaw(int fileInRaw, Context ctx) {
		String res = "";
		try {
			InputStream in = ctx.getResources().openRawResource(fileInRaw);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			res = EncodingUtils.getString(buffer, "GBK");
			// res = new String(buffer,"GBK");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 从asset中获取文件并读取数据（资源文件只能读不能写）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readFromAsset(String fileName, Context ctx) {
		String res = "";
		try {
			InputStream in = ctx.getResources().getAssets().open(fileName);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void fileSave(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeSave(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(fileName, true);// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加文件：使用RandomAccessFile
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            追加的内容
	 */
	public static void randomSave(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	static boolean flag_beta;
	static File file_beta;
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    flag_beta = false;  
	    file_beta = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file_beta.isFile() && file_beta.exists()) {  
	        file_beta.delete();  
	        flag_beta = true;  
	    }  
	    return flag_beta;  
	}  
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag_beta = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag_beta = deleteFile(files[i].getAbsolutePath());  
	            if (!flag_beta) break;  
	        } //删除子目录  
	        else {  
	            flag_beta = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag_beta) break;  
	        }  
	    }  
	    if (!flag_beta) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  


	public static long getAutoSize(String path)
	{
		long size=0;
		try {
			File file=new File(path);
			if(file.isDirectory())
			{
				getFileSizes(file);
			}else
			{
				getFileSize(file);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	
	 
	/*** 获取文件大小 ***/
	public static long getFileSizes(File f) throws Exception {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	/*** 获取文件夹大小 ***/
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}
	
	
	
}
