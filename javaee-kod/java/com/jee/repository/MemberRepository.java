package com.jee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jee.model.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {
}