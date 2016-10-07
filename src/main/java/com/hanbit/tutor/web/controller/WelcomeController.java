package com.hanbit.tutor.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.tutor.core.session.LoginRequired;
import com.sun.media.jfxmedia.logging.Logger;

@Controller
public class WelcomeController {

	@LoginRequired
	@RequestMapping("/")
	public String welcome() {

		return "welcome";
	}

	@LoginRequired
	@RequestMapping("/api/data")
	@ResponseBody
	public Map getData(String dummy) {
		Map map = new HashMap();

		map.put("name", "Hanbit");
		map.put("message", "Hello");


		return map;
	}

}
