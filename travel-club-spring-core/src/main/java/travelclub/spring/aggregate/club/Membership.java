package travelclub.spring.aggregate.club;

import travelclub.spring.aggregate.Entity;
import travelclub.spring.aggregate.club.vo.RoleInClub;
import travelclub.spring.shared.NameValue;
import travelclub.spring.shared.NameValueList;
import travelclub.spring.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Membership extends Entity {
	//
	private String clubId;
	private String memberId;
	private RoleInClub role;
	private String joinDate;

	public Membership(String id) {
		//
		super(id);
	}

	public Membership(String clubId, String memberId) {
		//
		this.clubId = clubId; 
		this.memberId = memberId;
		this.role = RoleInClub.Member;
		this.joinDate = DateUtil.today();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("club Id:").append(clubId);
		builder.append(", member Id:").append(memberId);
		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public void modifyValues(NameValueList nameValueList) {
		//
		for (NameValue nameValue : nameValueList.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "role":
					this.role = RoleInClub.valueOf(value);
					break;
			}
		}
	}

	public static Membership sample() {
		//
		return new Membership(
				TravelClub.sample().getId(),
				CommunityMember.sample().getId()
		);
	}

	public static void main(String[] args) {
		// 
		System.out.println(sample().toString());
	}
}