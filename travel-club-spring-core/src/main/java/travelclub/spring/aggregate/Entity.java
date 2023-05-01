package travelclub.spring.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

//Getter Setter어노테이션이 pom.xml에 dependency로 다운받은 lombok을 import해서 사용하는거다.
//이렇게 어노테이션만 붙여주면 아래 있는 필드를 getter setter 메서드 없이 롬복이 알아서 생성해줘서 사용가능하다.
@Getter
@Setter
//ID는 중복이 되면이 안되니까 Entity클래스를 추상클래스로 만들고 필드는 ID만 만든다.
//다른 VO클래스에서 Entity클래스를 상속받는다 => 이렇게 되면 ID가 Primary Key값이라고 보면된다.
public abstract class Entity {

    protected String id;

    protected Entity() {
        //UUID를 통해 랜덤한 UUID를 생성해서 id에 담아준다.
        this.id = UUID.randomUUID().toString();
    }

    protected Entity(String id) {
        this.id = id;
    }
}
