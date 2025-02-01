package toy.testopenservice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.Checklist;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.domain.DNSChecklist;
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
    public String getManager(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("loginUser");
        
        if (user == null) {
            return "redirect:/auth/login";
        } else {
            if (user.getRole().equals(RoleType.USER)) {
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<script> alert('관리자만 접근 가능합니다.'); history.go(-1); </script>"); 
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "redirect:/";
            } else {
                return "/system/manager";
            }
        }
    }

    @GetMapping("/auth/manager")
    public @ResponseBody ResponseDTO<?> getManageResult(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
        } else {
            if (user.getRole().equals(RoleType.USER)) {
                return new ResponseDTO<>(HttpStatus.FORBIDDEN.value(), "관리자만 접근 가능합니다.");
            } else {
                String userId = user.getUserId();
                String userName = user.getUserName();
                String role = user.getRole().toString();
                Map<String, Object> data = managerService.getChecklistResult(user);
                
                CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, role, data);
                
                return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
            }
        }
    }

    @GetMapping("/auth/allResult")
    public @ResponseBody ResponseDTO<?> getResult(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
        } else {
            if (user.getRole().equals(RoleType.USER)) {
                return new ResponseDTO<>(HttpStatus.FORBIDDEN.value(), "관리자만 접근 가능합니다.");
            } else {
                String userId = user.getUserId();
                String userName = user.getUserName();
                String role = user.getRole().toString();
                Map<String, Object> allRslt = managerService.getAllResult();
                
                CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, role, allRslt);
                
                return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
            }
        }
    }

    public void getCstmCheckResult() {
    }
}
