package travelclub.spring.spring.web.store.jpastore.jpo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.aggregate.club.vo.Address;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Travel_Member")
public class TravelMemberJpo {

    @Id
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String nickName;
    private String birthDay;

    //파라미터로 넘어온 travelMemberJpo데이터를 현재 필드에 주입
    public TravelMemberJpo(CommunityMember communityMember) {
        BeanUtils.copyProperties(communityMember, this);
    }

    //Jpo객체를 도메인 객체로 변환
    public CommunityMember toDomain() {
        CommunityMember communityMember = new CommunityMember(this.email,this.name,this.phoneNumber);
        communityMember.setId(this.getId());
        communityMember.setNickName(this.getNickName());
        communityMember.setBirthDay(this.getBirthDay());
        return communityMember;
    }

}
