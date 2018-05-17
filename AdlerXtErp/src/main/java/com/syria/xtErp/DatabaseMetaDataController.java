package com.syria.xtErp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.persistence.EntityManager;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DatabaseMetaDataController {

	private final GetDataBaseMetaData metaDataGetter ;
	private final EntityManager entityManager ;
	@Autowired
	public DatabaseMetaDataController(GetDataBaseMetaData metaDataGetter, EntityManager entityManager) {
		this.metaDataGetter = metaDataGetter;
		this.entityManager = entityManager;
	}
	
	@RequestMapping(value = "/tables",method = RequestMethod.GET)
	public Object getTablesNames(){
		Connection connection = entityManager.unwrap(SessionImpl.class).connection();
		try {
			
			DatabaseMetaData metaData = connection.getMetaData();
			return this.metaDataGetter.processMetaData(metaData);
		} catch (SQLException | MetaDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/columns",method = RequestMethod.GET)
	public Object getColumnsName(@RequestParam("tableName") String tableName) {
		Connection connection = entityManager.unwrap(SessionImpl.class).connection();
		try {
			
			DatabaseMetaData metaData = connection.getMetaData();
			return this.metaDataGetter.getcolumnsNames(tableName, metaData);
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
