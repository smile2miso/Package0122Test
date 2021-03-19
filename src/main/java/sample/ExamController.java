package sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {
	
	@RequestMapping("/exam/test1")
	public String examTest1() {
		return "exam text1 page";
	}
	
	@RequestMapping("/exam/test2")
	public String examTest2() {
		return "exam text2 page";
	}
	
	@RequestMapping("/exam/test3")
	public String examTest3() {
		return "exam text3 page";
	}
}
