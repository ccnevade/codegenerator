package ${tableData.basicPackageName}dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ${tableData.basicPackageName}entity.${tableData.className};

public interface ${tableData.className}DAO extends JpaRepository<${tableData.className}, ${tableData.primaryKeyType} > {

}
