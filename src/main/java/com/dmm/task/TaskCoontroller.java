package com.dmm.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		LocalDate dayweek2 = beforemonth.plusDays(7);
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
		
		
		model.addAttribute("matrix",month);
		model.addAttribute("week",week);
		return "main";
	}
	

	@RequestMapping("/loginForm")
	public String login() {
		return "login";
	}

}
