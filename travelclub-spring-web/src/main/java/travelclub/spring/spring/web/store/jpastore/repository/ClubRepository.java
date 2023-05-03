package travelclub.spring.spring.web.store.jpastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelclub.spring.spring.web.store.jpastore.jpo.TravelClubJpo;

import java.util.List;

//JpaRepository =>  스프링프레임워크 패키지 안에 있는 JpaRepository인터페이스
//JpaRepository안에는 수많은 인터페이스를 상속받았다 그걸 현재 우리 ClubRepository에 상속받아 JpaRepository안에있는
//메서드들을 사용하여 DB에 저장하게 될것이다. => 순수 JPA
//                                              <@Entity정의된 객체,ID type(primary타입)>
public interface ClubRepository extends JpaRepository<TravelClubJpo,String> {
    //findAllbyName메서드 명으로 Jpa가 쿼리문을 날려 해당 데이터를 가져온다.
    //만약에 findbyName 하면 한객체만 가져올거다.
    //*주의* 메서드명을 읽어서 해당 데이터를 가져오는거라 메서드명이 오타가 나면 안된다!!
    List<TravelClubJpo> findAllByName(String name);
}
