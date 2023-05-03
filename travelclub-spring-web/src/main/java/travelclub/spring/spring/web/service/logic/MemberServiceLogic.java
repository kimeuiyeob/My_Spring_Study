package travelclub.spring.spring.web.service.logic;

import org.springframework.stereotype.Service;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.service.MemberService;
import travelclub.spring.spring.web.service.sdo.MemberCdo;
import travelclub.spring.spring.web.shared.NameValueList;
import travelclub.spring.spring.web.store.MemberStore;
import travelclub.spring.spring.web.util.exception.MemberDuplicationException;
import travelclub.spring.spring.web.util.exception.NoSuchMemberException;

import java.util.List;

//Bean에 등록
@Service
public class MemberServiceLogic implements MemberService {
	private MemberStore memberStore;

	public MemberServiceLogic(MemberStore memberStore) {
		//memberStore 주입
		this.memberStore = memberStore;
	}
	//=====================================================================================
	//멤버 생성하기
	@Override
	public String registerMember(MemberCdo newMemberCdo) {
		//파라미터로 넘어온 newMemberCdo에서 email추출해 email에 담는다.
		String email = newMemberCdo.getEmail();
		//이메일 중복 확인 있으면 해당 member리턴, 없으면 null리턴
		CommunityMember member = memberStore.retrieveByEmail(email);
		//만약 null이아니라면 이미 존재하는 이메일이라 예외처리
		if (member != null) {
			throw new MemberDuplicationException("Member already exists with email: " + member.getEmail());
		}
		//null이라면 해당 같은 이메일이 존재하지 않기때문에 새롭게 객체 생성
		member = new CommunityMember(
				newMemberCdo.getEmail(),
				newMemberCdo.getName(),
				newMemberCdo.getPhoneNumber()
		);
		//닉네임과, 생일까지 셋해준다.
		member.setNickName(newMemberCdo.getNickName());
		member.setBirthDay(newMemberCdo.getBirthDay());
		//이메일 확인까지 하고 create메서드를 통해서 새로운 member객체 생성뒤 ID 리턴
		member.checkValidation();
		memberStore.create(member);
		return member.getId();
	}
	//=====================================================================================
	//Id로 CommunityMember 조회
	@Override
	public CommunityMember findMemberById(String memberId) {
		return memberStore.retrieve(memberId);
	}
	//=====================================================================================
	//이메일 확인
	@Override
	public CommunityMember findMemberByEmail(String memberEmail) {
		return memberStore.retrieveByEmail(memberEmail);
	}
	//=====================================================================================
	//같은 이름으로 CommunityMember전체 조회
	@Override
	public List<CommunityMember> findMembersByName(String name) {
		return memberStore.retrieveByName(name);
	}
	//=====================================================================================
	//CommunityMember 전체 조회
	@Override
	public List<CommunityMember> findAll() {
		return memberStore.retrieveAll();
	}
	//=====================================================================================
	//CommunityMember 전체 조회
	@Override
	public void modifyMember(String memberId, NameValueList nameValueList) {
		//retrieve()메서드로 해당 Id가져온다.
		CommunityMember targetMember = memberStore.retrieve(memberId);
		//만약 리턴받은게 null이라면 예외 처리
		if (targetMember == null) {
			throw new NoSuchMemberException("No such member with id " + memberId);
		}
		//nameValueList파라미터로 넘어오면 modifyValues()메서드를 통해 해당 값들로 변경
		targetMember.modifyValues(nameValueList);
		//update()로 수정완료
		memberStore.update(targetMember);
	}
	//=====================================================================================
	//Id로 해당 CommunityMember 삭제
	@Override
	public void removeMember(String memberId) {
		//exists()존재 유무 파악 메서드
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such member with id " + memberId);
		}
		memberStore.delete(memberId);
	}
}
