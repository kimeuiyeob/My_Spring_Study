package travelclub.spring.aggregate.club;

import travelclub.spring.aggregate.Entity;
import travelclub.spring.shared.NameValue;
import travelclub.spring.shared.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//롬복을 이용해서 NoArgsConstructor 생성자를 생성
@NoArgsConstructor
public class TravelClub extends Entity {

    //최소글자 제한 , 상수로 정의
    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MINIMUM_INTRO_LENGTH = 10;
    private String name;
    private String intro;
    //클럽이 생성된 날짜
    private long foundationTime;

//	public TravelClub(){;} <= 기본 생성자를 NoArgsConstructor 롬복이 알아서 생성해준다.

    //문자열을 받는 생성자를 생성해서 기본생성자는 NoArgsConstructor 생성
    public TravelClub(String id) {
        super(id);
    }

    public TravelClub(String name, String intro) {
        //파라미터로 받아온 이름과, 소개, 현재시간 필드로 저장
        super();
        this.name = name;
        this.intro = intro;
        //현재 시간을 밀리미초 => 1682830663771 방식으로 저장한다.
        this.foundationTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("Travel Club Id:").append(id);
        builder.append(", name:").append(name);
        builder.append(", intro:").append(intro);
        builder.append(", foundation day:").append(foundationTime);

        return builder.toString();
    }

    public void checkValidation() {
        //
        checkNameValidation(name);
        checkIntroValidation(intro);
    }

    //이름의 길이가 3글자보다 짧다면 예외처리
    private void checkNameValidation(String name) {
        if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
        }
    }
    //소개 길이가 10글자보다 짧다면 예외처리
    private void checkIntroValidation(String intro) {
        if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
            throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
        }
    }

    //Name과 Intro를 수정하는 메서드
    public void modifyValues(NameValueList nameValues) {
        //넘어온 파라미터로 반복실행
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    //checkNameValidation메서드를 통해 Name의 길이가 3보다 작으면 예외처리
                    checkNameValidation(value);
                    //예외처리가 안됬다면 this.name에 이름 담는다.
                    this.name = value;
                    break;
                case "intro":
                    checkIntroValidation(value);
                    this.intro = value;
                    break;
            }
        }
    }

    public static TravelClub sample() {
        //
        String name = "JavaTravelClub";
        String intro = "Travel club to the Java island.";

        return new TravelClub(name, intro);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toString());
    }
}
