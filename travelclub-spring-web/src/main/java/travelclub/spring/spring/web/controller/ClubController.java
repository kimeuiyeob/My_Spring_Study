package travelclub.spring.spring.web.controller;

import org.springframework.web.bind.annotation.*;
import travelclub.spring.spring.web.aggregate.club.TravelClub;
import travelclub.spring.spring.web.service.ClubService;
import travelclub.spring.spring.web.service.sdo.TravelClubCdo;
import travelclub.spring.spring.web.shared.NameValueList;

import java.util.List;

//@Controller사용할때는 기본적으로 view페이지가 있을때 사용한다. => HTML파일
//전체적인 흐름 => 컨트롤러를 거쳐서 사용자가 원하는 데이터를 가져왔다면 그걸 Model에 담아서 View로 보내주게 되고
//View에서 해당 데이터를 화면에 만들어서 사용자에게 전달해준다.

//지금하는건 view가 없고 json데이터를 ResponseBody에 넣어서 바로 보내줄거다. => 그래서 @RestController 사용한다.

//@RestController안에 @Controller와 @ResponseBody라는게 포함하고 있다.

//ClubController를 @RestController어노테이션을 붙여 해당 클래스를 Bean에 주입해서 IOC컨테이너에게 관리되겠금 한다.
@RequestMapping("/club") //해당 클래스 모든 Mapping경로앞에 club을 root경로로 잡아준다.
@RestController
public class ClubController {

    private ClubService clubService;

    //ClubController가 실행될때 clubService구현되고 있는 clubServiceLogic객체 주입
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    //    ======================================================================================

    //값이 넘어올때는 해당 url뒤에 값이 붙어서 넘어오는경우가 있고 RequestBody에 담겨서 넘어오는경우가 있다.
    //이걸 Test할때는 Postman,Insomnia프로그램으로 테스트 한다.

    //사용자에게 TravelClubCdo데이터가 넘어올꺼다.
    //TravelClubCdo안에는 name,intro가 있다. => @RequestBody통해 postman,insomnia에서
    //Json형식으로 데이터 보내서 리턴값이 오면 정상적으로 작동하는것이다.

    //클럽 생성하기
    @PostMapping/*("/club")*/ //저장할때는 POST 방식 사용
    //TravelClubCdo의 name,intro는 Http RequestBody에 데이터가 담겨서 넘어온다.
    public String register(@RequestBody TravelClubCdo travelClubCdo) {
        return clubService.registerClub(travelClubCdo);
    }

    //    ======================================================================================

    //전체 클럽 조회하기
    @GetMapping("/all") //데이터를 조회할때는 GET
    public List<TravelClub> findAll() {
        return clubService.findAll();
    }

    //    ======================================================================================

    //Get방식으로 아이디를 보낼때에는 @RequestBody 바디에 값을 담아 보낸느게 아니라
    //URL에 포함되서 넘어오는 형식이다. => 즉 GET방식으로 값을 보낼때에는 @RequestBody로 보내는게 아니다.
    //URL에 {clubId} 이형식으로 해당 데이터를 받아와서 파라미터에 @PathVariable로 데이터 값을 읽은후 값을 보낸다.

    //아이디로 해당 TravelClub 조회하기
    @GetMapping("/{clubId}") //조회할때는 Get방식 사용
    public TravelClub find(@PathVariable String clubId) { //<================= @PathVariable 방식
        return clubService.findClubById(clubId);
    }

    //    ======================================================================================

    //현재 name으로 TravelClub을 찾을때 위에 같이 PathVariable방식으로 찾으면
    //스피링 입장에서는 똑같이 club/String이 넘어와서 위에껄 실행시킬지 아래껄 실행시킬지 모른다 그래서 충돌이 나게되서 에러가난다.
    //그렇다고 url경로를 늘려주는건 RestAPI설계 지침에 맞지 않다.
    //그래서 또다른 방법으로는 RequestParam방식으로 URL에 ?clubName=이름 으로 보내줘서 데이터를 조회하는 방식이있다.

    //@GetMapping("/club/{clubName}")
    //public List<TravelClub> findByName(@PathVariable String clubName) {

    //이름으로 TravelClub 리스트 조회하기
    @GetMapping
    public List<TravelClub> findByName(@RequestParam String clubName) {  //<================= @RequestParam 방식
        return clubService.findClubsByName(clubName);
    }

    //    ======================================================================================

    //Http 프로토콜 방식 => Post,Put RequestBody 값을 담아서 보낸다.
    //("/club/{clubId}")이거는 아이디로 해당 TravelClub 조회하기랑 동일한데 Mapping방식이 달라서 충돌이 안난다.

    //이름과 소개글 수정
    @PutMapping("/{clubId}") //수정할때는 Put방식을 사용
    public void modify(@PathVariable String clubId, @RequestBody NameValueList nameList) {
        clubService.modify(clubId, nameList);
    }

    //    ======================================================================================

    //아이디로 지우기
    @DeleteMapping("/{clubId}") //지울때 Delete방식 사용
    public void delete(@PathVariable String clubId) {
        clubService.remove(clubId);
    }

    //    ======================================================================================
}
