package ${package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/
@Entity
@Table(name="${table_name_small}")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ${table_name} extends BaseModel implements ICreateListenable,IModifyListenable,IDeleteListenable {
	
    <#if model_column?exists>
        <#list model_column as model>
        	数据库字段名称：${model.columnName}
        	数据库字段类型：${model.columnType}
        	数据库字段首字母小写且去掉下划线字符串：${model.changeColumnName}
        	数据库字段注释：${model.columnComment}
        	
        	
    /**
    *${model.columnComment!}
    */
    <#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
    @Column(name = "${model.columnName}",columnDefinition = "VARCHAR")
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'TIMESTAMP' >
    @Column(name = "${model.columnName}",columnDefinition = "TIMESTAMP")
    private Date ${model.changeColumnName?uncap_first};

    </#if>
        </#list>
    <#else>
    	model_column is no exist!
    </#if>

<#if model_column?exists>
<#list model_column as model>
<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
<#if model.columnType = 'TIMESTAMP' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
</#list>
</#if>

}