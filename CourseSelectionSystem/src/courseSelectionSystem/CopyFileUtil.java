package courseSelectionSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * A class that supports file or folder copying operation. The source code
 * comes from the Internet.
 */
class CopyFileUtil {

	/** 
	 * ���Ƶ����ļ� 
	 *  
	 * @param srcFileName 
	 *            �����Ƶ��ļ��� 
	 * @param descFileName 
	 *            Ŀ���ļ��� 
	 * @param overlay 
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸� 
	 * @return ������Ƴɹ�����true�����򷵻�false 
	 */  
	public static boolean copyFile(String srcFileName, String destFileName,  
			boolean overlay) {  
		File srcFile = new File(srcFileName);  

		// �ж�Դ�ļ��Ƿ����  
		if (!srcFile.exists()) {  
			return false;  
		} else if (!srcFile.isFile()) {  
			return false;  
		}  

		// �ж�Ŀ���ļ��Ƿ����  
		File destFile = new File(destFileName);  
		if (destFile.exists()) {  
			// ���Ŀ���ļ����ڲ�������  
			if (overlay) {  
				// ɾ���Ѿ����ڵ�Ŀ���ļ�������Ŀ���ļ���Ŀ¼���ǵ����ļ�  
				new File(destFileName).delete();  
			}  
		} else {  
			// ���Ŀ���ļ�����Ŀ¼�����ڣ��򴴽�Ŀ¼  
			if (!destFile.getParentFile().exists()) {  
				// Ŀ���ļ�����Ŀ¼������  
				if (!destFile.getParentFile().mkdirs()) {  
					// �����ļ�ʧ�ܣ�����Ŀ���ļ�����Ŀ¼ʧ��  
					return false;  
				}  
			}  
		}  

		// �����ļ�  
		int byteread = 0; // ��ȡ���ֽ���  
		InputStream in = null;  
		OutputStream out = null;  

		try {  
			in = new FileInputStream(srcFile);  
			out = new FileOutputStream(destFile);  
			byte[] buffer = new byte[1024];  

			while ((byteread = in.read(buffer)) != -1) {  
				out.write(buffer, 0, byteread);  
			}  
			return true;  
		} catch (FileNotFoundException e) {  
			return false;  
		} catch (IOException e) {  
			return false;  
		} finally {  
			try {  
				if (out != null)  
					out.close();  
				if (in != null)  
					in.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}  

	/** 
	 * ��������Ŀ¼������ 
	 *  
	 * @param srcDirName 
	 *            ������Ŀ¼��Ŀ¼�� 
	 * @param destDirName 
	 *            Ŀ��Ŀ¼�� 
	 * @param overlay 
	 *            ���Ŀ��Ŀ¼���ڣ��Ƿ񸲸� 
	 * @return ������Ƴɹ�����true�����򷵻�false 
	 */  
	public static boolean copyDirectory(String srcDirName, String destDirName,  
			boolean overlay) {  
		// �ж�ԴĿ¼�Ƿ����  
		File srcDir = new File(srcDirName);  
		if (!srcDir.exists()) {  
			return false;  
		} else if (!srcDir.isDirectory()) {  
			return false;  
		}  

		// ���Ŀ��Ŀ¼���������ļ��ָ�����β��������ļ��ָ���  
		if (!destDirName.endsWith(File.separator)) {  
			destDirName = destDirName + File.separator;  
		}  
		File destDir = new File(destDirName);  
		// ���Ŀ���ļ��д���  
		if (destDir.exists()) {  
			// �����������ɾ���Ѵ��ڵ�Ŀ��Ŀ¼  
			if (overlay) {  
				new File(destDirName).delete();  
			} else {  
				return false;  
			}  
		} else {  
			// ����Ŀ��Ŀ¼  
			System.out.println("Ŀ��Ŀ¼�����ڣ�׼������������");  
			if (!destDir.mkdirs()) {  
				System.out.println("����Ŀ¼ʧ�ܣ�����Ŀ��Ŀ¼ʧ�ܣ�");  
				return false;  
			}  
		}  

		boolean flag = true;  
		File[] files = srcDir.listFiles();  
		for (int i = 0; i < files.length; i++) {  
			// �����ļ�  
			if (files[i].isFile()) {  
				flag = CopyFileUtil.copyFile(files[i].getAbsolutePath(),  
						destDirName + files[i].getName(), overlay);  
				if (!flag)  
					break;  
			} else if (files[i].isDirectory()) {  
				flag = CopyFileUtil.copyDirectory(files[i].getAbsolutePath(),  
						destDirName + files[i].getName(), overlay);  
				if (!flag)  
					break;  
			}  
		}  
		if (!flag) {  
			return false;  
		} else {  
			return true;  
		}  
	} 

	/**
	 * Delete a file.
	 * @param sPath The path of the file.
	 * @return Whether the file is successfully deleted.
	 */
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
		File file = new File(sPath);  
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
		if (file.isFile() && file.exists()) {  
			file.delete();  
			flag = true;  
		}  
		return flag;  
	} 
	
	/**
	 * Rename a file.
	 * @param path The path of the old file.
	 * @param newpath The new path of the file.
	 * @param _replace Whether it is allowed to replace a file that already exists.
	 * @return Whether the file is successfully renamed.
	 */
	public static boolean renameFile(String path,String newpath, boolean _replace) { 
		if(!path.equals(newpath)){//�µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ���������� 
			File oldfile=new File(path); 
			File newfile=new File(newpath); 
			if(!oldfile.exists()){
				return false;//�������ļ�������
			}
			if(newfile.exists()) {
				if(_replace) {
					newfile.delete();
					oldfile.renameTo(newfile);
					return true;
				}
				else
					return false;
			}
			else { 
				oldfile.renameTo(newfile); 
				return true;
			} 
		}
		return false;
	}
}
