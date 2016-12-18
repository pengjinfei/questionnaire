package com.pengjinfei.questionnaire;

import com.pengjinfei.questionnaire.dao.PersonRepository;
import com.pengjinfei.questionnaire.dao.QuestionsRepository;
import com.pengjinfei.questionnaire.domain.Person;
import com.pengjinfei.questionnaire.domain.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pengjinfei on 2016/12/17.
 * Description:
 */
@Controller
@SpringBootApplication
public class Application {

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Questions> questionsList = questionsRepository.findAll();
        model.addAttribute("questionsList", questionsList);

        List<String> departments = new ArrayList<String>();
        List<Person> all = personRepository.findAll();
        for (Person person : all) {
            if (!departments.contains(person.getDepartment())) {
                departments.add(person.getDepartment());
            }
        }
        model.addAttribute("deparments", departments);
        List<Person> persons = personRepository.findByDepartment(departments.get(0));
        model.addAttribute("persons", persons);
        return "index";
    }

    @RequestMapping("/questions")
    @ResponseBody
    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }

    @RequestMapping("/departments")
    @ResponseBody
    public List<String> getAllDepartments() {
        List<String> departments = new ArrayList<String>();
        List<Person> all = personRepository.findAll();
        for (Person person : all) {
            if (!departments.contains(person.getDepartment())) {
                departments.add(person.getDepartment());
            }
        }
        return departments;
    }

    @RequestMapping("/department/persons")
    @ResponseBody
    public List<Person> getPersonsByDeparment(@RequestParam String department) {
        return personRepository.findByDepartment(department);
    }
}
