package travelclub.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import travelclub.spring.aggregate.club.CommunityMember;
import travelclub.spring.aggregate.club.TravelClub;
import travelclub.spring.aggregate.club.vo.Address;
import travelclub.spring.service.ClubService;
import travelclub.spring.service.MemberService;
import travelclub.spring.service.sdo.MemberCdo;
import travelclub.spring.service.sdo.TravelClubCdo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TravelClubAppTest {

    public static void main(String[] args) {

        //ClassPathXmlApplicationContext : ClassPath에 위치한 xml 파일을 읽어 설정 정보를 로딩
        //메인에서드가 실행이 되면 스프링 컨테이너에게 설정 정보가 applicationContext.xml에 있다라는걸 알려준다.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //실제로 Bean이 등록되어있는지를 확인해보자...
        //context를 통해서 Bean의 이름을 배열에 담는다.
        String[] beanNames = context.getBeanDefinitionNames();
        //이러면 Bean에 등록된 Bean의 아이디들이 출력이 된다.
        System.out.println(Arrays.toString(beanNames));

//        ======================================================================================================

        //TravelClubCdo 클럽 객체 하나 생성
        TravelClubCdo clubCdo = new TravelClubCdo(
                "TravelClub",
                "Test TravelClub");
        //스프링 컨테이너로 하여금 Bean을 생성하게 하고 그 Bean을 찾아오는 코드
        ClubService clubService = context.getBean("clubServiceLogic", ClubService.class);
        //clubService안에 만들어놓은 registerClub클럽 생성 메서드로 clubCdo의 객체를 생성한다.
        String clubId = clubService.registerClub(clubCdo);
        System.out.println("Club ID : " + clubId.toString() + "\n");

//        ======================================================================================================

        //클럽 아이디로 해당 클럽 찾아와서 foundedClub 담기
        TravelClub foundedClub = clubService.findClubById(clubId);
        System.out.println("Club : " + foundedClub);
        System.out.println("Club name : " + foundedClub.getName());
        System.out.println("Club intro : " + foundedClub.getIntro());
        //밀리 세컨드 형식으로 저장된 getFoundationTime을 new Date메서드를 통하여 날짜 형식으로 바꿔서 출력
        System.out.println("Club foundation day : " + new Date(foundedClub.getFoundationTime()) + "\n");

//        ======================================================================================================

        MemberCdo meberCdo = new MemberCdo(
                "hello@gmail.com",
                "kimeuiyeob",
                "yoby",
                "010-5040-8875",
                "2023.05.01");

        MemberService memberService = context.getBean("memberServiceLogic", MemberService.class);
        String MemberId = memberService.registerMember(meberCdo);

        CommunityMember foundedMember = memberService.findMemberById(MemberId);

        System.out.println(foundedMember.toString());
        System.out.println("Member Id : " + foundedMember.getId());
        System.out.println("Member Name : " + foundedMember.getName());
        System.out.println("Member NikcName : " + foundedMember.getNickName());
        System.out.println("Member Email : " + foundedMember.getEmail());
        System.out.println("Member Birth : " + foundedMember.getBirthDay());
        System.out.println("Member PhoneNumber : " + foundedMember.getPhoneNumber());
        System.out.println("Member Address : " + foundedMember.getAddresses());

//        ======================================================================================================

    }
}
