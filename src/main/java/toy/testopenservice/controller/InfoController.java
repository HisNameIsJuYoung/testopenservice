package toy.testopenservice.controller;

// import java.io.File;
// import java.io.IOException;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import toy.testopenservice.domain.Info;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.CommonResponseDTO;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.InfoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;


@Controller
public class InfoController {
    @Autowired
    private InfoService infoService;
	// private WebApplicationContext context; 
    
    @GetMapping("/getInfo")
    public @ResponseBody ResponseDTO<?> getInfo(HttpSession session) {
        User user = ((User) session.getAttribute("loginUser"));
        String userId = user.getUserId();
        String userName = user.getUserName();
        Object data = infoService.getInfo();

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(userId, userName, data);

        return new ResponseDTO<>(HttpStatus.OK.value(), commonResponseDTO);
    }

    @PostMapping("/postInfo")
    public @ResponseBody ResponseDTO<?> postInfo(@RequestBody Info info, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        info.setUser(loginUser);
        info.setCount(0);
        infoService.postInfo(info);
        return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항이 등록되었습니다.");
    }

    @DeleteMapping("/deleteInfo")
    public @ResponseBody ResponseDTO<?> deleteInfo(@RequestBody Info info) {
        int id = info.getId();
        infoService.deleteInfo(id);
        return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 공지사항이 삭제되었습니다.");
    }

    @PutMapping("/putInfo")
    public @ResponseBody ResponseDTO<?> putInfo(@RequestBody Info info) {
        System.out.println(info);
        infoService.putInfo(info);
        return new ResponseDTO<>(HttpStatus.OK.value(), info.getId() + "번 공지사항이 수정되었습니다.");
    }
}
