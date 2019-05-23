package com.sh.notice;

import com.sh.upload.UploadDTO;

public class NoticeDTO {

	private int num;
	private String title;
	private String contents;
	private String writer;
	private String reg_date;
	private int hit;
	private UploadDTO uploadDTO;
	
	public UploadDTO getUploadDTO() {
		return uploadDTO;
	}
	public void setUploadDTO(UploadDTO uploadDTO) {
		this.uploadDTO = uploadDTO;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
