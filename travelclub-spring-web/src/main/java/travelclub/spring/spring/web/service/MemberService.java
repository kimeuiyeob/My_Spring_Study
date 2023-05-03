package travelclub.spring.spring.web.service;

import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.service.sdo.MemberCdo;
import travelclub.spring.spring.web.shared.NameValueList;

import java.util.List;

public interface MemberService {
    String registerMember(MemberCdo member);

    CommunityMember findMemberById(String memberId);

    CommunityMember findMemberByEmail(String memberEmail);

    List<CommunityMember> findMembersByName(String name);

    List<CommunityMember> findAll();

    void modifyMember(String memberId, NameValueList member);

    void removeMember(String memberId);
}