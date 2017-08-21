package ${tableData.basicPackageName}.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ${tableData.className} {

	<#list tableData.columns as column >
		<#if column.comment != "">
		/**
	 	 * ${column.comment}
	 	 */
		</#if>
		private ${column.fieldType} ${column.columnName};
	</#list>

	

	public ${tableData.className}() {
		super();
	}
	
	<#list tableData.columns as column >
	public ${column.fieldType} get${column.methodName}() {
		return ${column.columnName};
	}
	
	public void set${column.methodName}(${column.fieldType} ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
	
	</#list>

}
