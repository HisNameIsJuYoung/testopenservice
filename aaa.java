public String decryptRsa(PrivateKey privateKey, String securedValue) {
	 String decryptedValue = "";
	 try{
		Cipher cipher = Cipher.getInstance("RSA");
	   /*
    	* 암호화 된 값은 byte 배열이다.		
        * 이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경한다. 		
        * 서버측에서도 값을 받을 때 hex 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤에 복호화 과정을 수행한다.		
        */		
        byte[] encryptedBytes = hexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		decryptedValue = new String(decryptedBytes, "utf-8");  // 문자 인코딩 주의.
        } catch (Exception e) {
		 logger.info("decryptRsa Exception Error : "+e.getMessage());
	    }
        return decryptedValue;
    }
    /*
    * 16진 문자열을 byte 배열로 변환한다.
    */
    
public static byte[] hexToByteArray(String hex) {
    if (hex == null || hex.length() % 2 != 0) {
        return new byte[]{};
	}
    byte[] bytes = new byte[hex.length() / 2];
	for (int i = 0; i < hex.length(); i += 2) {
		byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
		bytes[(int) Math.floor(i / 2)] = value;
	}
    return bytes;
}
/* 로그인 체크 */
@RequestMapping(value = "/proc/login.proc",headers="Accept=application/json",method = RequestMethod.POST)public @ResponseBody JSONObject loginChk(HttpServletRequest request, Map mMap ) {
	List<UserList> loginMember = null;
	JSONObject listObj = new JSONObject();
		String uid = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	HttpSession session = request.getSession();
	PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
    //로그인전에 세션에 저장된 개인키를 가져온다.
    if (privateKey == null) 	{
 		listObj.put("state", "false");
	} else {
        try {
            //암호화처리된 사용자계정정보를 복호화 처리한다.
            String _uid = decryptRsa(privateKey, uid);
            String _pwd = decryptRsa(privateKey, pwd);
            //복호화 처리된 계정정보를 map에 담아서 iBatis와 연동한다.
            mMap.put("user_id",_uid);
            mMap.put("user_pwd", _pwd);
            //    ....... iBatis 처리 및 로그인후 session 처리		
        } catch(Exception e) {
            listObj.put("state", "false");
            logger.info("login ERROR : "+e.getMessage());
        }
    }
    return listObj;
}