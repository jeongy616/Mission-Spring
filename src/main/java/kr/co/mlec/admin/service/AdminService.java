package kr.co.mlec.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	List<Map<String,Object>> selectCustomer(int pageNo);
	int selectCustomerCount();
	List<Map<String,Object>> selectFile(int pageNo);
	int selectFileCount();
	
	List<Map<String,Object>> selectFileByKeyword(int pageNo,String type,String keyword);
	int selectFileByKeyword(String type,String keyword);

	//COFIX
	Map<String, Object> selectCofix(String date);
	
	Map<String,Object> selectOneCustomer(String no);
	
	Map<String,Object> selectCofixByDate(String date);
	String addCofix(Map<String,String> data);
	
	//Item
	void addItem(Map<String,String> data);
	
	
	void updateFileStatus(Map<String,Integer> map);
}
