package travelclub.spring.spring.web.store;

import travelclub.spring.spring.web.aggregate.club.TravelClub;

import java.util.List;

//중간다리로 인터페이스를 둠으로써 타이트커플링를 안한다.
public interface ClubStore {
    String create(TravelClub club);

    TravelClub retrieve(String clubId);

    List<TravelClub> retrieveByName(String name);

    List<TravelClub> retrieveAll();

    void update(TravelClub club);

    void delete(String clubId);

    boolean exists(String clubId);
}
