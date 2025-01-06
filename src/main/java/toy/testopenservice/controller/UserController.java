package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/join")
        public String insertUser() {
            return "/user/join";
        }

    @PostMapping("/auth/join")
    public @ResponseBody ResponseDTO<?> insertUser(@RequestBody User user) {
        User findUser = userService.getUser(user.getUserid());

        if (findUser.getUserid() == null) {
            userService.insertUser(user);
            return new ResponseDTO<>(HttpStatus.OK.value()
                , user.getUserid() + " 계정이 등록되었습니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value()
                , "요청하신 계정 " + user.getUserid() + "이(가) 이미 존재합니다.");
        }
    }
}