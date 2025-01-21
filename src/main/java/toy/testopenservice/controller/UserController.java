package toy.testopenservice.controller;

import java.security.PrivateKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginController loginController;

    @GetMapping("/auth/join")
    public String getUser() {
        return "/system/join";
    }

    @PutMapping("/auth/join")
    public @ResponseBody ResponseDTO<?> putUser(@RequestBody User user, HttpSession session) {
        User findUser = userService.getUser(user.getUserId());
        
        if (findUser.getUserId().equals(user.getUserId())) {
            PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
            String password = loginController.decryptRsa(privateKey, user.getPassword());
            if (password.length() < 9) {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호를 9자 이상으로 해 주세요.");
            } else {
                // password = loginController.encodeSha256(password);
                user.setPassword(password);
                userService.putUser(user);
                
                return new ResponseDTO<>(HttpStatus.OK.value(), user.getUserId() + " 계정이 업데이트되었습니다.");
            }
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value()
                , "요청하신 계정 " + user.getUserId() + "이(가) 이미 존재합니다.");
        }
    }
}