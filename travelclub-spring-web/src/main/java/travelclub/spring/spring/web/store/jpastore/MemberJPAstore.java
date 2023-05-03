package travelclub.spring.spring.web.store.jpastore;

import org.springframework.stereotype.Repository;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.store.MemberStore;
import travelclub.spring.spring.web.store.jpastore.jpo.TravelMemberJpo;
import travelclub.spring.spring.web.store.jpastore.repository.MemberRepository;
import travelclub.spring.spring.web.util.exception.NoSuchClubException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberJPAstore implements MemberStore {

    private MemberRepository memberRepository;

    //memberRepository주입 => Jpa상속받은 인터페이스
    public MemberJPAstore(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //CommunityMember생성
    @Override
    public String create(CommunityMember member) {
        memberRepository.save(new TravelMemberJpo(member));
        return member.getId();
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        Optional<TravelMemberJpo> communityMember = memberRepository.findById(memberId);
        if (!communityMember.isPresent()) {
            throw new NoSuchClubException(String.format("Travel Member(%s) is not found", memberId));
        }
        return communityMember.get().toDomain();
    }

    @Override
    public CommunityMember retrieveByEmail(String email) {
        CommunityMember communityMember = memberRepository.findByEmail(email);
        return communityMember;
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<TravelMemberJpo> findAllName = memberRepository.findAllByName(name);
        return findAllName.stream().map(i -> i.toDomain()).collect(Collectors.toList());
    }

    //전체조회
    @Override
    public List<CommunityMember> retrieveAll() {
        List<TravelMemberJpo> findAll = memberRepository.findAll();
        return findAll.stream().map(i -> i.toDomain()).collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberRepository.save(new TravelMemberJpo(member));
    }

    @Override
    public void delete(String memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public boolean exists(String memberId) {
        return memberRepository.existsById(memberId);
    }
}
