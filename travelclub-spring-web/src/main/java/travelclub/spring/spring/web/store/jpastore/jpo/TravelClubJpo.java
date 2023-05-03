package travelclub.spring.spring.web.store.jpastore.jpo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import travelclub.spring.spring.web.aggregate.club.TravelClub;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//도메인 객체를 바로 Entity로 매핑할수 있지만 타입등 여러가지 불일치드의 이유로 별도의 매핑 클래스(Jpo) => Java Persistence Object 클래스를 생성한다.
//매핑 클래스를 관계형 데이터베이스 테이블로 맵핑할 때 @Entity 어노테이션 사용한다.
//@Entity 어노테이션만 선언했을때 테이블 이름은 클래스 이름이되고 대/소문자 치환은 일어나지 않는다.
//대부분 RDBMS는 대/소문자를 가리지 않으므로 기본JPA 작명규칙이 문제를 발생하지는 않는다.
@Entity
@Getter
@Setter
@NoArgsConstructor //기본 생성자 (default Constructor)
@Table(name = "Travel_Club") //클래스명으로 테이블명이 생성됨으로 그걸 변경하기 위해서는 @Table어노테이션을 사용한다.
public class TravelClubJpo {

    //현재 데이터베이스에 해당 테이블을 미리 생성하지 않았다.
    //하지만 JPA에 의해서 현재 클래스명과 필드값으로 테이블 이름과 컬럼이름으로 자동으로 생성된다.

//    create table travel_club_jpo (
//          id varchar(255) not null,
//          foundation_time bigint not null,
//          intro varchar(255),
//          name varchar(255),
//          primary key (id)
//    )

    //=> H2데이터베이스에 테이블이 생긴걸 확인할수있다.

    @Id //primary key
    private String id;

    private String name;
    private String intro;
    private long foundationTime;

    //TravelClub객체를 데이터베이스에 담기위해서는 매핑을 해야함으로
    //해당 필드값을 가져와 TravelClubJpo에 필드값으로 매핑 변환해서 데이터베이스에 해당 값을 저장한다.
    public TravelClubJpo(TravelClub travelClub) {

//        this.id = travelClub.getId();
//        this.name = travelClub.getName();
//        this.intro = travelClub.getIntro();
//        this.foundationTime = travelClub.getFoundationTime();

        //위에처럼 일일이 값을 대입해주면 필드값이 많다면 다소 코드가 길어질수있는 번거러움이 있기때문에
        //BeanUtils라는 스프링 프레임워크의 클래스가있는데 copyProperties()를 이용하여 파라미터로 넘어오는 travelClub을
        //복사해서 this로 저장한다.

        //TravelClubJpo 객체를 생성하면서 Entity객체인 TravelClub을 파라미터로 넘겨주면 이 객체들을 스프링이 분석해서
        //현재 가지고 있는 필드와 매핑시켜서 데이터를 넣어주는 형식이다. => entity 객체를 jpo객체로 변환한것이다.
        BeanUtils.copyProperties(travelClub, this);

    }

    //=====================================================================================================
    //Jpo객체를 도메인 객체로 변환해주는 메서드
    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name,this.intro);
        travelClub.setId(this.id);
        travelClub.setFoundationTime(this.foundationTime);
        return travelClub;
    }

}
