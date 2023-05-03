package travelclub.spring.spring.web.service.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelClubCdo implements Serializable {
    //Cdo 데이터를 Dto같은 개념 (data transfer object)
    //=> 데이터를 주고 받고하는 객체
    private String name;
    private String intro;
}
