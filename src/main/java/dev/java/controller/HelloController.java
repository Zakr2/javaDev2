package dev.java.controller;

import dev.java.DateProcessor;
import dev.java.Logging;
import dev.java.db.ConnectorDB;
import dev.java.db.daos.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@Controller
public class HelloController {

    @Value("${application.version}")
    private String buildVersion;

    private Logging logging = new Logging();

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(HttpServletRequest request) {
        logging.runMe(request);
        return "index";
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView showUsers(ModelMap map) {
//        System.out.println(System.getProperty("java.classpath"));
//        System.setProperty("java.classpath","C:\\Users\\zakre\\Desktop\\kseniya\\javaDev2\\src\\main\\java\\dev\\java\\db\\daos\\UserDao");
//        System.out.println(System.getProperty("java.classpath"));
        ModelAndView modelAndView = new ModelAndView("user");
        try(Connection connection=ConnectorDB.getConnection()){
            UserDao userDao=new UserDao(connection);
            modelAndView.addObject("listUsers",userDao.getAllEntities());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        modelAndView.addAllObjects(map);
        return modelAndView;
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView getBirthday(HttpServletRequest request, ModelMap map) {
        logging.runMe(request);
        String sBirthDate = request.getParameter("birthDate");
        Date birthDate = DateProcessor.tryParseDate(sBirthDate);
        ModelAndView modelAndView = new ModelAndView("index");
        if (birthDate == null) {
            modelAndView.addObject("error", "Invalid date format. Try again!");
        } else {
            modelAndView.addObject("birthdate", birthDate);
            modelAndView.addObject("age", DateProcessor.calcAge(birthDate));
            modelAndView.addObject("daysUntilNextBirthday", DateProcessor.calcDaysToBirth(birthDate));
        }
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "team2");
        model.addAttribute("version", buildVersion);
    }
}
