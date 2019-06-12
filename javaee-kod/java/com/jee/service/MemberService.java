package com.jee.service;

import java.util.List;

import com.jee.model.Member;

public interface MemberService {

	 public void subscribe(int user_id, int event_id);
	 public void unsubscribe(Integer id);
	public List<Member> GetAllMembers();
}
