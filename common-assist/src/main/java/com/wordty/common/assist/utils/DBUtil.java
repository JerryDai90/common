package com.wordty.common.assist.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

	public static Connection getConnection(Type type, String driver,String url,String username,String password) throws SQLException, NamingException, IllegalAccessException{
		if( type == Type.jdbc ) {
			return JDBCUtil.getConnection(driver, url, username, password);
		}
		else if( type == Type.jndi ) {
			return JNDIUtil.getConnection(url, driver);
		}
		else {
			throw new IllegalAccessException("unknow type:" + type);
		}
	}


	public static class JDBCUtil {

		private static final Map<String, DataSource> dbCache = new ConcurrentHashMap<String, DataSource>();//Cache dbCache = new Cache(1000L*60*60, 1000L*60*10);

		private static String getValidationQuery(String driverClassName) {
			if( driverClassName.toLowerCase().indexOf("oracle") != -1 ) {
				return "select 1 from dual";
			}
			else if( driverClassName.toLowerCase().indexOf("sqlserver") != -1 ) {
				return "select 1";
			}
			else if( driverClassName.toLowerCase().indexOf("mysql") != -1 ) {
				return "select 1";
			}
			else if( driverClassName.toLowerCase().indexOf("db2") != -1 ) {
				return "select 1 from SYSIBM.SYSDUMMY1";
			}
			throw new RuntimeException("unsupport yet.");
		}

		public static DataSource getDataSource(String driver,String url,String username,String password){
			String key = driver + url + username + password;
			BasicDataSource ds = (BasicDataSource)dbCache.get(key);
			if( ds == null ){
				synchronized(DBUtil.class){
					if( dbCache.get(key) == null ){
						ds = new BasicDataSource();
						ds.setDriverClassName(driver);
						ds.setUrl(url);
						ds.setUsername(username);
						ds.setPassword(password);

						ds.setRemoveAbandoned(true);
						ds.setTestOnBorrow(true);
						ds.setTestOnReturn(true);
						ds.setTestWhileIdle(true);
						ds.setValidationQuery( getValidationQuery(ds.getDriverClassName()) );

						dbCache.put(key, ds);
					}
				}
			}
			return dbCache.get(key);
		}

		public static Connection getConnection(String driver,String url,String username,String password) throws SQLException {
			return getDataSource(driver, url, username, password).getConnection();
		}

	}

	public static class JNDIUtil {
		public static DataSource getDataSource(String name, String initialContextFactory) throws NamingException {
			Context ctx = null;
			if( !isBlank(initialContextFactory) ) {
				Hashtable<String, String> parms = new Hashtable<String, String>();
				parms.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
				ctx = new InitialContext(parms);
			}
			else {
				ctx = new InitialContext();
			}
			return (DataSource) ctx.lookup(name);
		}
		public static Connection getConnection(String name, String initialContextFactory) throws SQLException, NamingException {
			return getDataSource(name, initialContextFactory).getConnection();
		}
	}


	/**
	 * �ṩ�ر���Դ���ͣ�ResultSet��Statement��Connection
	 * @param res
	 */
	public static void close(Object... res){
		ResourceCloseUtil.close(res);
	}

	private static boolean isBlank(Object object) {
		return object == null || object.toString().equals("");
	}

	public static enum Type {
		jdbc, jndi;

		public static Type transform(String type) {
			if( jndi.toString().equals(type) ) {
				return jndi;
			}
			else {
				return jdbc;
			}
		}
	}

}
