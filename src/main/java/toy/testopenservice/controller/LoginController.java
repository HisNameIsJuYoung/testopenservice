package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String login() {
        return "/system/login";
    }
    
    @PostMapping("/auth/login")
    public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
        User findUser = userService.getUser(user.getUserid());
        
        if (findUser.getUserid() == null) {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value()
            , "요청하신 계정 " + user.getUserid() + "이(가) 존재하지 않습니다.");
        } else {
            if (user.getPassword().equals(findUser.getPassword())) {
                session.setAttribute("loginUser", findUser);
                return new ResponseDTO<>(HttpStatus.OK.value()
                , user.getUserid() + "님 환영합니다.");
            } else {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value()
                , "비밀번호가 일치하지 않습니다.");
            }
        }
    }

    @GetMapping("/auth/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}