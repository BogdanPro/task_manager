package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import ua.kiev.prog.database.main.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.prog.database.main.entity.User;
import ua.kiev.prog.security.CustomUserDetailsUser;


import javax.xml.bind.DatatypeConverter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Bogdan on 30.06.2015.
 */
@Controller
public class MainController {

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    ManagerImpl manager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "login";
    }

    @RequestMapping(value = "registrationForm", method = RequestMethod.GET)
    public String registrationForm() {
        return "registration";
    }


    @RequestMapping(value = "/loginError", method = RequestMethod.POST)
    public ModelAndView loginErr() {
        return new ModelAndView("login", "errMsg", "Illegal email or password!");
    }

    @RequestMapping(value = "/user/index")
    public ModelAndView index() {
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDb = manager.loadUserByEmail(user.getEmail());

        return new ModelAndView("/user/index", "Tasks", userDb.getTasks());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView listClients(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam String confirm_password
    ) {

        if (!password.equals(confirm_password)) {
            return new ModelAndView("registration", "errMsg", "Password and confirm password values not equal!");
        }

        if (manager.loadUserByEmail(email) != null) {
            return new ModelAndView("registration", "errMsg", "Account with entered email already exists. Please, enter another email.");
        }

        // generate the "salt" value for password encoding
        byte[] saltBytes = KeyGenerators.secureRandom(2).generateKey();
        String salt = DatatypeConverter.printHexBinary(saltBytes);

        // creating password for smf ua.kiev.prog.database
        String passwordForForum = passwordEncoder.encodePassword(username.toLowerCase() + password, null);
        password = passwordEncoder.encodePassword(password, salt);

        User user = new User();

        if (username.isEmpty() || username == null) {
            username = email;
        }

        user.setName(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setSalt(salt);
//        List<Group> groupList=new ArrayList<Group>();
//        groupList.add(manager.loadGroupByName("users"));
//        user.setGroups(groupList);
        user.setType(manager.loadTypeByName("users"));
        manager.saveUser(user);

        return new ModelAndView("login");
    }

    //tasks
    @RequestMapping(value = "/user/NewTaskForm/{id}")
    public ModelAndView newTaskForm(@PathVariable("id") long id) {
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("/user/newTask", "id", id);
    }

//    @RequestMapping(value = "/user/NewSubTaskForm/{id}")
//    public ModelAndView newSubTaskForm(@PathVariable("id") long id) {
//        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return new ModelAndView("/user/newSubTask", "id", id);
//    }

    @RequestMapping(value = "/user/editTaskForm/{id}")
    public ModelAndView editTaskForm(@PathVariable("id") long id) {
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ModelAndView("/user/editTask", "task", manager.loadTaskById(id));
    }

    @RequestMapping("/complete/{id}")
    public ModelAndView completeTask(@PathVariable("id") long id) {
        Task task=manager.loadTaskById(id);
        task.setCompleteness((byte) 100);
        manager.saveTask(task);

        completenessCalculator(task);
//        Task mainTask = task.getMainTask();
//        if(mainTask!=null) {
//            List<Task> subtasks = mainTask.getSubTasks();
//            float part =  100/(float)subtasks.size();
//            System.out.println("size of 1 part: " + part);
//            System.out.println("subtasks size " + subtasks.size());
//            float completeness = 0;
//            System.out.println("completeness: " + completeness);
//            for (Task subtask : subtasks) {
//                completeness += subtask.getCompleteness() == 100 ? part : 0;
//                System.out.println("completeness: " + completeness);
//            }
//            System.out.println("byte completeness: "+(byte)completeness);
//            mainTask.setCompleteness((byte)completeness);
//            manager.saveTask(mainTask);
//        }
        return new ModelAndView("/user/task", "task", task);
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(
            @RequestParam String text,
            @RequestParam Long taskId
    ) {
        Task task=manager.loadTaskById(taskId);
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Commentary commentary=new Commentary();
        commentary.setUser(manager.loadUserByEmail(user.getEmail()));
        commentary.setTask(task);
        commentary.setText(text);
        commentary.setCreationTime(new Date(System.currentTimeMillis()));
        manager.saveCommentary(commentary);
        task.getCommentaries().add(commentary);
        return new ModelAndView("/user/task", "task", task);
    }

    @RequestMapping("/task/{id}")
    public ModelAndView getTask(@PathVariable("id") long id) {
        Task task=manager.loadTaskById(id);
        return new ModelAndView("/user/task", "task", manager.loadTaskById(id));
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable("id") long id) {
        manager.deleteTask(id);
        return new ModelAndView("Task #"+id+"has been successfully deleted.");
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ModelAndView addTask(
            @RequestParam String groupName,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Date deadline,
            @RequestParam Long id
    ) {
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if(manager.loadUserByEmail(email) != null) {
//            return new ModelAndView("registration", "errMsg", "Account with entered email already exists. Please, enter another email.");
//        }

        Task task = new Task();
        task.setCompleteness((byte) 0);
        if (!groupName.equals("none"))
            task.setGroup(manager.loadGroupByName(groupName));
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadline);
//        task.setUser(manager.loadUserByEmail(user.getEmail()));
        task.setCreationTime(new Date(System.currentTimeMillis()));
        if(id==0) {
            manager.saveTask(task);
            User userDb = manager.loadUserByEmail(user.getEmail());
            return new ModelAndView("/user/index", "Tasks", userDb.getTasks());
        }else {
            Task mainTask = manager.loadTaskById(id);
            task.setMainTask(mainTask);
            List<Task> subtasks=mainTask.getSubTasks();
            subtasks.add(task);
            float part = 100/(float)subtasks.size();
            float completeness=0;
            for (Task subtask : subtasks) completeness += subtask.getCompleteness() == 100 ? part : 0;
            mainTask.setSubTasks(subtasks);
            mainTask.setCompleteness((byte) completeness);
            manager.saveTask(task);
            manager.saveTask(mainTask);
            completenessCalculator(mainTask);

//            Task mainTask = manager.loadTaskById(id);
//            task.setMainTask(mainTask);
//            List<Task> subtasks=mainTask.getSubTasks();
//            subtasks.add(task);
//            float part = 100/(float)subtasks.size();
//            System.out.println("size of 1 part: "+part);
//            System.out.println("subtasks size "+subtasks.size());
//            float completeness=0;
//            System.out.println("completeness: " + completeness);
//            for (Task subtask : subtasks){
//                completeness += subtask.getCompleteness() == 100 ? part : 0;
//                System.out.println("completeness: "+completeness);
//            }
//            System.out.println("byte completeness: "+(byte)completeness);
//            mainTask.setSubTasks(subtasks);
//            mainTask.setCompleteness((byte) completeness);
//            manager.saveTask(task);
//            manager.saveTask(mainTask);
//            completenessCalculator(mainTask);

            //without main task reloading new subtask id is 0
            return new ModelAndView("/user/task", "task", manager.loadTaskById(mainTask.getId()));
        }
    }


//    @RequestMapping(value = "/addSubTask", method = RequestMethod.POST)
//    public ModelAndView addSubTask(
//            @RequestParam String groupName,
//            @RequestParam String name,
//            @RequestParam String description,
//            @RequestParam Date deadline,
//            @RequestParam Long id
//    ) {
//        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
////        if(manager.loadUserByEmail(email) != null) {
////            return new ModelAndView("registration", "errMsg", "Account with entered email already exists. Please, enter another email.");
////        }
//
//        Task task = new Task();
//        task.setCompleteness((byte) 0);
//        if (!groupName.equals("none"))
//            task.setGroup(manager.loadGroupByName(groupName));
//        task.setName(name);
//        task.setDescription(description);
//        task.setDeadline(deadline);
////        task.setUser(manager.loadUserByEmail(user.getEmail()));
//        task.setCreationTime(new Date(System.currentTimeMillis()));
//        Task mainTask = manager.loadTaskById(id);
//        task.setMainTask(mainTask);
//        mainTask.getSubTasks().add(task);
//        manager.saveTask(task);
//        manager.saveTask(mainTask);
//
//        //without main task reloading new subtask has id 0
//        return new ModelAndView("/user/task", "task", manager.loadTaskById(mainTask.getId()));
//    }

    @RequestMapping(value = "/editTask", method = RequestMethod.POST, params={"submit"})
    public ModelAndView editTask(
            @RequestParam String groupName,
            @RequestParam(value = "name",required=true) String name,
            @RequestParam(value = "description",required=true) String description,
            @RequestParam(value = "deadline",required=false) Date deadline,
            @RequestParam(value = "email",required=false) String email,
            @RequestParam(value = "id",required=true) Long id
    ) {
        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        @DateTimeFormat(pattern = "dd-MM-yyyy")
//        if(manager.loadUserByEmail(email) != null) {
//            return new ModelAndView("registration", "errMsg", "Account with entered email already exists. Please, enter another email.");
//        }

        Task task = manager.loadTaskById(id);

//        task.setCompleteness((byte) 0);
        if (!groupName.equals("none"))
            task.setGroup(manager.loadGroupByName(groupName));
        else
            task.setGroup(null);
        if(!name.equals(""))
        task.setName(name);
        if(!description.equals(""))
        task.setDescription(description);
        if(deadline!=null)
        task.setDeadline(deadline);
        if(email!=null && !email.equals("")){
            User userDb = manager.loadUserByEmail(email);
            if(userDb!=null)
        task.setUser(userDb);
        }
        task.setCreationTime(new Date(System.currentTimeMillis()));
        manager.saveTask(task);


        return new ModelAndView("/user/task", "task", task);
    }



    @RequestMapping(value = "/admin/search")
    public ModelAndView adminIndex() {
//        CustomUserDetailsUser user = (CustomUserDetailsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userDb=manager.loadUserByEmail(user.getEmail());
//        ,"Tasks", userDb.getTasks()
        return new ModelAndView("/admin/search");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, params={"submit"})
    public ModelAndView search(
            @RequestParam String groupName,
            @RequestParam(value = "namePattern",required=true) String namePattern,
            @RequestParam(value = "descriptionPattern",required=true) String descriptionPattern,
//            @RequestParam(required = false) Date deadline,
            @RequestParam(value = "email",required=true) String email
    ) {
//        System.out.println(groupName);
//        System.out.println(namePattern);
//        System.out.println(descriptionPattern);
//        System.out.println(email);
//        System.out.println("@@@@@@@@@");
        User DBuser = manager.loadUserByEmail(email);
        Group group=groupName.equals("none") ? null : manager.loadGroupByName(groupName);
        if (DBuser == null && !email.equals(""))
            return new ModelAndView("/admin/search", "errMsg", "Incorrect email. Please, enter another email.");

        if (groupName.equals("all") && email.equals(""))
            return new ModelAndView("/admin/search", "tasks", manager.listTasksByNameAndDescription("%" + namePattern + "%", "%" + descriptionPattern + "%"));

        if (!groupName.equals("all") && email.equals(""))
            return new ModelAndView("/admin/search", "tasks", manager.listTasksByNameAndDescriptionAndGroup("%" + namePattern + "%", "%" + descriptionPattern + "%", group));

        if (groupName.equals("all") && !email.equals(""))
            return new ModelAndView("/admin/search", "tasks", manager.listTasksByNameAndDescriptionAndUser("%" + namePattern + "%", "%" + descriptionPattern + "%", DBuser));

        return new ModelAndView("/admin/search", "tasks", manager.listTasksByConditionsWithoutDeadline("%" + namePattern + "%", "%" + descriptionPattern + "%", group, DBuser));
    }

    //support methods

    //recursive calculation of task completeness
    public Task completenessCalculator(Task task){
        Task mainTask=task.getMainTask();
        if(mainTask!=null) {
            List<Task> subtasks = mainTask.getSubTasks();
            float part = 100 / (float) subtasks.size();
            float completeness = 0;
            for (Task subtask : subtasks) completeness += subtask.getCompleteness() == 100 ? part : 0;
            mainTask.setCompleteness((byte) completeness);
            manager.saveTask(mainTask);
            return completenessCalculator(mainTask);
        }else return mainTask;
//        Task mainTask=task.getMainTask();
//        if(mainTask!=null) {
//            List<Task> subtasks = mainTask.getSubTasks();
//            float part = 100 / (float) subtasks.size();
//            System.out.println("size of 1 part: " + part);
//            System.out.println("subtasks size " + subtasks.size());
//            float completeness = 0;
//            System.out.println("completeness: " + completeness);
//            for (Task subtask : subtasks) {
//                completeness += subtask.getCompleteness() == 100 ? part : 0;
//                System.out.println("completeness: " + completeness);
//            }
//            System.out.println("byte completeness: " + (byte) completeness);
//            mainTask.setCompleteness((byte) completeness);
//            manager.saveTask(mainTask);
//            return completenessCalculator(mainTask);
//        }else return mainTask;
    }
}
