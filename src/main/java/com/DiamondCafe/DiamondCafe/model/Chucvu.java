package com.DiamondCafe.DiamondCafe.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @CREATED 16/05/2023 - 3:11 PM
 * @PROJECT Project
 * @AUTHOR Kiel
 */
@Data
@Getter
@Setter
public class Chucvu {
    private int MaChucVu;
    private String TenChucVu;
    
	public Chucvu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chucvu(int maChucVu, String tenChucVu) {
		super();
		MaChucVu = maChucVu;
		TenChucVu = tenChucVu;
	}

	public int getMaChucVu() {
		return MaChucVu;
	}

	public void setMaChucVu(int maChucVu) {
		MaChucVu = maChucVu;
	}

	public String getTenChucVu() {
		return TenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		TenChucVu = tenChucVu;
	}
    
	
    
}
