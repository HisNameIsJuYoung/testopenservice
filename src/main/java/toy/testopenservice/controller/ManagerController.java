package toy.testopenservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.RoleType;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.CommonResponseDTO;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.ManagerService;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager")
    public String getManager(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        
        if (user == null) {
            return "로그인이 필요합니다.";
        } else {
            if (user.getRole().equals(RoleType.ADMIN)) {
                return "/system/manager";
            } else {
                return "관리자만 접근 가능합니다.";
            }
        }
    }

    @GetMapping("/auth/manager")
    public @ResponseBody ResponseDTO<?> getManageResult(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
        } else {
            if (!user.getRole().equals(RoleType.ADMIN)) {
                return new ResponseDTO<>(HttpStatus.FORBIDDEN.value(), "관리자만 접근 가능합니다.");
            } else {
                String userId = user.getUserId();
                String userName = user.getUserName();
                Map<String, Object> data = managerService.getChecklistResult(user);
                
                CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, data);
                
                return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
            }
        }
    }
}
