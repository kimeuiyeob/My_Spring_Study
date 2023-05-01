package travelclub.spring.store.mapstore;

import org.springframework.stereotype.Repository;
import travelclub.spring.aggregate.club.CommunityMember;
import travelclub.spring.store.MemberStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@Repository 어노테이션을 통해 SpringBean에 추가!
@Repository
public class MemberMapStore implements MemberStore {

    //CommunityMember 맵
    private Map<String, CommunityMember> memberMap;

    public MemberMapStore() {
        memberMap = new LinkedHashMap<>();
    }

    //CommunityMember생성
    @Override
    //member파라미터로 넘어온 아이디값을 추출해 MemberMap 맵에 담는다.
    public String create(CommunityMember member) {
        memberMap.put(member.getId(), member);
        return member.getId();
    }

    //ID로 CommunityMember찾기(검색)
    @Override
    public CommunityMember retrieve(String memberId) {
        //키값으로 밸류값 리턴한다.
        return memberMap.get(memberId);
    }

    //해당 이메일로 CommunityMember찾기
    @Override
    public CommunityMember retrieveByEmail(String email) {
        //forEach문으로 email이 같은 밸류 값 리턴
//        for (Map.Entry<String, CommunityMember> member : memberMap.entrySet()) {
//            if(member.getValue().getEmail().equals(email)) {
//                return member.getValue();
//            };
//        }
//        return null;
        //===============================================
        CommunityMember targetMember = null;
        for (CommunityMember member : memberMap.values()) {
            if (member.getEmail().equals(email)) {
                targetMember = member;
                break;
            }
        }
        return targetMember;
    }

    //Name으로 CommunityMember 리스트 리턴하기
    @Override
    public List<CommunityMember> retrieveByName(String name) {
        return memberMap.values()
                .stream().filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }

    //CommunityMember 수정하기
    @Override
    public void update(CommunityMember member) {
        memberMap.put(member.getId(), member);
    }

    //CommunityMember 삭제하기
    @Override
    public void delete(String memberId) {
        memberMap.remove(memberId);
    }

    //Id로 CommunityMember 존재 유무
    @Override
    public boolean exists(String memberId) {
//        return memberMap.containsKey(memberId);
        return Optional.ofNullable(memberMap.get(memberId)).isPresent();
    }

}
