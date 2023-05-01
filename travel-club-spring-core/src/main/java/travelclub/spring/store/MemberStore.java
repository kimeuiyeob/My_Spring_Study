package travelclub.spring.store;

import travelclub.spring.aggregate.club.CommunityMember;

import java.util.List;

public interface MemberStore {
    //CommunityMember생성
    String create(CommunityMember member);

    //ID로 CommunityMember찾기
    CommunityMember retrieve(String memberId);

    //이메일로 CommunityMember찾기
    CommunityMember retrieveByEmail(String email);

    //이름으로 CommunityMember찾기
    List<CommunityMember> retrieveByName(String name);

    //CommunityMember수정하기
    void update(CommunityMember member);

    //Id로 CommunityMember삭제하기
    void delete(String memberId);

    //Id로 CommunityMember존재 유무 확인
    boolean exists(String memberId);
}
