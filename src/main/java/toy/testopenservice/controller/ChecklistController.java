package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.Checklist;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.ChecklistService;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class ChecklistController {
    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/checklist")
    public String login() {
        return "/checklist/checklist";
    }

    @GetMapping("/getChecklist")
    public @ResponseBody ResponseDTO<?> getChecklist(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        String loginUser = user.getUserId();
        return new ResponseDTO<>(HttpStatus.OK.value(), checklistService.getChecklist(loginUser));
    }

    @PutMapping("putChecklist")
    public @ResponseBody ResponseDTO<?> putChecklist(@RequestBody Checklist checklist) {
        checklistService.putChecklist(checklist.getId(), checklist.getVariResu());
        return new ResponseDTO<>(HttpStatus.OK.value(), null);
    }
}