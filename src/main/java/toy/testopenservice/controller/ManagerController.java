package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.RoleType;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.ManagerService;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager")
    public String getManager(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        System.out.println(user.getRole());
        if (user.getRole().equals(RoleType.ADMIN)) {
            return "/system/manager";
        } else {
            return "관리자만 접근 가능합니다.";
        }
    }
}
