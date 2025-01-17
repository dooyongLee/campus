package com.campus.board.free.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.board.free.model.service.FreeBoardService;
import com.campus.board.free.model.service.FreeBoardServiceImpl;
import com.campus.board.free.model.vo.FreeBoard;
import com.campus.board.free.model.vo.FreePage;

/**
 * Servlet implementation class FreeBoardSelectOneServlt
 */
@WebServlet(name = "FreeBoardSelectOneServlet", urlPatterns = { "/board/free/selectOne.do" })
public class FreeBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		int commentPage;
		
		if(request.getParameter("commentPage")==null) {
			commentPage=1;
		}else {
			commentPage = Integer.parseInt(request.getParameter("commentPage"));
		}
		
		
		FreeBoardService freebService = new FreeBoardServiceImpl();
		FreeBoard freeBoard = freebService.freeboardSelectOne(freeNo);
		
		HashMap<String,Object> map = freebService.freeboardComment(freeNo,commentPage,currentPage);
		
		if (freeBoard != null) {
			RequestDispatcher view = request.getRequestDispatcher("/community/free/freepost.jsp");
			request.setAttribute("freeBoard", freeBoard);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("map", map);
			request.setAttribute("commentPage", commentPage);
			view.forward(request, response);

		} else {
			response.sendRedirect("/main/error/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
