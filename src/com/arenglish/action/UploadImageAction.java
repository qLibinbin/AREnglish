package com.arenglish.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.arenglish.domain.Image;
import com.arenglish.service.UploadService;
import com.arenglish.utils.UUIDUtils;

import com.opensymphony.xwork2.ActionSupport;

public class UploadImageAction extends ActionSupport{
	
	private InputStream inputStream;
	
	// 上传文件域
    private File image;
    private UploadService uploadService;
    
    public String upload() throws Exception {
        
        FileOutputStream fos = null;
        FileInputStream fis = null;
        Image oneImage=null;
        try {
        	//准备图片信息
        	oneImage=new Image();
            oneImage.setId(UUIDUtils.getUUID());
            oneImage.setTag(0);
            oneImage.setUndefinedName("");
            //处理图片，进行存储
            String realPath = ServletActionContext.getServletContext()
                    .getRealPath("/images");
            File destFile = new File(new File(realPath), oneImage.getId()+".png");
            
            if(!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(destFile);
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            
           
            //设定路径，存储到数据库
            String webPath=destFile.getPath().substring(destFile.getPath().indexOf("AREnglish")+10, destFile.getPath().length());
            oneImage.setPath(webPath);
            uploadService.saveImage(oneImage);
            
            System.out.println("Image upload success");
        } catch (Exception e) {
            System.out.println("Image upload failed");
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
        return "success";
    }

    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

	
	
    

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	
	
	
}
