package com.study.core;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.sun.xml.internal.ws.util.StringUtils;

public class DBHelper {
	
//	private final static String DRIVER = "com.mysql.jdbc.Driver";
//	
//	private final static String SERVERURL = "jdbc:mysql://localhost:3306/";
//	
//	private final static String DATABASE = "taopiaopiao";
//	
//	private final static String USER = "root";
//	
//	private final static String PASSWORD = "root";
	
	private static Connection connection = null;
	
	
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnection(){
		try {
			if(connection != null){
				return connection;
			}else{
				Class.forName(Config.DRIVER);
				connection = (Connection) DriverManager.getConnection(Config.SERVERURL+Config.DATABASE, Config.USER, Config.PASSWORD);
				return connection;
			}
		} catch (SQLException e) {
			System.out.println("连接数据库失败！");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 获取数据库的元数据
	 * @return
	 */
	public DatabaseMetaData getMetaData(){
        try {
        	connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			return databaseMetaData;
			
		} catch (SQLException e) {
			System.out.println("获取数据库元数据失败！");
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取所有表信息
	 * @return
	 */
	public List<Table> getTableInfos(){
		try {
			List<Table> TableInfos = new ArrayList<>();
			DatabaseMetaData databaseMetaData  = getMetaData();
			String [] types = {"TABLE"};
			ResultSet rs = databaseMetaData.getTables(null, null, null,types);
			while (rs.next()) {  
                String tableName = rs.getString("TABLE_NAME");  //表名  
                Table t2 = new Table();
                t2.setBasicPackageName(Config.BASICPACKAGE_PATH.replace('/', '.'));
                t2.setTableName(tableName);
                t2.setClassName(name2UpperCase(tableName));
                //获取主键信息
                ResultSet rs1 = databaseMetaData.getPrimaryKeys(null, null, tableName);
                while(rs1.next()){
                	t2.setPrimaryKeyColumn(rs1.getString("COLUMN_NAME"));
                }
                
                //获取字段信息
                ResultSet rs2 = databaseMetaData.getColumns(null, null, tableName, null);
    			while (rs2.next()) {  
    				//为主键类型赋值
    				if(t2.getPrimaryKeyColumn().equals(rs2.getString("COLUMN_NAME"))){
    					t2.setPrimaryKeyType(dbType2JavaType(rs2.getString("TYPE_NAME")));
    				}
    				
    				com.study.core.Table.Column column = t2.createColumn();
    	            //获取字段名称
    				column.setColumnName(rs2.getString("COLUMN_NAME"));
    	            //转换字段名称，如 sys_name 变成 SysName
    				column.setFieldName(colName2ClassField(rs2.getString("COLUMN_NAME")));
    				//转换方法名
    				column.setMethodName(name2UpperCase(rs2.getString("COLUMN_NAME")));
    				//获取字段类型
    				column.setColumnType(rs2.getString("TYPE_NAME"));
    				//转换字段类型
    				column.setFieldType(dbType2JavaType(rs2.getString("TYPE_NAME")));
    	            //字段在数据库的注释
    				column.setComment(rs2.getString("REMARKS"));
    				
    				t2.getColumns().add(column);
                } 
    			TableInfos.add(t2);
            }
			return TableInfos;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 数据库字段类型转换
	 * @param dbType
	 * @return
	 */
	private String dbType2JavaType(String dbType){
		switch (dbType) {
		case "VARCHAR":
			return "java.lang.String";
			
		case "CHAR":
			return "java.lang.String";
		
		case "BLOB":
			return "java.lang.byte[]";
			
		case "TEXT":
			return "java.lang.String";
			
		case "INT":
			return "java.lang.Integer";

		case "INTEGER":
			return "java.lang.Long";
			
		case "TINYINT":
			return "java.lang.Integer";
			
		case "SMALLINT":
			return "java.lang.Integer";
			
		case "MEDIUMINT":
			return "java.lang.Integer";
			
		case "BIT":
			return "java.lang.Boolean";
			
		case "BIGINT":
			return "java.lang.Long";
		
		case "FLOAT":
			return "java.lang.Float";
			
		case "DOUBLE":
			return "java.math.Double";
			
		case "DECIMAL":
			return "java.math.BigDecimal";
			
		case "BOOLEAN":
			return "java.lang.Integer";
			
		case "ID":
			return "java.lang.Long";
			
		case "DATE":
			return "	java.sql.Date";
			
		case "TIME":
			return "java.sql.Time";
			
		case "DATETIME":
			return "java.sql.Timestamp";
			
		case "TIMESTAMP":
			return "java.sql.Timestamp";
			
		case "YEAR":
			return "java.sql.Date";
				
		default:
			return "java.lang.String";
		}
	}
	
	
	/**
	 * 字段下划线转驼峰式
	 * @param str
	 * @return
	 */
	private String colName2ClassField(String str){
	        StringBuffer sb = new StringBuffer();
	        sb.append(str);
	        int count = sb.indexOf("_");
	        while(count!=0){
	            int num = sb.indexOf("_",count);
	            count = num + 1;
	            if(num != -1){
	                char ss = sb.charAt(count);
	                char ia = (char) (ss - 32);
	                sb.replace(count , count + 1,ia + "");
	            }
	        }
	        String result = sb.toString().replaceAll("_","");
	        return result;
	    }
	
	 /**
	  * 类名首字母大写
	  * @param str
	  * @return
	  */
	private String name2UpperCase(String str){
	        StringBuffer sb = new StringBuffer();
	        sb.append(str);
	        int count = sb.indexOf("_");
	        while(count!=0){
	            int num = sb.indexOf("_",count);
	            count = num + 1;
	            if(num != -1){
	                char ss = sb.charAt(count);
	                char ia = (char) (ss - 32);
	                sb.replace(count , count + 1,ia + "");
	            }
	        }
	        String result = sb.toString().replaceAll("_","");
	        return StringUtils.capitalize(result);
	    }
	
}
