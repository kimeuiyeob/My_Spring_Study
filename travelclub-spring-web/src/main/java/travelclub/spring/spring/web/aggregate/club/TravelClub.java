package travelclub.spring.spring.web.aggregate.club;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travelclub.spring.spring.web.aggregate.Entity;
import travelclub.spring.spring.web.shared.NameValue;
import travelclub.spring.spring.web.shared.NameValueList;

@Getter
@Setter
@NoArgsConstructor
public class TravelClub extends Entity {

    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MINIMUM_INTRO_LENGTH = 10;

    private String name;
    private String intro;
    private long foundationTime;

    public TravelClub(String id) {
        super(id);
    }

    public TravelClub(String name, String intro) {
        super();
        this.name = name;
        this.intro = intro;
        this.foundationTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Travel Club Id:").append(id);
        builder.append(", name:").append(name);
        builder.append(", intro:").append(intro);
        builder.append(", foundation day:").append(foundationTime);

        return builder.toString();
    }

    //이름과 소개 확인 메서드
    public void checkValidation() {
        checkNameValidation(name);
        checkIntroValidation(intro);
    }

    //상수 변수 정의로 하여금 길이에 맞는 이름과 소개글 작성하게 만드는 메서드
    private void checkNameValidation(String name) {
        if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
        }
    }

    private void checkIntroValidation(String intro) {
        if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
            throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
        }
    }

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    checkNameValidation(value);
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
        String name = "JavaTravelClub";
        String intro = "Travel club to the Java island.";
        return new TravelClub(name, intro);
    }

    public static void main(String[] args) {

        //데이터 객체를 생성하여 값을 확인할때 사용하는 명령어 => new Gson().toJson()
        System.out.println(new Gson().toJson(sample()));

        NameValueList list = new NameValueList();
        list.addNameValue("name", "change name success");
        list.addNameValue("intro", "change intro sucess");

        System.out.println(new Gson().toJson(list));

    }
}
