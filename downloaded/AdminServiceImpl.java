package com.kh.naturephone.admin.model.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.naturephone.admin.model.dao.AdminDao;
import com.kh.naturephone.boardSurvey.model.vo.Survey_TB;
import com.kh.naturephone.common.Board_TB;
import com.kh.naturephone.common.PageInfo;
import com.kh.naturephone.member.model.vo.Member;
import com.kh.naturephone.report.model.vo.Report;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao aDao;

	@Override
	public int selectMemberListCount() {
		return aDao.selectMemberListCount();
	}

	@Override
	public List<Member> selectMemberList(PageInfo pi) {
		return aDao.selectMemberList(pi);
	}

	@Override
	public int adminQuitMember(int userNo) {
		return aDao.adminQuitMember(userNo);
	}

	@Override
	public void adminChangeGradeMember(Member m, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println(aDao.adminChangeGradeMember(m));
		out.flush();
		out.close();
	}

	@Override
	public Member adminSelectMember(int userNo) {
		return aDao.adminSelectMember(userNo);
	}

	@Override
	public int selectBoardListCount(String category) {
		return aDao.selectBoardListCount(category);
	}

	@Override
	public List<Board_TB> selectBoardList(String category, PageInfo pi) {
		return aDao.selectBoardList(category, pi);
	}

	@Override
	public int selectSurveyListCount() {
		return aDao.selectSurveyListCount();
	}

	@Override
	public List<Survey_TB> selectSurveyList(PageInfo pi) {
		return aDao.selectSurveyList(pi);
	}

	@Override
	public int selectReportListCount() {
		return aDao.selectReportListCount();
	}

	@Override
	public List<Report> selectReportList(PageInfo pi) {
		return aDao.selectReportList(pi);
	}

	@Override
	public int adminDownMember(int userNo) {
		return aDao.updateDownMember(userNo);
	}

}
