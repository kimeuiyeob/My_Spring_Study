package travelclub.spring.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelClubApp {
    //톰캣 가동 포트번호 : 8090 => appliction.yml설정했다.
    public static void main(String[] args) {
        //서버 실행 명령어
        SpringApplication.run(TravelClubApp.class);
    }
}
