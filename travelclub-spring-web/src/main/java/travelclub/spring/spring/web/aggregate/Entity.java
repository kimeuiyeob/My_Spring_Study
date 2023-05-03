package travelclub.spring.spring.web.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Entity {

	protected String id;

	protected Entity() {
		//랜덤 ID 생성
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		this.id = id;
	}
}
