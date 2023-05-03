package travelclub.spring.spring.web.service;

import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.service.sdo.TravelClubCdo;
import travelclub.spring.spring.web.shared.NameValueList;

import java.util.List;

public interface ClubService {
    String registerClub(TravelClubCdo club);

    TravelClub findClubById(String id);

    List<TravelClub> findClubsByName(String name);

    List<TravelClub> findAll();

    void modify(String clubId, NameValueList nameValues);

    void remove(String clubId);
}
