package com.news.model;

import java.sql.*;
import java.util.*;

//NewsJDBCDAO的SEQUENCE部分程式碼需再修改
public class NewsJDBCDAO implements NewsDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO NEWS (NEWS_NO,NEWS_CLASSIFICATION_NO,NEWS_TITLE,NEWS_CONTENT,ANNOUNCE_DATE,ADMINISTRATOR_NO) VALUES ('N'||LPAD(to_char(news_N_seq.NEXTVAL), 2, '0'), ?, ?, ?,CURRENT_DATE, ?)";
	private static final String UPDATE = 
			"UPDATE NEWS SET NEWS_CLASSIFICATION_NO = ?, NEWS_TITLE = ?, NEWS_CONTENT = ?, ANNOUNCE_DATE = ?, ADMINISTRATOR_NO = ? WHERE NEWS_NO = ?";
	private static final String DELETE = 
			"DELETE FROM NEWS WHERE NEWS_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM NEWS WHERE NEWS_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM NEWS ORDER BY NEWS_NO";
	
	@Override
	public void insert(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, newsVO.getNews_classification_no());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3, newsVO.getNews_content());
			pstmt.setString(4, newsVO.getAdministrator_no());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsVO.getNews_classification_no());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3, newsVO.getNews_content());
			pstmt.setDate(4, newsVO.getAnnounce_date());
			pstmt.setString(5, newsVO.getAdministrator_no());
			pstmt.setString(6, newsVO.getNews_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String news_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, news_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public NewsVO findByPrimaryKey(String news_no) {

		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("NEWS_NO"));
				newsVO.setNews_classification_no(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsVO.setNews_title(rs.getString("NEWS_TITLE"));
				newsVO.setNews_content(rs.getString("NEWS_CONTENT"));
				newsVO.setAnnounce_date(rs.getDate("ANNOUNCE_DATE"));
				newsVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return newsVO;
		
	}

	@Override
	public List<NewsVO> getAll() {

		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("newsNo"));
				newsVO.setNews_classification_no(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsVO.setNews_title(rs.getString("NEWS_TITLE"));
				newsVO.setNews_content(rs.getString("NEWS_CONTENT"));
				newsVO.setAnnounce_date(rs.getDate("ANNOUNCE_DATE"));
				newsVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				list.add(newsVO);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
		
	}

}
