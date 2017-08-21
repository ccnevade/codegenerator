package com.study.core;

import java.util.ArrayList;
import java.util.List;

public class Table {
	//基本包名
	private String basicPackageName;
	//表名
	private String tableName;
	//类名
	private String className;
	//主键字段
	private String primaryKeyColumn;
	//主键类型
	private String primaryKeyType;
	//字段集合
	private List<Column> columns;
	
	public Table(){
		this.columns = new ArrayList<>();
	}
	
	
	
	public String getBasicPackageName() {
		return basicPackageName;
	}



	public void setBasicPackageName(String basicPackageName) {
		this.basicPackageName = basicPackageName;
	}



	public String getTableName() {
		return tableName;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}



	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}



	public String getPrimaryKeyType() {
		return primaryKeyType;
	}



	public void setPrimaryKeyType(String primaryKeyType) {
		this.primaryKeyType = primaryKeyType;
	}



	public List<Column> getColumns() {
		return columns;
	}



	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	

	@Override
	public String toString() {
		return "Table [basicPackageName=" + basicPackageName + ", tableName=" + tableName + ", className=" + className
				+ ", primaryKeyColumn=" + primaryKeyColumn + ", columns=" + columns + "]";
	}



	public  Column createColumn(){
		return new Column();
	}

	public  class Column{
		
		//表字段名
		private String columnName;
		//表字段类型
		private String columnType;
		//表字段长度
		private int columnLength;
		//表字段注释
		private String comment;
		
		//类属性名
		private String fieldName;
		//类方法名
		private String methodName;
		//类属性类型
		private String fieldType;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getColumnType() {
			return columnType;
		}
		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}
		public int getColumnLength() {
			return columnLength;
		}
		public void setColumnLength(int columnLength) {
			this.columnLength = columnLength;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		public String getFieldType() {
			return fieldType;
		}
		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}
		@Override
		public String toString() {
			return "Column [columnName=" + columnName + ", columnType=" + columnType + ", columnLength=" + columnLength
					+ ", comment=" + comment + ", fieldName=" + fieldName + ", fieldType=" + fieldType + "]";
		}
		
	}

}
