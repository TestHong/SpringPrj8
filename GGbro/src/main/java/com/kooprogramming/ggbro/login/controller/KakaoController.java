package com.kooprogramming.ggbro.login.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kooprogramming.ggbro.login.service.KakaoService;

@Controller
public class KakaoController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@GetMapping("/loginTest")
	public String loginTest() {
		return "test";
	}
	
	@GetMapping("/kakaoLogin")
	public String kakaoLogin(String code, HttpSession session) throws Exception {
		//System.out.println("code: " + code);
		String access_Token = kakaoService.getAccessToken(code);
        //System.out.println("controller access_token : " + access_Token);
		Map<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    
	    //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    if (userInfo.get("email") != null) {
	        //session.setAttribute("userId", userInfo.get("email"));
	        //session.setAttribute("image", userInfo.get("image"));
	        session.setAttribute("access_Token", access_Token);
	    	session.setAttribute("login", userInfo);
	    }
		//return "test";
	    return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.invalidate();
	    //return "test";
	    return "redirect:/";
	}

}
