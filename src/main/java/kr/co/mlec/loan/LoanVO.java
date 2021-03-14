package kr.co.mlec.loan;

import java.util.Arrays;

public class LoanVO {
	private int loanPrice; 		//대출금
	private float loanRate;		//대출금리
	private int loanPeriod;		//대출기간
	private int holdingPeriod; //거치기간
	private int totalInterest;  	//총이자액
	
	private int[] payment;			//납입원금
	private int[] interest;			//납입이자
	private int[] accumulatePayment;	//납입원금누계
	private int[] monthlyPayment;//상환금(납입원금+이자)
	private int[] theBalance;		//잔금
	
	
	public LoanVO(int loanPrice, float loanRate, int loanPeriod, int holdingPeriod) {
		super();
		this.loanPrice = loanPrice;
		this.loanRate = loanRate;
		this.loanPeriod = loanPeriod;
		this.holdingPeriod = holdingPeriod;
	}
	
	public LoanVO(int loanPrice, float loanRate, int loanPeriod, int holdingPeriod, int totalInterest, int[] payment,
			int[] interest, int[] totalPayment, int[] monthlyPayment, int[] theBalance) {
		super();
		this.loanPrice = loanPrice;
		this.loanRate = loanRate;
		this.loanPeriod = loanPeriod;
		this.holdingPeriod = holdingPeriod;
		this.totalInterest = totalInterest;
		this.payment = payment;
		this.interest = interest;
		this.accumulatePayment = accumulatePayment;
		this.monthlyPayment = monthlyPayment;
		this.theBalance = theBalance;
	}

	public int getLoanPrice() {
		return loanPrice;
	}
	public void setLoanPrice(int loanPrice) {
		this.loanPrice = loanPrice;
	}
	public float getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(float loanRate) {
		this.loanRate = loanRate;
	}
	public int getLoanPeriod() {
		return loanPeriod;
	}
	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}
	public int getHoldingPeriod() {
		return holdingPeriod;
	}
	public void setHoldingPeriod(int holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}
	public int getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(int totalInterest) {
		this.totalInterest = totalInterest;
	}
	public int[] getPayment() {
		return payment;
	}
	public void setPayment(int[] payment) {
		this.payment = payment;
	}
	public int[] getInterest() {
		return interest;
	}
	public void setInterest(int[] interest) {
		this.interest = interest;
	}
	
	public int[] getAccumulatePayment() {
		return accumulatePayment;
	}

	public void setAccumulatePayment(int[] accumulatePayment) {
		this.accumulatePayment = accumulatePayment;
	}

	public int[] getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(int[] monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public int[] getTheBalance() {
		return theBalance;
	}
	public void setTheBalance(int[] theBalance) {
		this.theBalance = theBalance;
	}
	@Override
	public String toString() {
		return "LoanVO [loanPrice=" + loanPrice + ", loanRate=" + loanRate + ", loanPeriod=" + loanPeriod
				+ ", holdingPeriod=" + holdingPeriod + ", totalInterest=" + totalInterest + ", payment="
				+ Arrays.toString(payment) + ", interest=" + Arrays.toString(interest) + ", totalPayment="
				+ Arrays.toString(accumulatePayment) + ", monthlyPayment=" + Arrays.toString(monthlyPayment)
				+ ", theBalance=" + Arrays.toString(theBalance) + "]";
	}
}
