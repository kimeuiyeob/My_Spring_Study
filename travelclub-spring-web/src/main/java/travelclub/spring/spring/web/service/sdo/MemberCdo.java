package travelclub.spring.spring.web.service.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//DTO 같은 개념의 클래스 CDO
public class MemberCdo implements Serializable {
    //MemberCdo 필드값 멤버 생성시 넘어와야할 데이터 값들
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;
}
