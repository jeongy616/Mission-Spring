package kr.co.mlec.loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanController {
	@Autowired
	LoanService service;
//	@RequestParam("loanPrice") int price,@RequestParam("loanPeriod") int period,@RequestParam("loanRate") float rate,@RequestParam("holdingPeriod") int holding
	@PutMapping("/loan/result")
	@ResponseBody
	public Map<String,Object> list(@RequestBody Map<String, String> data) {
		System.out.println(data);
		LoanVO loan1 = new LoanVO(Integer.parseInt(data.get("loanPrice"))
											,Float.parseFloat(data.get("loanRate"))
											,Integer.parseInt(data.get("loanPeriod"))
											,Integer.parseInt(data.get("holdingPeriod")));
		LoanVO loan2 = new LoanVO(Integer.parseInt(data.get("loanPrice"))
											,Float.parseFloat(data.get("loanRate"))
											,Integer.parseInt(data.get("loanPeriod"))
											,Integer.parseInt(data.get("holdingPeriod")));
		LoanVO loan3 = new LoanVO(Integer.parseInt(data.get("loanPrice"))
											,Float.parseFloat(data.get("loanRate"))
											,Integer.parseInt(data.get("loanPeriod"))
											,Integer.parseInt(data.get("holdingPeriod")));

		
		List<LoanVO> loanlist = new ArrayList<>();
		loanlist = service.loanMethod(loan1,loan2,loan3);
		
		Map<String,Object> map = new HashMap<>();
		map.put("loan",loanlist);
		return map;
	}
}
