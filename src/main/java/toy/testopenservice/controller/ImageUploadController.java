package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class ImageUploadController {
    @Autowired
	private final WebApplicationContext context; 
    
    @PostMapping(value = "/imageUpload")
    // @RequestParam은 자바스크립트에서 설정한 이름과 반드시 같아야합니다.
	public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        System.out.println("************************ 1. 업로드 컨트롤러 실행 ************************");
		try {
            // 서버에 저장할 경로
			String uploadDirectory = context.getServletContext().getRealPath("/resources/images/"); 
			// String uploadDirectory = "/Users/ysh/workspace/testopenservice/src/main/resources/static/image/"; 
            System.out.println("************************ 2. uploadDirectory ************************");
            System.out.println(uploadDirectory);
			
			// 업로드 된 파일의 이름
			String originalFileName = file.getOriginalFilename();
            System.out.println("************************ 3. originalFileName ************************");
            System.out.println(originalFileName);
			
			// 업로드 된 파일의 확장자
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            System.out.println("************************ 4. fileExtension ************************");
            System.out.println(fileExtension);
			
			// 업로드 될 파일의 이름 재설정 (중복 방지를 위해 UUID 사용)
			String uuidFileName = UUID.randomUUID().toString() + fileExtension;
            System.out.println("************************ 5. uuidFileName ************************");
            System.out.println(uuidFileName);
			
			// 위에서 설정한 서버 경로에 이미지 저장
			file.transferTo(new File(uploadDirectory, uuidFileName));
            System.out.println("************************ 6. file.transferTo ************************");
		
			System.out.println("************************ 2. 업로드 컨트롤러 실행 ************************");
			System.out.println(uploadDirectory);
			
			// Ajax에서 업로드 된 파일의 이름을 응답 받을 수 있도록 해줍니다.
			return ResponseEntity.ok(uuidFileName);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("이미지 업로드 실패");
		}
		
	}
}

// @RestController
// public class ImageUploadController {

//     private static final String UPLOAD_DIR = "/Users/ysh/workspace/testopenservice/src/main/resources/static/image/";

//     @PostMapping("/imageUpload")
//     public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
//         try {
//             // Ensure the upload directory exists
//             Path uploadPath = Paths.get(UPLOAD_DIR);
//             if (!Files.exists(uploadPath)) {
//                 Files.createDirectories(uploadPath);
//             }

//             // Save the file to the local disk
//             String fileName = file.getOriginalFilename();
//             Path filePath = uploadPath.resolve(fileName);
//             file.transferTo(filePath.toFile());
//             System.out.println("File uploaded successfully: " + fileName);

//             return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK.value(), fileName), HttpStatus.OK);
//         } catch (IOException e) {
//             e.printStackTrace();
//             return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to upload image"), HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
// }