package sems.controls.ajax;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sems.services.SubjectService;

@Controller
@RequestMapping("/subject")
public class MainControl {
	static Logger log = Logger.getLogger(MainControl.class);
	
	@Autowired
	SubjectService subjectService;

	@RequestMapping("/main")
	public String list(
			@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
			@RequestParam(value="pageSize",defaultValue="10") int pageSize, 
			Model model) {
		model.addAttribute("list", subjectService.list(pageNo, pageSize));
		return "main";
	}

}










