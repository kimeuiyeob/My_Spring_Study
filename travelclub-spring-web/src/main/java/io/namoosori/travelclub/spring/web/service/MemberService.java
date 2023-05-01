package io.namoosori.travelclub.spring.web.service;

import io.namoosori.travelclub.spring.web.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.web.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.web.shared.NameValueList;

import java.util.List;

public interface MemberService {
	//
	String registerMember(MemberCdo member);
	CommunityMember findMemberById(String memberId);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	void modifyMember(String memberId, NameValueList member);
	void removeMember(String memberId);
}