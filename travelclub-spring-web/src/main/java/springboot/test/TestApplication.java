package springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBoot만들어서 톰캣 서버에 연결하기!!!
//우선 pom.xml에 필요한 라이브러리를 다운받아준다.
//start-parent로 xml을 상속받아 좀더 간편히 사용한다.
//그다음 @SpringBootApplication어노테이션으로 등록하고 appliciton.yml포트번호 설정한다.
//그리고 SpringApplication.run(TestApplication.class,args);실행하면 해당 포트번호로 톰캣서버에 연결된다.
//이때 컨드롤러 클래스 하나 만들어 어노테이션 지정하고 mapping으로 경로 설정해서 브라우저에 해당 경로 입력하면 연결된걸 확인할수있다.

//3개의 중요한 어노테이션이 SpringBootApplication통해서 Bean에추가된다.
//@Component ,@Configuration, @EnableAutoConfiguration
//톰캣 실행 및 localhost 연결 시키기!
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {

        //SpringBootApplication동작 명령어
        //톰캣이 실행이 되고 , application.yml에 등록한 포트번호 8090포트로 연결되면서 계속 동작중이다.
        //이렇게 run을 하면 이제 localhost/8090 브라우저로 연결된것을 확인할수있다.
        // => Tomcat initialized with port(s): 8090 (http)
        // => Starting service [Tomcat]
        // => Starting Servlet engine: [Apache Tomcat/9.0.56]
        // => Tomcat started on port(s): 8090 (http) with context path ''
        SpringApplication.run(TestApplication.class, args);

    }
}
