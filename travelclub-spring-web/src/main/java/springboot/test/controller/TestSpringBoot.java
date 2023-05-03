package springboot.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSpringBoot {
    //TestApplcation에서 tomcat연결해서 포트 8090으로 동작하고 있는중...
    //GetMapping을 통해 경로를 읽고 해당 값을 리턴해준다.
    //이렇게 해서 controller를 통해 원하는 html파일경로로 보내줘서 html파일을 브라우저에 띄우게 하는것이다.
    @GetMapping("/test")
    public String test() {
        return "Hi My Name is Kimeuiyeob!!!";
    }
}
