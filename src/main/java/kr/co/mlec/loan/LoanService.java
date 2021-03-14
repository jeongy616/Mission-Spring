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
	 *1.�����Ͻû�ȯ
	 * ��ġ��ȯ�Ⱓ ���� 
	 */
	private LoanVO loanMethod1(LoanVO loan1) {
		int loanPrice = loan1.getLoanPrice();
		float loanRate = loan1.getLoanRate();
		int loanPeriod = loan1.getLoanPeriod();
		int holdingPeriod = loan1.getHoldingPeriod();

		int[] payment = new int[loanPeriod+1]; // ���Կ��� (�ʱⰪ 0)
		int[] interest = new int[loanPeriod+1];//��������
		int[] monthlyPayment = new int[loanPeriod+1];//��ȯ��
		int[] theBalance = new int[loanPeriod+1];//�ܱ� 
		int[] accumulatePayment = new int[loanPeriod+1];//���Կ��ݴ���
		int monthlyInterest =(int)(loanPrice*((double)loanRate/100/12)); //���������� ���ϱ�
		loan1.setTotalInterest((monthlyInterest * loanPeriod));//�����ھ�
		
		
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
	 * 2. ���ݱյ��ȯ
	 */
	private LoanVO loanMethod2(LoanVO loan2) {
		int loanPrice = loan2.getLoanPrice();
		float loanRate = loan2.getLoanRate();
		int loanPeriod = loan2.getLoanPeriod();
		int holdingPeriod = loan2.getHoldingPeriod();

		int[] payment = new int[loanPeriod+1]; // ���Կ��� 
		int[] interest = new int[loanPeriod+1];//��������
		int[] monthlyPayment = new int[loanPeriod+1];//��ȯ��
		int[] theBalance = new int[loanPeriod+1];//�ܱ� 
		theBalance[0] = loanPrice;
		int[] accumulatePayment = new int[loanPeriod+1];//���Կ��ݴ���
		int  payPerMon=(int)(loanPrice/(double)loanPeriod); //������� �Ⱓ���� ���� ��
		int totalInterest = 0;
		for(int paymentCnt = 1 ; paymentCnt <= loanPeriod;paymentCnt++) {
			payment[paymentCnt] = payPerMon;
			accumulatePayment[paymentCnt] =  accumulatePayment[paymentCnt-1]+payPerMon;
			theBalance[paymentCnt] = loanPrice-accumulatePayment[paymentCnt];
			interest[paymentCnt] = (int)(theBalance[paymentCnt-1]*((double)loanRate/100/12));
			totalInterest += interest[paymentCnt];
			monthlyPayment[paymentCnt] = payment[paymentCnt] + interest[paymentCnt];
			//������ȸ���϶�
			if(paymentCnt == loanPeriod) {
				if(theBalance[paymentCnt] != 0) { //0�� �ƴ϶��
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
	//3.�����ݱյ��ȯ
	private LoanVO loanMethod3(LoanVO loan3) {
		int loanPrice = loan3.getLoanPrice();
		float loanRate = loan3.getLoanRate();
		int loanPeriod = loan3.getLoanPeriod();
		int holdingPeriod = loan3.getHoldingPeriod();
		
		int[] payment = new int[loanPeriod+1]; // ���Կ��� 
		int[] interest = new int[loanPeriod+1];//��������
		int[] monthlyPayment = new int[loanPeriod+1];//��ȯ��
		int[] theBalance = new int[loanPeriod+1];//�ܱ� 
		theBalance[0] = loanPrice; //�ܱ� �ʱⰪ = ����ݾ�
		int[] accumulatePayment = new int[loanPeriod+1];//���Կ��ݴ���
		int  payPerMon=(int)(loanPrice/(double)loanPeriod); //������� �Ⱓ���� ���� ��
		
		//��ȯ��(�ſ��յ�) ���ϱ�
		double rate = (double)loanRate/100.0/12.0; //���⿡ ���� ������
		double deno = Math.pow((1+rate),loanPeriod) - 1;
		double num = loanPrice * rate * Math.pow((1+rate),loanPeriod);
		int monthlyResult =(int)Math.floor((num / deno)); //�ſ� ��ȯ�� ������ �ݾ�
		int totalInterest = 0;
		for(int paymentCnt = 1 ; paymentCnt <= loanPeriod;paymentCnt++) {
			monthlyPayment[paymentCnt]= monthlyResult; // ��ȯ��
			interest[paymentCnt] =((int)Math.floor(theBalance[paymentCnt-1] * rate));//�������� : ��ȸ�� �ܱ� �� ����
			totalInterest += interest[paymentCnt];
			payment[paymentCnt] = monthlyPayment[paymentCnt] - interest[paymentCnt]; // ���Կ��� :  ��ȯ�� - ��������
			theBalance[paymentCnt] = theBalance[paymentCnt-1] - payment[paymentCnt]; //�ܱ� : ��ȸ�� �ܱ� - ���Կ���
			accumulatePayment[paymentCnt] = accumulatePayment[paymentCnt-1] + payment[paymentCnt]; // ���Կ��ݴ��� 
			//������ȸ���϶�
			if(paymentCnt == loanPeriod) {
				if(theBalance[paymentCnt] != 0) { //0�� �ƴ϶��
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
