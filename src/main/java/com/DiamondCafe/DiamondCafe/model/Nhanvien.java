package com.DiamondCafe.DiamondCafe.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.sql.Date;

/**
 * @CREATED 04/05/2023 - 9:23 AM
 * @PROJECT Project
 * @AUTHOR Kiel
 */

@Data
@Getter
@Setter
public class Nhanvien {
    private String MaTK;
    private String Matkhau;
    private String TenNV;
    private Date NgaySinh;
    private String DiaChi;
    private String SDT;
    private String CMT;
    private String ID_ChucVu;

    public Nhanvien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nhanvien(String maTK, String matkhau, String tenNV, Date ngaySinh, String diaChi, String sDT, String cMT,
			String iD_ChucVu) {
		super();
		MaTK = maTK;
		Matkhau = matkhau;
		TenNV = tenNV;
		NgaySinh = ngaySinh;
		DiaChi = diaChi;
		SDT = sDT;
		CMT = cMT;
		ID_ChucVu = iD_ChucVu;
	}

	public String getMaTK() {
		return MaTK;
	}

	public void setMaTK(String maTK) {
		MaTK = maTK;
	}

	public String getMatkhau() {
		return Matkhau;
	}

	public void setMatkhau(String matkhau) {
		Matkhau = matkhau;
	}

	public String getTenNV() {
		return TenNV;
	}

	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getCMT() {
		return CMT;
	}

	public void setCMT(String cMT) {
		CMT = cMT;
	}

	public String getID_ChucVu() {
		return ID_ChucVu;
	}

	public void setID_ChucVu(String iD_ChucVu) {
		ID_ChucVu = iD_ChucVu;
	}



	enum chucvu {
        QUAN_LY,
        THU_NGAN,
        PHUC_VU
    }

    public static String getchucvybyNum(int chucvu){
        switch (chucvu) {
            case 0:
                return "Quản lý";
            case 1:
                return "Thu ngân";
            case 2:
                return "Nhân viên";
        }
        return null;
    }
}
