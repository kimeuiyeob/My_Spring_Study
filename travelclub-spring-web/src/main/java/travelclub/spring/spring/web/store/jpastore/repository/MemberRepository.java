package travelclub.spring.spring.web.store.jpastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.store.jpastore.jpo.TravelMemberJpo;

import java.util.List;

public interface MemberRepository extends JpaRepository<TravelMemberJpo,String> {
    CommunityMember findByEmail(String email);
    List<TravelMemberJpo> findAllByName(String name);
}
