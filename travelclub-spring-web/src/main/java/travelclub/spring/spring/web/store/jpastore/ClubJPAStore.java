package travelclub.spring.spring.web.store.jpastore;

import org.springframework.stereotype.Repository;
import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.store.ClubStore;
import travelclub.spring.spring.web.store.jpastore.jpo.TravelClubJpo;
import travelclub.spring.spring.web.store.jpastore.repository.ClubRepository;
import travelclub.spring.spring.web.util.exception.NoSuchClubException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClubJPAStore implements ClubStore {

    private ClubRepository clubRepository;

    //ClubJPAStore객체가 생성될때 ClubRepository의 인터페이스가 생성되 상속받은 JPA CRUD메서드를 사용하는것이다.
    //실제로 쿼리문을 사용하지 않고 Spring data jpa가 제공해주는 JpaRepository<T,S>를
    //내가 만든 ClubRepository 인터페이스에 상속받아 간편하게 CRUD작업을 할수 있다.

    public ClubJPAStore(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    //===================================================================
    //TravelClub 생성하기
    @Override
    public String create(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));
        return club.getId();
    }

    //===================================================================
    //Id로 TravelClub 조회하기
    @Override
    public TravelClub retrieve(String clubId) {
        Optional<TravelClubJpo> travelClubJpo = clubRepository.findById(clubId);
        if (!travelClubJpo.isPresent()) {
            throw new NoSuchClubException(String.format("Travel Club(%s) is not found", clubId));
        }
        return travelClubJpo.get().toDomain();
    }
    //===================================================================

    //Jpa를 통해서 데이터를 넣어줄때에는 도메인 객체를 Jpo객체로 바꿔서 넣어주고
    //데이터를 찾아올때는 Jpo객체를 찾아오게 되고 그 Jpo객체를 다시 도메인 객체로 변환하는 작업이 필요하다.

    //전체 TravelClub 조회하기
    @Override
    public List<TravelClub> retrieveAll() {
        List<TravelClubJpo> travelClubJpo = clubRepository.findAll();
//      return travelClubJpo.stream().map(i -> i.toDomain()).collect(Collectors.toList());
        return travelClubJpo.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
    }

    //===================================================================

    @Override
    public List<TravelClub> retrieveByName(String name) {
        List<TravelClubJpo> travelClubJpo = clubRepository.findAllByName(name);
        return travelClubJpo.stream().map(i -> i.toDomain()).collect(Collectors.toList());
    }

    //===================================================================

    @Override
    public void update(TravelClub club) {

        //JpaRepository에는 update()메서드가 없다, 하지만 save()가있다.
        //save()는 단순히 create하는 작업만 포함된게 아니라 update까지 포함되어있다고 생각하면 된다.
        //그래서 save()는 해당 ID가없으면 save가 되고 Id가 있다면 update가 된다.

        clubRepository.save(new TravelClubJpo(club));
    }

    //===================================================================

    @Override
    public void delete(String clubId) {
        clubRepository.deleteById(clubId);
    }

    //===================================================================

    @Override
    public boolean exists(String clubId) {
        return clubRepository.existsById(clubId);
    }
}
