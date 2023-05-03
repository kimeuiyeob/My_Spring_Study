package travelclub.spring.spring.web.store.mapstore;

import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.store.ClubStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//이제 MAP 말고 JPA로 데이터를 영속적으로 저장하자 => ClubJPAStore로 대체한다.
//@Repository
public class ClubMapStore implements ClubStore {

    //TravelClub 데이터 저장 (데이터 베이스라고 생각!)
    private Map<String, TravelClub> clubMap;

    public ClubMapStore() {
        this.clubMap = new LinkedHashMap<>();
    }

    //	==================================================================================
    //TravelClub 생성, 아이디중복 확인
    @Override
    public String create(TravelClub club) {
        clubMap.put(club.getId(), club);
        return club.getId();
    }

    //	==================================================================================
    //아이디로 해당 TravelClub 조회하기
    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    //	==================================================================================
    //이름으로 모든 TravelClub 리스트 조회하기
    @Override
    public List<TravelClub> retrieveByName(String name) {
        //Stream인터페이스로 filter로 파라미터로 넘어온 name이랑 같은 TravelClub을 리스트화해서 리턴한다.
        return clubMap.values().stream()
                .filter(club -> club.getName().equals(name))
                .collect(Collectors.toList());
    }

    //	==================================================================================
    //모든 clubMap밸류값들 리스트로해서 리턴
    @Override
    public List<TravelClub> retrieveAll() {
        return clubMap.values().stream().collect(Collectors.toList());
    }

    //	==================================================================================
    //모든 clubMap값들 리스트로해서 리턴
    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    //	==================================================================================
    //Id로 삭제
    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    //	==================================================================================
    //Id 존재 유무
    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
