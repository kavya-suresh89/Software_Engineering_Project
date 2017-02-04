package com.itubuzz.valueobjects;

import java.util.ArrayList;

public class SearchPostVO {
	
	public ArrayList<PostVO> search_plist=new ArrayList<PostVO>();
	public ArrayList<ReplyVO> search_rlist=new ArrayList<ReplyVO>();
    
	public ArrayList<PostVO> getSearch_plist() {
		return search_plist;
	}
	public void setSearch_plist(ArrayList<PostVO> search_plist) {
		this.search_plist = search_plist;
	}
	public ArrayList<ReplyVO> getSearch_rlist() {
		return search_rlist;
	}
	public void setSearch_rlist(ArrayList<ReplyVO> search_rlist) {
		this.search_rlist = search_rlist;
	}
		
}
