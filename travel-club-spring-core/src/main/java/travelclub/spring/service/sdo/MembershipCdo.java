package travelclub.spring.service.sdo;

import travelclub.spring.aggregate.club.vo.RoleInClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCdo {
    //
    private String clubId;
    private String memberId;
    private RoleInClub role;
}
