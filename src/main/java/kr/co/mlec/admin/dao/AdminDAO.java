package kr.co.mlec.admin.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.co.mlec.account.vo.FileVO;

public interface AdminDAO {
	List<Map<String,Object>> selectCustomer(int pageNo);
	int selectCustomerCount();
	 List<Map<String,Object>> selectFile(int pageNo);
	int selectFileCount();
	List<Map<String,Object>> selectFileByKeyword(Map<String, String> map);
	int selectFileByKeywordCount(Map<String,String> map);
	
	Map<String, Object> selectCofix(String date);
	List<FileVO>  loadFile(String no);
	
	void updateFileStatus(Map<String,Integer> map);
	
	void addCofix(Map<String,String> data);
	
	void addItem(Map<String,String> map);
	int checkCofix(String date);
}
