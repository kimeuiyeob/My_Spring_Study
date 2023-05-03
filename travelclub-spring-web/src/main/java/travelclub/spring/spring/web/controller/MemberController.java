package travelclub.spring.spring.web.controller;


import org.springframework.web.bind.annotation.*;
import travelclub.spring.spring.web.aggregate.club.CommunityMember;
import travelclub.spring.spring.web.service.MemberService;
import travelclub.spring.spring.web.service.sdo.MemberCdo;
import travelclub.spring.spring.web.shared.NameValueList;

import java.util.List;

@RequestMapping("/member")
@RestController
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //======================================================================
    //멤버 생성하기
    @PostMapping
    public String register(@RequestBody MemberCdo memberCdo) {
        return memberService.registerMember(memberCdo);
    }

    //======================================================================
    //전체 멤버 조회하기
    @GetMapping("/all")
    public List<CommunityMember> findAll() {
        return memberService.findAll();
    }

    //======================================================================
    //아이디로 해당 멤버 조회하기
    @GetMapping("/{id}")
    public CommunityMember find(@PathVariable String id) {
        return memberService.findMemberById(id);
    }

    //======================================================================
    //멤버이름으로 리스트 조회하기
    @GetMapping
    public List<CommunityMember> findByname(@RequestParam String name) {
        return memberService.findMembersByName(name);
    }

    //======================================================================
    //이름과 소개글 수정하기
    @PutMapping("/{id}")
    public void modify(@PathVariable String id, @RequestBody NameValueList nameList) {
        memberService.modifyMember(id, nameList);
    }

    //======================================================================
    //아이디로 맴버 지우기
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  String id) {
        memberService.removeMember(id);
    }

}
