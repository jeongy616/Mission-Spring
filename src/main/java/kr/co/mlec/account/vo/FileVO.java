package kr.co.mlec.account.vo;

public class FileVO {
	private int no;
	private String customerNo;
	private String originFileName1;
	private String saveFileName1;
	private String originFileName2;
	private String saveFileName2;
	private String originFileName3;
	private String saveFileName3;
	private String regDate;
	private String status;
	public FileVO(int no, String customerNo, String originFileName1, String saveFileName1, String originFileName2,
			String saveFileName2, String originFileName3, String saveFileName3, String regDate, String status) {
		super();
		this.no = no;
		this.customerNo = customerNo;
		this.originFileName1 = originFileName1;
		this.saveFileName1 = saveFileName1;
		this.originFileName2 = originFileName2;
		this.saveFileName2 = saveFileName2;
		this.originFileName3 = originFileName3;
		this.saveFileName3 = saveFileName3;
		this.regDate = regDate;
		this.status = status;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getOriginFileName1() {
		return originFileName1;
	}
	public void setOriginFileName1(String originFileName1) {
		this.originFileName1 = originFileName1;
	}
	public String getSaveFileName1() {
		return saveFileName1;
	}
	public void setSaveFileName1(String saveFileName1) {
		this.saveFileName1 = saveFileName1;
	}
	public String getOriginFileName2() {
		return originFileName2;
	}
	public void setOriginFileName2(String originFileName2) {
		this.originFileName2 = originFileName2;
	}
	public String getSaveFileName2() {
		return saveFileName2;
	}
	public void setSaveFileName2(String saveFileName2) {
		this.saveFileName2 = saveFileName2;
	}
	public String getOriginFileName3() {
		return originFileName3;
	}
	public void setOriginFileName3(String originFileName3) {
		this.originFileName3 = originFileName3;
	}
	public String getSaveFileName3() {
		return saveFileName3;
	}
	public void setSaveFileName3(String saveFileName3) {
		this.saveFileName3 = saveFileName3;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FileVO [no=" + no + ", customerNo=" + customerNo + ", originFileName1=" + originFileName1
				+ ", saveFileName1=" + saveFileName1 + ", originFileName2=" + originFileName2 + ", saveFileName2="
				+ saveFileName2 + ", originFileName3=" + originFileName3 + ", saveFileName3=" + saveFileName3
				+ ", regDate=" + regDate + ", status=" + status + "]";
	}
}

