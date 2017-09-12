package ${tableData.basicPackageName}entity;

import javax.persistence.*;

@Entity
public class ${tableData.className} {

	<#list tableData.columns as column >
		<#if column.comment != "">
		/**
	 	 * ${column.comment}
	 	 */
		</#if>
		<#if column.columnName == tableData.primaryKeyColumn>
		@Id
		@GeneratedValue
		</#if>
		private ${column.fieldType} ${column.fieldName};
	</#list>

	

	public ${tableData.className}() {
		super();
	}
	
	<#list tableData.columns as column >
	public ${column.fieldType} get${column.methodName}() {
		return ${column.fieldName};
	}
	
	public void set${column.methodName}(${column.fieldType} ${column.fieldName}) {
		this.${column.fieldName} = ${column.fieldName};
	}
	
	</#list>

}
