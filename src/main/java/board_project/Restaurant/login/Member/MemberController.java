package board_project.Restaurant.login.Member;


import board_project.Restaurant.login.annotation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "basic/members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "basic/members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/find")
    public String findForm(@ModelAttribute("member") Member member) {
        return "basic/members/findMemberForm";
    }

    @PostMapping("/find")
    public String findByName(@Valid @ModelAttribute Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "basic/members/findMemberForm";
        }
        Optional<Member> memberOpt = memberRepository.findByName(member.getName());
        if (memberOpt.isPresent()) {
            model.addAttribute("member", memberOpt.get());
            return "basic/members/memberDetail";
        } else {
            model.addAttribute("error", "Member not found");
            return "basic/members/findMemberForm";
        }
    }


    @GetMapping("/find/detail/{name}")
    public String findMember(@PathVariable String name, Model model) {
        Optional<Member> member = memberService.findByName(name);
        model.addAttribute("member", member.orElse(null));
        return "basic/members/memberDetail"; // memberDetail.html 페이지로 이동
    }

    @PostMapping("/find/detail/{name}")
    public String findByName(@PathVariable String name, Model model) {
        Optional<Member> memberOpt = memberService.findByName(name);
        if (memberOpt.isPresent()) {
            model.addAttribute("member", memberOpt.get());
            return "basic/members/memberDetail";
        } else {
            model.addAttribute("error", "Member not found");
            return "basic/members/findMemberForm";
        }
    }



}

