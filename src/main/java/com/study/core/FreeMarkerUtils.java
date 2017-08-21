package com.study.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {

//	public static final String JAVA_PATH = "src/main/java/";
//	public static final String RESOURCES_PATH = "src/main/resources/";
//	public static final String BASICPACKAGE_PATH ="cc/monkeylau";
	

	public static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	static {
		// 设置模板所在路径
		cfg.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtils.class, "/ftl-springboot"));
		cfg.setDefaultEncoding("UTF-8");
	}

	/**
	 * 输出模板
	 * 
	 * @param dataModel
	 *            数据模型
	 * @param templateFileName
	 *            模板文件名 eg:xxx.ftl
	 * @param outPutFileName
	 *            生成文件名 eg:xxx.java
	 */
	public static void process(Map dataModel, String templateFileName, String outPutFileName) {
		FileOutputStream fos;
		try {
			String name = mkdirs(outPutFileName);
			fos = new FileOutputStream(name);
			Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
			cfg.getTemplate(templateFileName).process(dataModel, out);
			System.out.println("process template :"+name+" successfully!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成Entity类
	 * @param dataModel
	 * @param templateFileName
	 * @param outPutFileName
	 */
	public static void processEntity(Map dataModel, String templateFileName, String outPutFileName) {
		process(dataModel, templateFileName, Config.JAVA_PATH+Config.BASICPACKAGE_PATH+"entity/"+outPutFileName+".java");
	}
	
	/**
	 * 生成DAO接口
	 * @param dataModel
	 * @param templateFileName
	 * @param outPutFileName
	 */
	public static void processDAO(Map dataModel, String templateFileName, String outPutFileName) {
		process(dataModel, templateFileName, Config.JAVA_PATH+Config.BASICPACKAGE_PATH+"dao/"+outPutFileName+"DAO.java");
	}
	
	/**
	 * 生成属性文件
	 * @param templateFileName
	 */
	public static void processApplictaionProperties(String templateFileName) {
		process(null, templateFileName, Config.RESOURCES_PATH+"application.properties");
	}
	
	/**
	 * 生成不存在的目录
	 * @param path
	 * @return
	 */
	private static String mkdirs(String path) {

		String dirs = path.substring(0, path.lastIndexOf('/'));
		File file = new File(dirs);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}


}
