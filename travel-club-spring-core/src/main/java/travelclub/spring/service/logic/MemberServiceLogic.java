package travelclub.spring.service.logic;

import org.springframework.stereotype.Service;
import travelclub.spring.aggregate.club.CommunityMember;
import travelclub.spring.service.MemberService;
import travelclub.spring.service.sdo.MemberCdo;
import travelclub.spring.shared.NameValueList;
import travelclub.spring.store.MemberStore;
import travelclub.spring.util.exception.MemberDuplicationException;
import travelclub.spring.util.exception.NoSuchMemberException;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {

    //여기다 @Autowired어노테이션을 사용해도 된다.
    private MemberStore memberStore;

    //파라미터로 넘어온 memberStore가 생성이되서 Bean에 주입이된다. => Spring IOC, DI
    //즉 => memberStore자리에 MemberMapStore넘어와서 new는 Spring IOC 컨테이너가 해준다.
    //MemberStore인터페이스 구현하고 있는 클래스는 MemberMapStore밖에 없어서 Spring IOC 컨테이너 입장에서는
    //memberStore에 bean객체를 넣어줘야 하는데 MemberStore인터페이스를 구현하고 있는 클래스가 MemberMapStore여서 MemberMapStore를 주입한거다.
    public MemberServiceLogic(MemberStore memberStore) {
        this.memberStore = memberStore;
    }

    // ===============================================================================================
    //새로운 Member생성
    @Override
    public String registerMember(MemberCdo member) {
        //우선 이메일이 이미 MAP에 있는지 확인 => 이메일 중복 불가
        String email = member.getEmail();
        //retrieveByEmail()메서드를 통해 해당 이메일이 있으면 아래 조건문 타고 Null이라면 조건문 건너뛴다.
        CommunityMember foundedMember = memberStore.retrieveByEmail(email);
        if (foundedMember != null) {
            throw new MemberDuplicationException("Member already exists with email : " + email);
        }
        //CommunityMember()생성자를 통해 새로운 foundedMember를 생성한다.
        foundedMember = new CommunityMember(member.getEmail(), member.getName(), member.getPhoneNumber());
        foundedMember.setNickName(foundedMember.getNickName());
        foundedMember.setBirthDay(foundedMember.getBirthDay());
        //이메일 형식 체크
        foundedMember.checkValidation();
        //create()메서드를 통해 foundedMember 생성한다.
        memberStore.create(foundedMember);
        //생성된 아이디를 리턴한다. (ID는 UUID로 랜덤값으로 만들어진다.)
        return foundedMember.getId();
    }

    // ===============================================================================================
    //ID로 CommunityMember찾기
    @Override
    public CommunityMember findMemberById(String memberId) {
        return memberStore.retrieve(memberId);
    }

    // ===============================================================================================
    //Email로 CommunityMember찾기
    @Override
    public CommunityMember findMemberByEmail(String memberEmail) {
        return memberStore.retrieveByEmail(memberEmail);
    }

    // ===============================================================================================
    //Name으로 CommunityMember찾기
    @Override
    public List<CommunityMember> findMembersByName(String name) {
        return memberStore.retrieveByName(name);
    }

    // ===============================================================================================
    //ID로 해당 name과,intro 수정하기
    @Override
    public void modifyMember(String memberId, NameValueList nameValues) {
        CommunityMember foundedMember = memberStore.retrieve(memberId);
        if (foundedMember == null) {
            throw new NoSuchMemberException("No such member with id : " + memberId);
        }
        //modifyValues메서드를 통해 name과,intro 수정
        foundedMember.modifyValues(nameValues);
        //update메서드를 통해 수정완료
        memberStore.update(foundedMember);
    }

    // ===============================================================================================
    //ID로 CommunityMember 삭제하기
    @Override
    public void removeMember(String memberId) {
        if (!memberStore.exists(memberId)) {
            throw new NoSuchMemberException("No such member with id : " + memberId);
        }
        memberStore.delete(memberId);
    }

    // ===============================================================================================
}
