package travelclub.spring.spring.web.store.jpastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelclub.spring.spring.web.store.jpastore.jpo.MemberShipJpo;

public interface MemberShipRepository extends JpaRepository<MemberShipJpo, String> {
}
