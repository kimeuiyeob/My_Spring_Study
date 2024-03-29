package travelclub.spring.spring.web.service.logic;

import org.springframework.stereotype.Service;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.aggregate.club.Membership;
import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.aggregate.club.vo.RoleInClub;
import travelclub.spring.spring.web.service.MembershipService;
import travelclub.spring.spring.web.service.sdo.MembershipCdo;
import travelclub.spring.spring.web.shared.NameValueList;
import travelclub.spring.spring.web.store.ClubStore;
import travelclub.spring.spring.web.store.MemberStore;
import travelclub.spring.spring.web.store.MembershipStore;
import travelclub.spring.spring.web.util.exception.NoSuchMemberException;
import travelclub.spring.spring.web.util.exception.NoSuchMembershipException;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {

    private MembershipStore membershipStore;
    private ClubStore clubStore;
    private MemberStore memberStore;

    public MembershipServiceLogic(MembershipStore membershipStore, ClubStore clubStore, MemberStore memberStore) {
        this.membershipStore = membershipStore;
        this.memberStore = memberStore;
        this.clubStore = clubStore;
    }


    @Override
    public String registerMembership(MembershipCdo membershipCdo) {

        String clubId = membershipCdo.getClubId();
        String memberId = membershipCdo.getMemberId();
        RoleInClub role = membershipCdo.getRole();
        TravelClub club = clubStore.retrieve(clubId);

//        if (club == null) {
//            throw new NoSuchClubException("No such club with id " + clubId);
//            CommunityMember member = memberStore.retrieve(memberId);
//            if (member == null) {
//                throw new NoSuchMemberException("No such member with id " + memberId);
//            }
//            Membership membership = findMembershipByClubIdAndMemberId(clubId, memberId);
//            if (membership != null) {
//                throw new MembershipDuplicationException("Member already exists in the club");
//            }
//            membership = new Membership(clubId, memberId);
//            membership.setRole(role);
//            String membershipId = membershipStore.create(membership);
//            return membershipId;
//        }
        return null;
    }

    @Override
    public Membership findMembership(String membershipId) {
        return membershipStore.retrieve(membershipId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberId) {
        return membershipStore.retrieve(clubId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail) {
        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with email " + memberEmail);
        }

        Membership membership = findMembershipByClubIdAndMemberId(clubId, member.getId());
        return membership;
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        return membershipStore.retrieveByClubId(clubId);
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberId) {
        return membershipStore.retrieveByMemberId(memberId);
    }

    @Override
    public void modifyMembership(String membershipId, NameValueList nameValueList) {
        Membership membership = membershipStore.retrieve(membershipId);
        if (membership == null) {
            throw new NoSuchMembershipException("No such membership");
        }
        membership.modifyValues(nameValueList);
        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String membershipId) {
        membershipStore.delete(membershipId);

    }
}
