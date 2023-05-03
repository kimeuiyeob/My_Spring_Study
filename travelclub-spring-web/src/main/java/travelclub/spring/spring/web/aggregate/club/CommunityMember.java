package travelclub.spring.spring.web.aggregate.club;

import com.google.gson.Gson;
import travelclub.spring.spring.web.aggregate.Entity;
import travelclub.spring.spring.web.aggregate.club.vo.Address;
import travelclub.spring.spring.web.shared.NameValue;
import travelclub.spring.spring.web.shared.NameValueList;
import travelclub.spring.spring.web.util.exception.InvalidEmailException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommunityMember extends Entity {
    //CommunityMember 필드값들
    private String email; //key값으로 중복확인한다.
    private String name;
    private String phoneNumber;
    private String nickName;
    private String birthDay;

    private List<Address> addresses;

    public CommunityMember() {
        super();
        this.addresses = new ArrayList<>();
    }

    public CommunityMember(String id) {
        super(id);
    }

    public CommunityMember(String email, String name, String phoneNumber) {
        //CommunityMember 생성자
        this();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        //toString()메서드를 호출하면 StringBuilder객체를 통해 해당 값들을 다 append로 이어 붙여서 해당 객체를 리턴한다.
        StringBuilder builder = new StringBuilder();

        builder.append("Name:").append(name);
        builder.append(", email:").append(email);
        builder.append(", nickname:").append(nickName);
        builder.append(", phone number:").append(phoneNumber);
        builder.append(", birthDay:").append(birthDay);
        //만약 addresses값이 없다면 append(i)를 해서 리턴한다.
        if (addresses != null) {
            int i = 1;
            for (Address address : addresses) {
                builder.append(", Address[").append(i).append("]").append(address.toString());
            }
        }
        return builder.toString();
    }

    public void checkValidation() {
        //아래 메서드로 email데이터를 보내준다.
        checkEmailValidation(email);
    }

    private void checkEmailValidation(String email) {
        //이메일 등록할때 잡아주는 메서드
        //예를들어 특수문자 이런게 섞여있다면 이메일 가입 불가 이런거다.
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        boolean valid = m.matches();
        if (!valid) {
            throw new InvalidEmailException("Email is invalid.");
        }
    }

    public void modifyValues(NameValueList nameValues) {
        //파라미터로 nameValues가 넘어오면 해당 객체밸류값으로 반복돌아 이름,폰번호,닉네임,생일 변경
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "phoneNumber":
                    this.phoneNumber = value;
                    break;
                case "nickName":
                    this.nickName = value;
                    break;
                case "birthDay":
                    this.birthDay = value;
                    break;
            }
        }
    }

    //샘플 데이터 값 생성
    public static CommunityMember sample() {
        CommunityMember member = new CommunityMember("mymy@nextree.co.kr", "Minsoo Lee", "010-3321-1001");
        member.setBirthDay("2001.09.23");
        member.getAddresses().add(Address.sampleHomeAddress());
        return member;
    }

    public static void main(String[] args) {
//		System.out.println(new Gson().toJson(sample()));

    }
}
