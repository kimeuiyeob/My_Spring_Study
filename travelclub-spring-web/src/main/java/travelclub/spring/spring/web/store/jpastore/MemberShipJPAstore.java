package travelclub.spring.spring.web.store.jpastore;

import org.springframework.stereotype.Repository;
import travelclub.spring.spring.web.aggregate.club.Membership;
import travelclub.spring.spring.web.store.MembershipStore;

import java.util.List;

@Repository
public class MemberShipJPAstore implements MembershipStore {

    @Override
    public String create(Membership membership) {
        return null;
    }

    @Override
    public Membership retrieve(String membershipId) {
        return null;
    }

    @Override
    public Membership retrieveByClubIdAndMemberId(String clubId, String memberId) {
        return null;
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId) {
        return null;
    }

    @Override
    public List<Membership> retrieveByMemberId(String memberId) {
        return null;
    }

    @Override
    public void update(Membership membership) {

    }

    @Override
    public void delete(String membershipId) {

    }

    @Override
    public boolean exists(String membershipId) {
        return false;
    }
}
