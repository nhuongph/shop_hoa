<%@page import="java.util.ArrayList"%>
<%@page import="bean.HoaBean"%>
<%@page import="bean.ErrorHoaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/layout/header.jsp"></jsp:include>
<title>Thêm vào giỏ Hoa</title>
<div class="row">
	<div class="form-group"></div>
	<div class="form-group">
		<h1 class="text-center">Thêm hoa vào giỏ</h1>
	</div>
	<div class="form-group"></div>
</div>
<form action="<%=request.getContextPath() %>/add-hoa" method="post">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<% 
			ErrorHoaBean errHoaBean = (ErrorHoaBean)request.getAttribute("errors");
			if (errHoaBean != null) {
				HoaBean hoaBean = (HoaBean)request.getAttribute("hoaBean");%>
				<div class="row form-group">
					<label for="id" class="col-md-4 control-label text-right">Id hoa</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="id" placeholder="Id hoa" value="<%=hoaBean.getId() %>" name="id">
						<label class="error col-md-12"><%=errHoaBean.getErrId() == null ? "" : errHoaBean.getErrId() %></label>
					</div>
				</div>
				<div class="row form-group">
					<label for="ten" class="col-md-4 control-label text-right">Tên hoa</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="ten" placeholder="Tên hoa" value="<%=hoaBean.getTen() %>" name="ten">
						<label class="error col-md-12"><%=errHoaBean.getErrTen() == null ? "" : errHoaBean.getErrTen() %></label>
					</div>
				</div>
				<div class="row form-group">
					<label for="soluong" class="col-md-4 control-label text-right">Số lượng</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="soluong" placeholder="Số lượng" value="<%=hoaBean.getSoLuong() %>" name="soluong">
						<label class="error col-md-12"><%=errHoaBean.getErrSoLuong() == null ? "" :  errHoaBean.getErrSoLuong()%></label>
					</div>
				</div>
				<div class="row form-group">
					<label for="gia" class="col-md-4 control-label text-right">Giá</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="gia" placeholder="Giá" value="<%=hoaBean.getGia() %>" name="gia">
						<label class="error col-md-12"><%=errHoaBean.getErrGia() == null ? "" : errHoaBean.getErrGia() %></label>
					</div>
				</div>
				<div class="row form-group">
					<label for="submit" class="col-md-4 control-label"></label>
					<div class="col-sm-8">
						<input type="submit" class="btn btn-success" id="btn_submit" value="Thêm">
					</div>
				</div>
			<% } else { %>
				<div class="row form-group">
					<label for="id" class="col-md-4 control-label text-right">Id hoa</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="id" placeholder="Id hoa" value="" name="id">
					</div>
				</div>
				<div class="row form-group">
					<label for="ten" class="col-md-4 control-label text-right">Tên hoa</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="ten" placeholder="Tên hoa" value="" name="ten">
					</div>
				</div>
				<div class="row form-group">
					<label for="soluong" class="col-md-4 control-label text-right">Số lượng</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="soluong" placeholder="Số lượng" value="" name="soluong">
						
					</div>
				</div>
				<div class="row form-group">
					<label for="gia" class="col-md-4 control-label text-right">Giá</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="gia" placeholder="Giá" value="" name="gia">
					</div>
				</div>
				<div class="row form-group">
					<label for="submit" class="col-md-4 control-label"></label>
					<div class="col-sm-8">
						<input type="submit" class="btn btn-success" id="btn_submit" value="Thêm">
					</div>
				</div>
			<% }%>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<table class="table table-hover table-striped">
				<tr>
					<th>STT</th>
					<th>Id hoa</th>
					<th>Tên hoa</th>
					<th>Số lượng</th>
					<th>Đơn giá</th>
					<th>Thành Tiền</th>
				</tr>
				<% 
				ArrayList<HoaBean> arrHoa = (ArrayList<HoaBean>) session.getAttribute("arrHoa");
				if (arrHoa != null) {
					int stt = 1;
					for (HoaBean hoa : arrHoa) {%>
						<tr>
							<td><%=stt++ %></td>
							<td><%=hoa.getId() %></td>
							<td><%=hoa.getTen() %></td>
							<td><%=hoa.getSoLuong() %></td>
							<td><%=hoa.getGia() %></td>
							<td><%=Integer.parseInt(hoa.getGia()) * Integer.parseInt(hoa.getSoLuong()) %></td>
						</tr>
					<%}
				} %>
			</table>
		</div>
	</div>
</form>
<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>