package travelclub.spring.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelclub.spring.aggregate.club.TravelClub;
import travelclub.spring.service.ClubService;
import travelclub.spring.service.sdo.TravelClubCdo;
import travelclub.spring.shared.NameValueList;
import travelclub.spring.store.ClubStore;
import travelclub.spring.util.exception.NoSuchClubException;

import java.util.List;

/*이렇게 되면 xml파일에 일일히 클래스를 bean에다 등록해줘야 하는 번거러움이 있다. 그래서 요즘은 어노테이션방법을 사용한다.*/
/*아래 xml파일에 클래스를 Bean에다 써서 등록시킨것은 예전 방식이다.*/

//현재 ClubServiceLogic, ClubMapStore클래스를 applicationContext.xml에서 Bean에다 등록시켜놨다.
//ClubServiceLogic이 사용(생성)되는 시점에 생성자를 호출하면서 파라미터(clubStore)
// =>  clubMapStore new해서 this.clubStore에다 넘겨준다.
@Service
public class ClubServiceLogic implements ClubService {

    //ClubStore인터페이스 객체 정의
    @Autowired
    private ClubStore clubStore;

//    public ClubServiceLogic(ClubStore clubStore) {
//        //객체 생성할때 clubStore인터페이스를 구현한 ClubMapStore클래스 생성
//        this.clubStore = clubStore;
//    } <=  @Autowired 가 해준다.

    // ===============================================================================================
    //registerClub클럽 생성
    @Override
    public String registerClub(TravelClubCdo club) {
        TravelClub newClub = new TravelClub(club.getName(), club.getIntro());
        newClub.checkValidation();
        return clubStore.create(newClub);
    }

    // ===============================================================================================
    //ID로 해당 클럽 찾기
    @Override
    public TravelClub findClubById(String id) {
        return clubStore.retrieve(id);
    }

    // ===============================================================================================
    //클럽 Name으로 클럽 LIST 찾기
    @Override
    public List<TravelClub> findClubsByName(String name) {
        return clubStore.retrieveByName(name);
    }

    // ===============================================================================================
    //ID로 해당 name과,intro 수정하기
    @Override
    public void modify(String clubId, NameValueList nameValues) {
        //clubStore.retrieve(clubId)를 통해 해당 clubId를 가져온다.
        TravelClub foundedClub = clubStore.retrieve(clubId);
        //만약 값이 없다면 Id가 없는것임으로 예외 처리
        if (foundedClub == null) {
            throw new NoSuchClubException("No such club with id : " + clubId);
        }
        //modifyValues메서드를 통해 name과,intro 수정
        foundedClub.modifyValues(nameValues);
        //update메서드를 통해 수정완료
        clubStore.update(foundedClub);
    }

    // ===============================================================================================
    //클럽ID로 해당 클럽 삭제
    @Override
    public void remove(String clubId) {
        if (!clubStore.exists(clubId)) {
            throw new NoSuchClubException("No such club with id : " + clubId);
        }
        clubStore.delete(clubId);
    }

    // ===============================================================================================

}
