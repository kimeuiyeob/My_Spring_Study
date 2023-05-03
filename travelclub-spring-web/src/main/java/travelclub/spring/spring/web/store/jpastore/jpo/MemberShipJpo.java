package travelclub.spring.spring.web.store.jpastore.jpo;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import travelclub.spring.spring.web.aggregate.club.Membership;
import travelclub.spring.spring.web.aggregate.club.vo.RoleInClub;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MemberShipJpo {

    @Id
    private String clubId;
    private String memberId;
    private RoleInClub role;
    private String joinDate;

    public MemberShipJpo(Membership membership) {
        BeanUtils.copyProperties(membership,this);
    }

    public Membership toDomain() {
        Membership memberShipJpo = new Membership(this.clubId,this.memberId);
        memberShipJpo.setRole(this.getRole());
        memberShipJpo.setJoinDate(this.joinDate);
        return memberShipJpo;
    }

}
