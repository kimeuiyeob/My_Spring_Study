package travelclub.spring.spring.web.store.mapstore;

import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.store.MemberStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@Repository
public class MemberMapStore implements MemberStore {
	//값들 담아줄 MAP 생성
	private Map<String, CommunityMember> memberMap;

	public MemberMapStore() {
		//MemberMapStore객체가 생성될때 MAP도 생성
		this.memberMap = new LinkedHashMap<>();
	}

	//======================================================================================
	//CommunityMember생성
	@Override
	public String create(CommunityMember member) {
		//CommunityMember객체가 생성될때 memberMap맵에 아이디를 키값으로 주고 담는다.
		memberMap.put(member.getId(), member);
		return member.getId();
	}

	//======================================================================================
	//ID로 해당 CommunityMember리턴
	@Override
	public CommunityMember retrieve(String memberId) {
		return memberMap.get(memberId);
	}

	//======================================================================================
	//ID로 해당 CommunityMember리턴
	//이메일 중복 확인
	@Override
	public CommunityMember retrieveByEmail(String email) {
		//파라미터로 이메일을 넘어오면 해당 이메일이 memberMap 맵에 있는지 없는지 확인
		CommunityMember targetMember = null;
		//반복 돌아 해당 이메일이랑 같은 이메일이 있으면 그 member데이터를 리턴하고 없으면 null리턴한다.
		for (CommunityMember member : memberMap.values()) {
			if (member.getEmail().equals(email)) {
				targetMember = member;
				break;
			}
		}
		return targetMember;
	}

	//======================================================================================
	//name으로 같은 이름들의 CommunityMember전체 리턴
	@Override
	public List<CommunityMember> retrieveByName(String name) {
		return memberMap.values().stream()
				.filter(member -> member.getName().equals(name))
				.collect(Collectors.toList());
	}
	//======================================================================================
	//모든 CommunityMember 리스트로 조회
	@Override
	public  List<CommunityMember> retrieveAll() {
		return memberMap.values().stream().collect(Collectors.toList());
	}

	//======================================================================================
	//CommunityMember 수정
	@Override
	public void update(CommunityMember member) {
		memberMap.put(member.getId(), member);
	}

	//======================================================================================
	//ID로 CommunityMember 삭제
	@Override
	public void delete(String memberId) {
		memberMap.remove(memberId);
	}

	//======================================================================================
	//ID로 CommunityMember 존재 유무 파악
	@Override
	public boolean exists(String memberId) {
		return Optional.ofNullable(memberMap.get(memberId)).isPresent();
	}
}

