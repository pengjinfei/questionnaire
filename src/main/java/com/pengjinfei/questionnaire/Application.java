package com.pengjinfei.questionnaire;

import com.pengjinfei.questionnaire.dao.PersonRepository;
import com.pengjinfei.questionnaire.dao.QuestionsRepository;
import com.pengjinfei.questionnaire.dao.ScoreRepository;
import com.pengjinfei.questionnaire.domain.Person;
import com.pengjinfei.questionnaire.domain.Questions;
import com.pengjinfei.questionnaire.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @Autowired
    ScoreRepository scoreRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Questions> questionsList = questionsRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        List<Person> persons = personRepository.findAll();
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

    @RequestMapping("/submit")
    @ResponseBody
    public Map<String, String> submit(@RequestParam String name,@RequestParam String res,@RequestParam String suggestion) {
        Map<String, String> response = new HashMap<String, String>();
        try {
            Score score=new Score();
            score.setName(name);
            score.setScoreDetail(res);
            if (StringUtils.hasText(suggestion)) {
                score.setSuggestion(suggestion);
            }
            String[] split = res.split(",");
            int sum=0;
            for (String s : split) {
                sum += Integer.parseInt(s);
            }
            score.setRes(sum);
            scoreRepository.save(score);
            response.put("code", "success");
        } catch (Exception e){
            response.put("code", "fail");
        }
        return response;
    }

    @RequestMapping("/result")
    public String result(Model model) {
        List<Score> aggrateResult = scoreRepository.getAggrateResult();
        model.addAttribute("aggRes",aggrateResult);
        return "result";
    }

    @RequestMapping("/result/5/{name}")
    @ResponseBody
    public Object get5DetailByName(@PathVariable("name") String name) {
        List<Score> scoreList = scoreRepository.findByName(name);
        int[] distribute = new int[5];
        for (Score score : scoreList) {
            String scoreDetail = score.getScoreDetail();
            String[] split = scoreDetail.split(",");
            for (String s : split) {
                distribute[Integer.parseInt(s)-1]++;
            }
        }
        return distribute;
    }

    @RequestMapping("/result/20/{name}")
    @ResponseBody
    public Object get20DetailByName(@PathVariable("name") String name) {
        List<Score> scoreList = scoreRepository.findByName(name);
        int[][] distribute = new int[5][20];
        for (int i = 0; i <5; i++) {
            Arrays.fill(distribute[i],0);
        }
        for (Score score : scoreList) {
            String scoreDetail = score.getScoreDetail();
            String[] split = scoreDetail.split(",");
            for (int i = 0; i < split.length; i++) {
                distribute[Integer.parseInt(split[i])-1][i]++;
            }
        }
        return distribute;
    }

    @RequestMapping("/suggest/{name}")
    @ResponseBody
    public Object getSuggetsion(@PathVariable("name") String name) {
        return scoreRepository.findByNameAndSuggestionNotNull(name);
    }
}
