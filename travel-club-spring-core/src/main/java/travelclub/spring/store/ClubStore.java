package travelclub.spring.store;

import travelclub.spring.aggregate.club.TravelClub;

import java.util.List;

public interface ClubStore {

	//클럽 생성
	String create(TravelClub club);

	//ID로 클럽 찾기
	TravelClub retrieve(String clubId);

	//NAME으로 모든 클럽 찾기
	List<TravelClub> retrieveByName(String name);

	//클럽 수정
	void update(TravelClub club);

	//ID로 클럾 삭제
	void delete(String clubId);

	//ID로 클럽 존재하는지 확인
	boolean exists(String clubId);
}
