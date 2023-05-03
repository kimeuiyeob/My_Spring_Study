package travelclub.spring.spring.web.service.logic;

import org.springframework.stereotype.Service;
import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.service.ClubService;
import travelclub.spring.spring.web.service.sdo.TravelClubCdo;
import travelclub.spring.spring.web.shared.NameValueList;
import travelclub.spring.spring.web.store.ClubStore;
import travelclub.spring.spring.web.util.exception.NoSuchClubException;

import java.util.List;

@Service
public class ClubServiceLogic implements ClubService {
	private ClubStore clubStore;

	public ClubServiceLogic(ClubStore clubStore) {
		this.clubStore = clubStore;
	}
	//===========================================================================
	//클럽 생성
	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		TravelClub club = new TravelClub(clubCdo.getName(), clubCdo.getIntro());
		club.checkValidation();
		String clubId = clubStore.create(club);
		return clubId;
	}
	//===========================================================================
	//Id로 클럽 조회
	@Override
	public TravelClub findClubById(String id) {
		return clubStore.retrieve(id);
	}
	//===========================================================================
	//같은 이름 전체 클럽 조회
	@Override
	public List<TravelClub> findClubsByName(String name) {
		return clubStore.retrieveByName(name);
	}
	//===========================================================================
	//전체 클럽 조회
	@Override
	public List<TravelClub> findAll(){
		return clubStore.retrieveAll();
	}
	//===========================================================================
	//클럽 수정 => nameValueList RequestBody 넘어온 데이터값으로 수정
	@Override
	public void modify(String clubId, NameValueList nameValueList) {
		TravelClub travelClub = clubStore.retrieve(clubId);
		if (travelClub == null) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		travelClub.modifyValues(nameValueList);
		clubStore.update(travelClub);
	}
	//===========================================================================
	//Id로 삭제
	@Override
	public void remove(String clubId) {
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		clubStore.delete(clubId);
	}
}
