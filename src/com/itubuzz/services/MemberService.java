package com.itubuzz.services;

import java.util.List;

import com.itubuzz.valueobjects.GroupSearchVO;
import com.itubuzz.dao.GroupDAO;
import com.itubuzz.valueobjects.UserVO;

public class MemberService {

	public List<UserVO> fetchGroupMembers(GroupSearchVO criteria){
		return GroupDAO.fetchGroupMembers(criteria);
	}
	
	public void addMembers(Integer gid,String emailIds){
		GroupDAO.addMembersToGroup(gid,emailIds);
	}

}
