package travelclub.spring.store.mapstore;

import org.springframework.stereotype.Repository;
import travelclub.spring.aggregate.club.TravelClub;
import travelclub.spring.store.ClubStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
//@Repository("clubStore") <= beanID를 이렇게 변경해줄수도 있다.
public class ClubMapStore implements ClubStore {

    //TravelClub 맵
    private Map<String, TravelClub> clubMap;

    //ClubMapStore이 생성될때 LinkedHashMap을 생성한다.
    public ClubMapStore() {
        this.clubMap = new LinkedHashMap<>();
    }

    @Override
    //하나의 클럽을 생성하는 메서드
    public String create(TravelClub club) {
        //club객체가 파라미터로 넘어오면 getID를 추출해 키값으로 주고, 밸류값으로 club을 clubMap 저장한다.
        clubMap.put(club.getId(), club);
        //id를 리턴한다.
        return club.getId();
    }

    @Override
    //하나의 클럽을 찾는 메서드 , 키값을 id로 저장했으니까 id로 해당 크럽을 찾는다.
    public TravelClub retrieve(String clubId) {
        //clubMap 맵에서 파라미터로 넘어온 clubId로 키값으로 찾으면 해당 밸류 club값을 리턴한다.
        return clubMap.get(clubId);
    }

    @Override
    //파라미터로 클럽의 이름이 넘어오면 해당 TravelClub 리스를 리턴한다.
    public List<TravelClub> retrieveByName(String name) {

        //jdk 8버젼 이후 stream() 인터페이스 사용 가능
        //clubMap의 밸류를 가져와 필터링으로 밸류안의 name과 파라미터로 넘어온 name이 같다면 해당 값들을 list화 해서 리턴한다.
        return clubMap.values()
                .stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());

        //forEach형식으로 한다면 아래처럼 저장
//        List<TravelClub> listClub = new ArrayList<>();
//        for (Map.Entry<String, TravelClub> club : clubMap.entrySet()) {
//            if (club.getValue().getName().equals(name)) {
//                listClub.add(club.getValue());
//            }
//        }
//        return listClub;

    }

    @Override
    //파라미터로 넘어온 club은 이미 수정되서 받아온 정보이이다, 그래서 바로 Map에다 담아주면된다.
    public void update(TravelClub club) {
        //Map은 중복이 안되므로 해당 키값으로 밸류값을 너어주면 수정된다.
        clubMap.put(club.getId(), club);
    }

    @Override
    //파라미터로 넘어온 clubId를 통해서 club를 삭제한다.
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    //파라미터로 넘어온 clubId를 통해서 해당 id가 있는지 없는지 확인, boolean으로 리턴한다.
    public boolean exists(String clubId) {

//        for (Map.Entry<String, TravelClub> club : clubMap.entrySet()) {
//            if(club.getKey().equals(clubId)) {
//                return true;
//            }
//        }
//        return false;
        //================================================================
//        return clubMap.containsKey(clubId);
        //================================================================

//        Optional는 “존재할 수도 있지만 안 할 수도 있는 객체”, 즉, “null이 될 수도 있는 객체”을 감싸고 있는 일종의 래퍼 클래스
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();

        //================================================================
//        return clubMap.entrySet().stream().anyMatch(i -> i.getKey().equals(clubId));
        //================================================================
    }
}
