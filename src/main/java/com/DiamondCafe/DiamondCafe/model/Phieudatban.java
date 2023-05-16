package com.DiamondCafe.DiamondCafe.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @CREATED 09/05/2023 - 9:58 AM
 * @PROJECT Project
 * @AUTHOR Kiel
 */

@Data
@Getter
@Setter
public class   Phieudatban {
    private int MaPhieu;
    private String TenNguoiDat;
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private Date NgayDat = new Date(new java.util.Date().getTime());

    private String SoDienThoai;
    @DateTimeFormat(pattern = "HH:mm")
    private String GioBatdau = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    private int SoBan;
    private int Trangthai = 0;
    public enum status {
        DANG_DAT_BAN,
        DANG_NHAN_BAN,
        DA_TRA_BAN,
        TRE_HEN

    }

    public status convertTrangthaiNum(int num){
        switch (num) {
            case 0 :
                return status.DANG_DAT_BAN;
            case 1:
                return status.DANG_NHAN_BAN;
            case 2:
                return status.DA_TRA_BAN;
            case 3:
                return status.TRE_HEN;
        }
        return status.DANG_DAT_BAN;
    }
    public static int convertTrangthaiStatus(status TT){
        switch (TT) {
            case DANG_DAT_BAN:
                return 0;
            case DANG_NHAN_BAN:
                return 1;
            case DA_TRA_BAN:
                return 2;
            case TRE_HEN:
                return 3;
        }
        return 0 ;
    }

    public String convertTrangthaiString(int num){
        switch (num) {
            case 0 :
                return "Đang đặt bàn";
            case 1:
                return "Đang nhận bàn";
            case 2:
                return "Đã Trả bàn";
            case 3:
                return "Trễ hẹn";
        }
        return "Đang đặt bàn";
    }

    public String getTime(String time){
        String temp = time.substring(0, 5);
        return temp;
    }

    public String setTime(String time) throws ParseException {
        String temp = time.substring(0, 5);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        new java.sql.Time(formatter.parse(temp).getTime());
        return temp;
    }
	public Phieudatban() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Phieudatban(int maPhieu, String tenNguoiDat, Date ngayDat, String soDienThoai, String gioBatdau, int soBan,
			int trangthai) {
		super();
		MaPhieu = maPhieu;
		TenNguoiDat = tenNguoiDat;
		NgayDat = ngayDat;
		SoDienThoai = soDienThoai;
		GioBatdau = gioBatdau;
		SoBan = soBan;
		Trangthai = trangthai;
	}
	public int getMaPhieu() {
		return MaPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		MaPhieu = maPhieu;
	}
	public String getTenNguoiDat() {
		return TenNguoiDat;
	}
	public void setTenNguoiDat(String tenNguoiDat) {
		TenNguoiDat = tenNguoiDat;
	}
	public Date getNgayDat() {
		return NgayDat;
	}
	public void setNgayDat(Date ngayDat) {
		NgayDat = ngayDat;
	}
	public String getSoDienThoai() {
		return SoDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}
	public String getGioBatdau() {
		return GioBatdau;
	}
	public void setGioBatdau(String gioBatdau) {
		GioBatdau = gioBatdau;
	}
	public int getSoBan() {
		return SoBan;
	}
	public void setSoBan(int soBan) {
		SoBan = soBan;
	}
	public int getTrangthai() {
		return Trangthai;
	}
	public void setTrangthai(int trangthai) {
		Trangthai = trangthai;
	}
    
    
    
}
