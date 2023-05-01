package io.namoosori.travelclub.spring.web.service;

import io.namoosori.travelclub.spring.web.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.web.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.web.shared.NameValueList;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	List<TravelClub> findAll();
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
