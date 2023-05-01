package travelclub.spring.service;

import travelclub.spring.aggregate.club.CommunityMember;
import travelclub.spring.service.sdo.MemberCdo;
import travelclub.spring.shared.NameValueList;

import java.util.List;

public interface MemberService {
	String registerMember(MemberCdo member);
	CommunityMember findMemberById(String memberId);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	void modifyMember(String memberId, NameValueList member);
	void removeMember(String memberId);
}