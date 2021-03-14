package kr.co.mlec.account.vo;

public class AccountVO {
	private int no;
	private String customerNo;
	private String accountNo;
	private char guaranteeType;
	private char accountType;
	private String agencyLocation;
	private String accountMoney;
	private int accountPeriod;
	private float accountRate;
	public AccountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AccountVO(String customerNo, char guaranteeType, char accountType,
			String accountMoney, int accountPeriod, float accountRate) {
		super();
		this.customerNo = customerNo;
		this.guaranteeType = guaranteeType;
		this.accountType = accountType;
		this.accountMoney = accountMoney;
		this.accountPeriod = accountPeriod;
		this.accountRate = accountRate;
	}
	public AccountVO(int no, String customerNo, String accountNo, char guaranteeType, char accountType,
			String agencyLocation, String accountMoney, int accountPeriod, float accountRate) {
		super();
		this.no = no;
		this.customerNo = customerNo;
		this.accountNo = accountNo;
		this.guaranteeType = guaranteeType;
		this.accountType = accountType;
		this.agencyLocation = agencyLocation;
		this.accountMoney = accountMoney;
		this.accountPeriod = accountPeriod;
		this.accountRate = accountRate;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public char getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(char guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public char getAccountType() {
		return accountType;
	}
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	public String getAgencyLocation() {
		return agencyLocation;
	}
	public void setAgencyLocation(String agencyLocation) {
		this.agencyLocation = agencyLocation;
	}
	public String getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(String accountMoney) {
		this.accountMoney = accountMoney;
	}
	public int getAccountPeriod() {
		return accountPeriod;
	}
	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	public float getAccountRate() {
		return accountRate;
	}
	public void setAccountRate(float accountRate) {
		this.accountRate = accountRate;
	}
	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", customerNo=" + customerNo + ", accountNo=" + accountNo + ", guaranteeType="
				+ guaranteeType + ", accountType=" + accountType + ", agencyLocation=" + agencyLocation
				+ ", accountMoney=" + accountMoney + ", accountPeriod=" + accountPeriod + ", accountRate=" + accountRate
				+ "]";
	}
}
