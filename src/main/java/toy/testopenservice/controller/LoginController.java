package toy.testopenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import toy.testopenservice.domain.User;
import toy.testopenservice.dto.RSAPublicKeyDTO;
import toy.testopenservice.dto.ResponseDTO;
import toy.testopenservice.service.UserService;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String getUser() {
        return "/system/login";
    }

    @GetMapping("/auth/getRSA")
    public @ResponseBody ResponseDTO<?> getRSA(HttpSession session, HttpServletRequest request) {
        // RSA 테스트
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair keyPair = generator.genKeyPair();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            session.setAttribute("_RSA_WEB_Key_", privateKey);  //세션에 RSA 개인키를 세션에 저장한다.
            RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            String publicKeyModulus = publicSpec.getModulus().toString(16);
            String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
            request.setAttribute("RSAModulus", publicKeyModulus);  //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
            request.setAttribute("RSAExponent", publicKeyExponent);  //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
            RSAPublicKeyDTO rsaPublicKeyDTO = new RSAPublicKeyDTO(publicKeyModulus, publicKeyExponent);
            return new ResponseDTO<>(HttpStatus.OK.value(), rsaPublicKeyDTO);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error generating RSA keys");
        }
    }

    public String decryptRsa(PrivateKey privateKey, String securedValue) {
        String decryptedValue = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            byte[] encryptedBytes = hexToByteArray(securedValue);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedValue = new String(decryptedBytes, "UTF-8");  // Ensure correct character encoding
        } catch (Exception e) {
            System.out.println("decryptRsa Exception Error : " + e.getMessage());
        }
        return decryptedValue;
    }


    public String encodeSha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }

    @PostMapping("/auth/login")
    public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
        PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
        User findUser = userService.getUser(user.getUserId());
        String decryptPassword = decryptRsa(privateKey, user.getPassword());
        // String Sha256Password = this.encodeSha256(decryptPassword);
        String Sha256Password = decryptPassword;

        String userid = user.getUserId();

        if (findUser.getUserId() == null) {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "요청하신 계정 " + user.getUserId() + "이(가) 존재하지 않습니다.");
        } else {
            if (Sha256Password.equals(findUser.getPassword())) {
                if (userid.equals(decryptPassword)) {
                    session.setAttribute("loginUser", user);
                    return new ResponseDTO<>(Integer.parseInt(user.getUserId()), "패스워드를 변경해 주세요.");
                } else {
                    session.setAttribute("loginUser", findUser);
                    return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUserName() + "님 환영합니다.");
                }
            } else {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다.");
            }
        }
    }

    @GetMapping("/auth/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}