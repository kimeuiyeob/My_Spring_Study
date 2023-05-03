package travelclub.spring.spring.web.store;

import travelclub.spring.spring.web.aggregate.club.Membership;

import java.util.List;

public interface MembershipStore {
    String create(Membership membership);

    Membership retrieve(String membershipId);

    Membership retrieveByClubIdAndMemberId(String clubId, String memberId);

    List<Membership> retrieveByClubId(String clubId);

    List<Membership> retrieveByMemberId(String memberId);

    void update(Membership membership);

    void delete(String membershipId);

    boolean exists(String membershipId);
}
