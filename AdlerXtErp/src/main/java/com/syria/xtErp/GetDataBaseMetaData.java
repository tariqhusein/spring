package com.syria.xtErp;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.ManagedBean;

import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.MetaDataAccessException;

@ManagedBean
public class GetDataBaseMetaData implements DatabaseMetaDataCallback {

	@Override
	public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
		ResultSet rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });

		ArrayList<String> l = new ArrayList<String>();
		while (rs.next()) {
			l.add(rs.getString(3));
		}
		return l;
	}

	public Object getcolumnsNames(String table, DatabaseMetaData dbmd) {
		ResultSet rs;
		ArrayList<String> l= new ArrayList<String>();
		try {
			rs = dbmd.getColumns(null, null, table, null);
			
			while (rs.next()) {
				l.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
		
	}

}