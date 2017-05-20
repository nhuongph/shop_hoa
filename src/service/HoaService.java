package service;

import javax.servlet.http.HttpServletRequest;

import constant.ErrorMessage;
import bean.ErrorHoaBean;
import bean.HoaBean;

public class HoaService {
	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 * @return HoaBean
	 * @throws NumberFormatException
	 */
	public HoaBean getParameter(HttpServletRequest request) throws NumberFormatException {
		HoaBean hoaBean = new HoaBean();
		hoaBean.setId(request.getParameter("id"));
		hoaBean.setTen(request.getParameter("ten"));
		hoaBean.setGia(request.getParameter("gia").toString());
		hoaBean.setSoLuong(request.getParameter("soluong").toString());
		return hoaBean;
	}

	/**
	 * Validate parameter
	 * 
	 * @param request
	 * @return HoaBean|null
	 */
	public ErrorHoaBean checkParameter(HttpServletRequest request) {
		ErrorHoaBean errHoaBean = new ErrorHoaBean();
		String id = request.getParameter("id").toString();
		String ten = request.getParameter("ten").toString();
		String soluong = request.getParameter("soluong").toString();
		String gia = request.getParameter("gia").toString();
		boolean check = true;
		if (id.isEmpty()) {
			errHoaBean.setErrId("Id hoa ".concat(ErrorMessage.ERR_REQUIRED));
			check = false;
		}
		if (ten.isEmpty()) {
			errHoaBean.setErrTen("Tên hoa ".concat(ErrorMessage.ERR_REQUIRED));
			check = false;
		}
		if (soluong.toString().isEmpty()) {
			errHoaBean.setErrSoLuong("Số lượng hoa ".concat(ErrorMessage.ERR_REQUIRED));
			check = false;
		} else if (!isInteger(soluong)) {
			errHoaBean.setErrSoLuong("Số lượng hoa ".concat(ErrorMessage.ERR_NUMBER_FORMAT));
			check = false;
		} else {
			if (Integer.parseInt(soluong) < 0) {
				errHoaBean.setErrSoLuong("Số lượng hoa ".concat(ErrorMessage.ERR_INTEGER_UNSIGNED));
				check = false;
			}
		}
		if (gia.isEmpty()) {
			errHoaBean.setErrGia("Đơn giá hoa ".concat(ErrorMessage.ERR_REQUIRED));
			check = false;
		} else if (!isInteger(gia)) {
			errHoaBean.setErrGia("Đơn giá hoa ".concat(ErrorMessage.ERR_NUMBER_FORMAT));
			check = false;
		} else {
			if (Integer.parseInt(gia) < 0) {
				errHoaBean.setErrGia("Đơn giá hoa ".concat(ErrorMessage.ERR_INTEGER_UNSIGNED));
				check = false;
			}
		}
		return check ? null : errHoaBean;
	}
	
	/**
	 * Check string is the integer
	 * 
	 * @param String s
	 * @return boolean
	 */
	public boolean isInteger(String s) {  
	    return s != null && s.matches("[-+]?\\d+");  
	}

}
