package cn.hsl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.hsl.database.DataBaseDriver;

public abstract class AbstractDAOImpl {
	protected PreparedStatement ps;
	protected Connection conn;
	public AbstractDAOImpl(){
		this.conn = DataBaseDriver.getConnection();
	}
}
