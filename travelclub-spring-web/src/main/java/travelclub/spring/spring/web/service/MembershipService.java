package travelclub.spring.spring.web.service;

import travelclub.spring.spring.web.aggregate.club.Membership;
import travelclub.spring.spring.web.service.sdo.MembershipCdo;
import travelclub.spring.spring.web.shared.NameValueList;

import java.util.List;

public interface MembershipService {
    String registerMembership(MembershipCdo membershipCdo);

    Membership findMembership(String membershipId);

    Membership findMembershipByClubIdAndMemberId(String clubId, String memberId);

    Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail);

    List<Membership> findAllMembershipsOfClub(String clubId);

    List<Membership> findAllMembershipsOfMember(String memberId);

    void modifyMembership(String membershipId, NameValueList nameValueList);

    void removeMembership(String membershipId);
}
