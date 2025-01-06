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
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.InfoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.context.WebApplicationContext;
// import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;


@Controller
public class InfoController {
    @Autowired
    private InfoService infoService;
	// private WebApplicationContext context; 
    
    @PostMapping("/postInfo")
    public @ResponseBody ResponseDTO<?> postInfo(@RequestBody Info info, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        info.setUser(loginUser);
        info.setCount(0);
        infoService.postInfo(info);
        return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항이 등록되었습니다.");
    }
    
    @GetMapping("/getInfo")
    public @ResponseBody ResponseDTO<?> getInfo() {
        return new ResponseDTO<>(HttpStatus.OK.value(), infoService.getInfo());
    }

    @DeleteMapping("/deleteInfo")
    public @ResponseBody ResponseDTO<?> deleteInfo(@RequestBody Info info) {
        int id = info.getId();
        infoService.deleteInfo(id);
        return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 공지사항이 삭제되었습니다.");
    }

    @PutMapping("/putInfo")
    public @ResponseBody ResponseDTO<?> putInfo(@RequestBody Info info) {
        infoService.putInfo(info);
        return new ResponseDTO<>(HttpStatus.OK.value(), info.getId() + "번 공지사항이 수정되었습니다.");
    }

    // @PostMapping(value = "/imageUpload")
    // // @RequestParam은 자바스크립트에서 설정한 이름과 반드시 같아야합니다.
	// public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
	// 	try {
    //         System.out.println(file);
	// 		// 서버에 저장할 경로
	// 		String uploadDirectory = context.getServletContext().getRealPath("/resources/asset/image/upload"); 
			
	// 		// 업로드 된 파일의 이름
	// 		String originalFileName = file.getOriginalFilename();
			
	// 		// 업로드 된 파일의 확장자
	// 		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			
	// 		// 업로드 될 파일의 이름 재설정 (중복 방지를 위해 UUID 사용)
	// 		String uuidFileName = UUID.randomUUID().toString() + fileExtension;
			
	// 		// 위에서 설정한 서버 경로에 이미지 저장
	// 		file.transferTo(new File(uploadDirectory, uuidFileName));
		
	// 		System.out.println("************************ 업로드 컨트롤러 실행 ************************");
	// 		System.out.println(uploadDirectory);
			
	// 		// Ajax에서 업로드 된 파일의 이름을 응답 받을 수 있도록 해줍니다.
	// 		return ResponseEntity.ok(uuidFileName);
	// 	} catch (Exception e) {
	// 		return ResponseEntity.badRequest().body("이미지 업로드 실패");
	// 	}
		
	// }
}
