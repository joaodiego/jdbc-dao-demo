package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			conn.setAutoCommit(false);

			st = conn.prepareStatement("delete from department where Id = 5");

			int rows1 = st.executeUpdate();

			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}

			st = conn.prepareStatement("delete from department where Id = 6");

			int rows2 = st.executeUpdate();

			conn.commit();

			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
