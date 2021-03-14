package kr.co.mlec.loan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoanService {

	public List<LoanVO> loanMethod(LoanVO loan1,LoanVO loan2,LoanVO loan3){
		List<LoanVO> list = new ArrayList<>();
		
		list.add(loanMethod1(loan1));
		list.add(loanMethod2(loan2));
		list.add(loanMethod3(loan3));
		return list;
	}
	
	/*
	 *1.만기일시상환
	 * 거치상환기간 무시 
	 */
	private LoanVO loanMethod1(LoanVO loan1) {
		int loanPrice = loan1.getLoanPrice();
		float loanRate = loan1.getLoanRate();
		int loanPeriod = loan1.getLoanPeriod();
		int holdingPeriod = loan1.getHoldingPeriod();

		int[] payment = new int[loanPeriod+1]; // 납입원금 (초기값 0)
		int[] interest = new int[loanPeriod+1];//납입이자
		int[] monthlyPayment = new int[loanPeriod+1];//상환금
		int[] theBalance = new int[loanPeriod+1];//잔금 
		int[] accumulatePayment = new int[loanPeriod+1];//납입원금누계
		int monthlyInterest =(int)(loanPrice*((double)loanRate/100/12)); //월이자율로 곱하기
		loan1.setTotalInterest((monthlyInterest * loanPeriod));//총이자액
		
		
		for(int paymentCnt = 1 ; paymentCnt <= loanPeriod;paymentCnt++) {
			if(paymentCnt == loanPeriod) {
				payment[paymentCnt] = loanPrice;
				theBalance[paymentCnt] = 0;
			}else {
				theBalance[paymentCnt] = loanPrice;
			}
			interest[paymentCnt] =monthlyInterest;
			monthlyPayment[paymentCnt] = payment[paymentCnt] + interest[paymentCnt];
		}
		loan1.setPayment(payment);
		loan1.setInterest(interest);
		loan1.setMonthlyPayment(monthlyPayment);
		loan1.setTheBalance(theBalance);
		loan1.setAccumulatePayment(accumulatePayment);
		return loan1;
	}
	/*
	 * 2. 원금균등상환
	 */
	private LoanVO loanMethod2(LoanVO loan2) {
		int loanPrice = loan2.getLoanPrice();
		float loanRate = loan2.getLoanRate();
		int loanPeriod = loan2.getLoanPeriod();
		int holdingPeriod = loan2.getHoldingPeriod();

		int[] payment = new int[loanPeriod+1]; // 납입원금 
		int[] interest = new int[loanPeriod+1];//납입이자
		int[] monthlyPayment = new int[loanPeriod+1];//상환금
		int[] theBalance = new int[loanPeriod+1];//잔금 
		theBalance[0] = loanPrice;
		int[] accumulatePayment = new int[loanPeriod+1];//납입원금누계
		int  payPerMon=(int)(loanPrice/(double)loanPeriod); //대출금을 기간으로 나눈 값
		int totalInterest = 0;
		for(int paymentCnt = 1 ; paymentCnt <= loanPeriod;paymentCnt++) {
			payment[paymentCnt] = payPerMon;
			accumulatePayment[paymentCnt] =  accumulatePayment[paymentCnt-1]+payPerMon;
			theBalance[paymentCnt] = loanPrice-accumulatePayment[paymentCnt];
			interest[paymentCnt] = (int)(theBalance[paymentCnt-1]*((double)loanRate/100/12));
			totalInterest += interest[paymentCnt];
			monthlyPayment[paymentCnt] = payment[paymentCnt] + interest[paymentCnt];
			//마지막회차일때
			if(paymentCnt == loanPeriod) {
				if(theBalance[paymentCnt] != 0) { //0이 아니라면
					int rest = theBalance[paymentCnt];
					theBalance[paymentCnt] -= rest;
					accumulatePayment[paymentCnt] -= -rest;
				}
			}
		}
		loan2.setPayment(payment);
		loan2.setInterest(interest);
		loan2.setMonthlyPayment(monthlyPayment);
		loan2.setTheBalance(theBalance);
		loan2.setAccumulatePayment(accumulatePayment);
		loan2.setTotalInterest(totalInterest);

		return loan2;
	}
	//3.원리금균등상환
	private LoanVO loanMethod3(LoanVO loan3) {
		int loanPrice = loan3.getLoanPrice();
		float loanRate = loan3.getLoanRate();
		int loanPeriod = loan3.getLoanPeriod();
		int holdingPeriod = loan3.getHoldingPeriod();
		
		int[] payment = new int[loanPeriod+1]; // 납입원금 
		int[] interest = new int[loanPeriod+1];//납입이자
		int[] monthlyPayment = new int[loanPeriod+1];//상환금
		int[] theBalance = new int[loanPeriod+1];//잔금 
		theBalance[0] = loanPrice; //잔금 초기값 = 대출금액
		int[] accumulatePayment = new int[loanPeriod+1];//납입원금누계
		int  payPerMon=(int)(loanPrice/(double)loanPeriod); //대출금을 기간으로 나눈 값
		
		//상환금(매월균등) 구하기
		double rate = (double)loanRate/100.0/12.0; //대출에 대한 이자율
		double deno = Math.pow((1+rate),loanPeriod) - 1;
		double num = loanPrice * rate * Math.pow((1+rate),loanPeriod);
		int monthlyResult =(int)Math.floor((num / deno)); //매월 상환할 동일한 금액
		int totalInterest = 0;
		for(int paymentCnt = 1 ; paymentCnt <= loanPeriod;paymentCnt++) {
			monthlyPayment[paymentCnt]= monthlyResult; // 상환금
			interest[paymentCnt] =((int)Math.floor(theBalance[paymentCnt-1] * rate));//납입이자 : 전회차 잔금 의 이자
			totalInterest += interest[paymentCnt];
			payment[paymentCnt] = monthlyPayment[paymentCnt] - interest[paymentCnt]; // 납입원금 :  상환금 - 납입이자
			theBalance[paymentCnt] = theBalance[paymentCnt-1] - payment[paymentCnt]; //잔금 : 전회차 잔금 - 납입원금
			accumulatePayment[paymentCnt] = accumulatePayment[paymentCnt-1] + payment[paymentCnt]; // 납입원금누계 
			//마지막회차일때
			if(paymentCnt == loanPeriod) {
				if(theBalance[paymentCnt] != 0) { //0이 아니라면
					int rest = theBalance[paymentCnt];
					theBalance[paymentCnt] -= rest;
					accumulatePayment[paymentCnt] -= -rest;
				}
			}
		}
		
		loan3.setMonthlyPayment(monthlyPayment);
		loan3.setInterest(interest);
		loan3.setPayment(payment);
		loan3.setTheBalance(theBalance);
		loan3.setAccumulatePayment(accumulatePayment);
		loan3.setTotalInterest(totalInterest);
		return loan3;
	}
}
