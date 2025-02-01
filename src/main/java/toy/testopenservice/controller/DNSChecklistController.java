package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.DNSChecklist;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.CommonResponseDTO;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.DNSChecklistService;

@Controller
public class DNSChecklistController {
    @Autowired
    private DNSChecklistService dNSChecklistService;

    @GetMapping("/DNSChecklist")
    public String login() {
        return "/checklist/DNSChecklist";
    }

    @GetMapping("/getDNSChecklist")
    public @ResponseBody ResponseDTO<?> getDNSChecklist(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        String userId = user.getUserId();
        String userName = user.getUserName();
        String role = user.getRole().toString();
        String customs = user.getCustoms();
        String department = user.getDepartment();
        Object data = dNSChecklistService.getDNSChecklist(customs, department);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, role, data);
        
        return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
    }

    @PutMapping("/putDNSChecklist")
    public @ResponseBody ResponseDTO<?> putDNSChecklist(@RequestBody DNSChecklist dNSChecklist) {
        System.out.println(dNSChecklist);
        dNSChecklistService.putDNSChecklist(dNSChecklist);
        return new ResponseDTO<>(HttpStatus.OK.value(), dNSChecklistService.getDNSChecklistById(dNSChecklist.getId()));
    }
}
