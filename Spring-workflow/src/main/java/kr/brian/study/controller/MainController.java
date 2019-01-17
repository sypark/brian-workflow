package kr.brian.study.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.brian.study.service.WorkflowService;
import kr.brian.study.workflow.WorkflowException;

@Controller("mainController")
@RequestMapping("/main")
public class MainController {
	
	@Resource(name = "workflowService")
    protected WorkflowService workflowService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			workflowService.work();
		} catch (WorkflowException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView("main/index");
		
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {

		workflowService.add();
		
		ModelAndView mav = new ModelAndView("main/add");
		
		return mav;
	}
}
