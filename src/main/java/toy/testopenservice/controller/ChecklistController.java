package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.ChecklistResult;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.CommonResponseDTO;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.ChecklistService;


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
        String userId = user.getUserId();
        String userName = user.getUserName();
        String role = user.getRole().toString();
        Object data = checklistService.getChecklist(userId, user.getCustoms(), user.getDepartment());
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, role, data);
        return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
    }

    @PutMapping("/putChecklist")
    public @ResponseBody ResponseDTO<?> putChecklist(@RequestBody ChecklistResult checklistResult) {
        ChecklistResult updatedChecklistResult = checklistService.putChecklist(checklistResult.getId(), checklistResult.getChckRslt());
        return new ResponseDTO<>(HttpStatus.OK.value(), updatedChecklistResult);
    }
}