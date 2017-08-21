package com.study.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
    	DBHelper dbHelper = new DBHelper();
    	List<Table> tableInfos = dbHelper.getTableInfos();
    	for (Table table : tableInfos) {
    		Map<String, Table> dataMap = new HashMap<>();
    		dataMap.put("tableData", table);
    		FreeMarkerUtils.processEntity(dataMap, "Entity.ftl", table.getClassName());
    		FreeMarkerUtils.processDAO(dataMap, "DAO.ftl", table.getClassName());
    		FreeMarkerUtils.processApplictaionProperties("application-properties.ftl");
		}
    }
}
