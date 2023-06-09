package com.DiamondCafe.DiamondCafe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DiamondCafe.DiamondCafe.bean.DoanhThu;
import com.DiamondCafe.DiamondCafe.bean.HoaDon;
import com.DiamondCafe.DiamondCafe.bean.Mon;
import com.DiamondCafe.DiamondCafe.bean.NhanVien;
import com.DiamondCafe.DiamondCafe.bean.Order;
import com.DiamondCafe.DiamondCafe.service.HoaDonService;
import com.DiamondCafe.DiamondCafe.service.NhanVienDoanhThuService;
import com.DiamondCafe.DiamondCafe.service.NhanVienSoDoChinhService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class NhanVienSoDoChinhController {
	
	@Autowired
	private NhanVienSoDoChinhService sdcService;
	
	@Autowired
	private HoaDonService HoaDonSV;
	
	@Autowired
	private NhanVienDoanhThuService nvdtService;
	
	private double TongTien=0;
	private int giamGia=0;
	private int ID_KhachHang=1;
	
	// Hiển thị màn hình sơ đồ chính
	@GetMapping
	public String Home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			// Xóa danh sách order
			session.removeAttribute("order");
			request.setAttribute("listTable", sdcService.getAllTables());
			return "Employee/PhieuDatMon/index";
		}
		else
			return "redirect:/";
	}
	
	// Hiển thị form thêm phiếu đặt món
	@GetMapping("themphieu/{id}/{ID_LoaiMon}")
	public String Create(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("ID_LoaiMon") int id_loaimon) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			if(request.getParameter("searchValue")!=null) {
				String search = request.getParameter("searchValue");
				request.setAttribute("listCustomer", sdcService.searchCustomer(search));
				request.setAttribute("searchForm", true);
			}
			else
				request.setAttribute("listCustomer", sdcService.getListCustomer());
			request.setAttribute("SoBan", id);
			request.setAttribute("listCategories", sdcService.getAllCategories());
			request.setAttribute("IDLoai", id_loaimon);
			request.setAttribute("listProduct", sdcService.getListProduct(id_loaimon));
			
			// Nếu phiếu order trống | Tính tổng tiền
			if(session.getAttribute("order")==null) {
				List<Order> order = new ArrayList<>();
				request.setAttribute("total", TongTien);
				session.setAttribute("order", order);
				request.setAttribute("giamGia", giamGia);
			}
			else {
				List<Order> order = (List<Order>) session.getAttribute("order");
				if(giamGia==0)
					TongTien = sdcService.TotalMoney(order);
				request.setAttribute("giamGia", giamGia);
				request.setAttribute("total", TongTien);
			}
			return "Employee/PhieuDatMon/themphieu";
		}
		else
			return "redirect:/";
	}
	
	// Thêm món vào phiếu Order
	@GetMapping("themphieu/{id}/{id_loai}/add")
	public String AddProduct(HttpServletRequest request, @PathVariable("id") int id_ban, @PathVariable("id_loai") int id_loai) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			// get thông tin order
			List<Order> list = (List<Order>) session.getAttribute("order");
			int MaMon = Integer.parseInt(request.getParameter("MaMon"));
			Mon m = sdcService.getProduct(MaMon);
			Order o = new Order(m.getMaMon(), m.getTenMon(), 1, m.getGiaBan(), "");
			sdcService.AddToOrder(o, list);
			// cập nhật lại biến session
			session.setAttribute("order", list);
			return "redirect:/home/themphieu/" + id_ban + "/" + id_loai;
		}
		else
			return "redirect:/";
	}
	
	// Cập nhật thông tin order
	@PostMapping("themphieu/{id}/{id_loai}/save")
	public String Save(HttpServletRequest request, @PathVariable("id") int id_ban, @PathVariable("id_loai") int id_loai) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			int MaMon = Integer.parseInt(request.getParameter("MaMon"));
			int SoLuong = Integer.parseInt(request.getParameter("SoLuong"));
			String GhiChu = request.getParameter("GhiChu");
			// Cập nhật lại biến session order
			List<Order> list = (List<Order>) session.getAttribute("order");
			for (Order o : list) {
				if(o.getMaMon()==MaMon) {
					o.setSoLuong(SoLuong);
					o.setGhiChu(GhiChu);
				}
			}
			session.setAttribute("order", list);
			return "redirect:/home/themphieu/" + id_ban + "/" + id_loai;
		}
		else
			return "redirect:/";
	}
	
	// Áp dụng thành viên
	@GetMapping("themphieu/{id}/{id_loai}/{id_khachhang}/applyMember")
	public String ApplyMember(HttpServletRequest request, @PathVariable("id") int id_ban, @PathVariable("id_loai") int id_loai, @PathVariable("id_khachhang") int id_khachhang) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			List<Order> list = (List<Order>) session.getAttribute("order");
			double total = Double.parseDouble(request.getParameter("total"));
			total -= (total*0.1);
			TongTien = total;
			giamGia=10;
			ID_KhachHang=id_khachhang;
			return "redirect:/home/themphieu/" + id_ban + "/" + id_loai;
		}
		else
			return "redirect:/";
	}
	
	// Xóa toàn bộ món 
	@GetMapping("themphieu/{id}/deleteAll")
	public String DeleteAll(HttpServletRequest request, @PathVariable("id") int id_ban) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			session.removeAttribute("order");
			TongTien=0;
			giamGia=0;
			return "redirect:/home/themphieu/" + id_ban + "/1";
		}
		else
			return "redirect:/";
	}
	
	// Xóa món
	@GetMapping("themphieu/{id}/{id_loai}/delete")
	public String Delete(HttpServletRequest request, @PathVariable("id") int id_ban, @PathVariable("id_loai") int id_loai) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			int id_mon = Integer.parseInt(request.getParameter("IDMon"));
			List<Order> list = (List<Order>) session.getAttribute("order");
			List<Order> newlist = sdcService.DeleteProductFromOrder(id_mon, list);
			session.setAttribute("order", newlist);
			return "redirect:/home/themphieu/" + id_ban + "/" + id_loai;
		}
		else
			return "redirect:/";
	}
	
	// Thêm phiếu order vào csdl
	@GetMapping("themphieu/{id}/{id_loai}/saveOrder")
	public String SaveOrder(HttpServletRequest request, @PathVariable("id") int id_ban, @PathVariable("id_loai") int id_loai) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			List<Order> list = (List<Order>) session.getAttribute("order");
			if(list!=null) {
				NhanVien nv = (NhanVien) session.getAttribute("tk");
				System.out.println(ID_KhachHang);
				int id_hoadon = sdcService.AddOrder(id_ban, giamGia, ID_KhachHang, nv.getMaTK());
				
				sdcService.AddOrderDetail(list, id_hoadon);
			}
			return "redirect:/home";
		}
		else
			return "redirect:/";
	}
	
	// Làm trống bàn khi khách đi
	@GetMapping("emptyTable/{id}")
	public String EmptyTable(HttpServletRequest request, @PathVariable("id") int SoBan) {
		sdcService.EmptyTable(SoBan);
		return "redirect:/home";
	}
	
	@GetMapping("inlaihoadon")
	public String InLaiHoaDon(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String key=request.getParameter("key");
		
		if(session.getAttribute("tk")!=null) {
			if(key!="" && key!=null) {
				List<HoaDon> list=HoaDonSV.Tim(key);
				request.setAttribute("listHD", list);
				return "Employee/InLaiHoaDon/index";
			}else {
				List<HoaDon> list=HoaDonSV.GetHD();
				request.setAttribute("listHD", list);
				return "Employee/InLaiHoaDon/index";
			}
			
		}
		else
			return "redirect:/";
		
	}
	
	@GetMapping("xemchitiet/{sohd}")
	public String XemChiTiet(HttpServletRequest request, @PathVariable("sohd") int sohd) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			List<HoaDon> list=HoaDonSV.GetCTHD(sohd);
			double sum=0;
			for(HoaDon hd : list) {
				sum+=hd.getThanhTien();
			}
			request.setAttribute("listCTHD", list);
			request.setAttribute("TongTien", sum);
			return "Employee/InLaiHoaDon/xemchitiet";
		}
		else
			return "redirect:/";
	}
	
	@GetMapping("doanhthu")
	public String DoanhThu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("tk")!=null) {
			List<DoanhThu> DoanhThu = nvdtService.doanhthu();
			double sum=0;
			for( DoanhThu doanhthu: DoanhThu) {
				sum = sum + doanhthu.getThanhTien();
				
			}
			request.setAttribute("tongtien",sum );
			request.setAttribute("listTable",nvdtService.doanhthu() );
			return "Employee/DoanhThu/index";
		}
		else
			return "redirect:/";
	}

}
