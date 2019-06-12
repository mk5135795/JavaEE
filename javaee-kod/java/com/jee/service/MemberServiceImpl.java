package com.jee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jee.model.Member;
import com.jee.repository.MemberRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
 
 @Autowired
 private MemberRepository memberRepository;
 

 @Override
 public void subscribe(int user_id, int event_id){
	 Member member = new Member();
	 member.setMember(user_id);
	 member.setEvent(event_id);
	  memberRepository.save(member);
	 }
 
 @Override
 public void unsubscribe(Integer id){
	  memberRepository.deleteById(id);
	 }
 
 @Override
 public List<Member> GetAllMembers() {
 	return (List<Member>) memberRepository.findAll();
 } 
}