package travelclub.spring.spring.web.store;

import travelclub.spring.spring.web.aggregate.club.CommunityMember;

import java.util.List;

public interface MemberStore {
    String create(CommunityMember member);

    CommunityMember retrieve(String memberId);

    CommunityMember retrieveByEmail(String email);

    List<CommunityMember> retrieveByName(String name);

    //모든 CommunityMember 리스트로 조회
    List<CommunityMember> retrieveAll();

    void update(CommunityMember member);

    void delete(String email);

    boolean exists(String memberId);
}
