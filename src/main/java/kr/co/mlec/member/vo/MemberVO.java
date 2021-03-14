package kr.co.mlec.member.vo;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class MemberVO {
	private int no;
	private String customerNo;
	@Pattern(regexp="^[0-9a-z]{4,20}$",message="특수기호를 사용할 수 없습니다.")
	private String id;
	@Pattern(regexp="^[A-Za-z0-9]{6,20}$",message="숫자와 문자 포함 형태의 6~20자리 이내로 입력하세요.")
	private String password;
	@Pattern(regexp="^[A-Za-z0-9]{6,20}$",message="숫자와 문자 포함 형태의 6~20자리 이내로 입력하세요.")
	private String passwordCheck;
	private String name;
	private String birth;
	private String address;
	private String email;
	private String phoneNumber;
	private String regDate;
	private String userType;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(int no, String id, String password, String name, String regDate) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}

	public MemberVO(int no, String customerNo,
			@Pattern(regexp = "^[0-9a-z]{4,20}$", message = "특수기호를 사용할 수 없습니다.") String id,
			@Pattern(regexp = "^[A-Za-z0-9]{6,20}$", message = "숫자와 문자 포함 형태의 6~20자리 이내로 입력하세요.") String password,
			@Pattern(regexp = "^[A-Za-z0-9]{6,20}$", message = "숫자와 문자 포함 형태의 6~20자리 이내로 입력하세요.") String passwordCheck,
			String name, String birth, String address, String email, String phoneNumber, String regDate,
			String userType) {
		super();
		this.no = no;
		this.customerNo = customerNo;
		this.id = id;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.regDate = regDate;
		this.userType = userType;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", customerNo=" + customerNo + ", id=" + id + ", password=" + password
				+ ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", regDate=" + regDate + "]";
	}
}
