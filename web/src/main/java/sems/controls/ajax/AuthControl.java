package sems.controls.ajax;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sems.services.AuthService;
import sems.services.UserGroup;
import sems.vo.AjaxResult;
import sems.vo.UserVo;

@Controller
@RequestMapping("/auth")
public class AuthControl {
	static Logger log = Logger.getLogger(AuthControl.class);
	
	@Autowired
	AuthService authService;
	
	/* 리턴 타입은 JSON으로 출력할 객체이다.
	 * - 자동으로 JSON 문자열로 변환하려면, 빈 설정파일에
	 *   JSON 변환 해결사를 등록해야 한다.
	 */
	@RequestMapping("/login")
	public AjaxResult login(
			String email, 
			String password, 
			@RequestParam(required=false) String saveEmail,
			HttpSession session,
			HttpServletResponse response,
			Model model) {
		try {
				UserVo userVo = authService.getLoginUser(
							email, password, UserGroup.STUDENT);
				
				AjaxResult result = null;
				if (userVo == null) {
					result =  new AjaxResult().setStatus("ok").setData("failure");
					
				} else {
					result = new AjaxResult().setStatus("ok")	.setData("success");
					session.setAttribute("loginUser", userVo);
					
					if (saveEmail.equals("true")) {
						Cookie cookie = new Cookie("loginEmail", email);
						cookie.setDomain("localhost"); // 서버 범위
						cookie.setPath("/web");					// 하위 폴더 범위
						
						response.addCookie(cookie);
					}
				}
				
				response.setContentType("text/html;charset=UTF-8");
				
				return result;
				
		} catch (Throwable ex) {
			return new AjaxResult()
					.setStatus("error")
					.setData(ex.getMessage());
		}
	}
	
	@RequestMapping("/logout")
  public String logout(HttpSession session) {
	  session.invalidate();
	  return "redirect:login.bit";
  }
}










