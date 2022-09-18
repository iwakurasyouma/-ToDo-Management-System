package com.dmm.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dmm.task.data.entity.Tasks;

@Controller
public class TaskCoontroller {

	@RequestMapping("/create")
	public String index() {
		return "create";
	}

	@RequestMapping("/edit")
	public String test() {
		return "edit";
	}

	@RequestMapping("/main")
	public String main(Model model) {
		List<LocalDate> week = new ArrayList<>();
		List<List<LocalDate>> month = new ArrayList<>();
		LocalDate now = LocalDate.now();
		LocalDate firstday = now.withDayOfMonth(1);
		DayOfWeek week1 = firstday.getDayOfWeek();
		LocalDate beforemonth = firstday.minusDays(week1.getValue());
		for (int i = 0; i < 7; i++) {
			week.add(beforemonth);
			beforemonth = beforemonth.plusDays(1);
			if (i == 6) {
				month.add(week);
			}
		}

		int length = now.lengthOfMonth();
		LocalDate beforemonth2 =  firstday.minusDays(week1.getValue());
		LocalDate dayweek2 = beforemonth2.plusDays(7);
		LocalDate lastday = now.withDayOfMonth(length);
		DayOfWeek lastdate = lastday.getDayOfWeek();
		int lestdate = lastdate.getValue();
		for (int n = 7; n < length+(6-lestdate); n++) {
			week.add(dayweek2);
			DayOfWeek week2 = dayweek2.getDayOfWeek();
			dayweek2 = dayweek2.plusDays(1);
			if (week2.getValue() == 6) {
				month.add(week);
			}
		}
		MultiValueMap<LocalDate, Tasks> tasks = new LinkedMultiValueMap<LocalDate, Tasks>();
		model.addAttribute("tasks", tasks); 
		
		
		model.addAttribute("matrix",month);
		return "main";
	}
	

	@RequestMapping("/loginForm")
	public String login() {
		return "login";
	}

}
