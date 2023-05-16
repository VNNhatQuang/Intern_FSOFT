package com.DiamondCafe.DiamondCafe.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @CREATED 04/05/2023 - 5:04 PM
 * @PROJECT Project
 * @AUTHOR Kiel
 */
@Data
@Getter
@Setter
public class Khachhang {
    private int MaKH;
    private String Hoten;
    private String SDT;
    
	public Khachhang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Khachhang(int maKH, String hoten, String sDT) {
		super();
		MaKH = maKH;
		Hoten = hoten;
		SDT = sDT;
	}

	public int getMaKH() {
		return MaKH;
	}

	public void setMaKH(int maKH) {
		MaKH = maKH;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
    
    
}
