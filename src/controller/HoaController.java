package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.ErrorMessage;
import service.HoaService;
import bean.ErrorHoaBean;
import bean.HoaBean;

/**
 * Servlet implementation class HoaController
 */
public class HoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HoaService hoaService;

    /**
     * Default constructor. 
     */
    public HoaController() {
    	this.hoaService = new HoaService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/hoa/addhoa.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		ErrorHoaBean errHoaBean = null;
		ArrayList<HoaBean> arrHoa = null;
		try {
			HoaBean hoaBean = this.hoaService.getParameter(request);
			errHoaBean = this.hoaService.checkParameter(request);
			if (errHoaBean != null ) {
				request.setAttribute("errors", errHoaBean);
				request.setAttribute("hoaBean", hoaBean);
				rd = request.getRequestDispatcher("/jsp/hoa/addhoa.jsp");
				rd.forward(request, response);
				return;
			}
			arrHoa = (ArrayList<HoaBean>) session.getAttribute("arrHoa");
			if (arrHoa == null) {
				arrHoa = new ArrayList<HoaBean>();
			}
			for (HoaBean hoa : arrHoa) {
				if (hoaBean.getId().equals(hoa.getId())) {
					errHoaBean = new ErrorHoaBean();
					errHoaBean.setErrId("Id hoa "
							.concat(ErrorMessage.ERR_DUPLICATE));
					request.setAttribute("errors", errHoaBean);
					request.setAttribute("hoaBean", hoaBean);
					rd = request.getRequestDispatcher("/jsp/hoa/addhoa.jsp");
					rd.forward(request, response);
					return;
				}
			}
			arrHoa.add(hoaBean);
			session.setAttribute("arrHoa", arrHoa);
			rd = request.getRequestDispatcher("/jsp/hoa/addhoa.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("err", "Có lỗi xảy ra ở server.");
		}
	}

}
