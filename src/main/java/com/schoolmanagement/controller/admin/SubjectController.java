package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;
    //index
    @GetMapping("/admin/subject")
    public String SubjectManage(Model model){
//        Iterable<Subjects> listSubject = subjectService.FindAllSubject();
//
//        model.addAttribute("listSubject" , listSubject);
//
//        return "/admin/subject/manage_subject";
        return SubjectPage(model , "", 1);
    }

    //index page and search
    @GetMapping("/admin/subject/page/{pagenumber}")
    public String SubjectPage(Model model , @RequestParam("datafind") String datafind , @PathVariable("pagenumber") int currentpage){
        Page<Subjects>  page = subjectService.subjectsPage(currentpage , datafind);
        long totalItems = page.getTotalElements();
        int totalPage = page.getTotalPages();
        Iterable<Subjects> listSubject = page.getContent();
        model.addAttribute("find", datafind);
        model.addAttribute("currentPage", currentpage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("listSubject", listSubject);

        return "/admin/subject/manage_subject";
    }
    //New
    @GetMapping("/admin/subject/insert")
    public String SubjectIndexAdd(Model model,Subjects subject){
        Iterable<User> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        model.addAttribute("subject", subject);

        return "/admin/subject/form_subject";
    }
    // Edit
    @GetMapping("/admin/subject/edit/{id}")
    public String SubjectEdit(Model model , @PathVariable("id") int id){
        Subjects subjects = subjectService.findBySubjectID(id);
        Iterable<User> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        model.addAttribute("subject", subjects);

        return "/admin/subject/form_subject";
    }

    //Post add , save
    @PostMapping("/admin/subject/save")
    public String SaveSubject(Model model , @Valid Subjects subjects , BindingResult result ){

        if(result.hasErrors()){
            return "admin/subject/form_subject";
        }

        subjectService.SaveSubject(subjects);
        return "redirect:/admin/subject";
    }

    //Detail
    @GetMapping("/admin/subject/detail/{id}")
    public String DetailSubject(Model model , @PathVariable("id") int id){
        Subjects subjects = subjectService.findBySubjectID(id);
        model.addAttribute("subject", subjects);
        model.addAttribute("listUser" , subjects.getUsers());

        return "/admin/subject/detail_subject";
    }

    @GetMapping("/admin/subject/detail/{id}/{userid}")
    public String DetailSubjectDelete(Model model , @PathVariable("id") int id , @PathVariable("userid") int userid){
        Subjects subjects = subjectService.findBySubjectID(id);
        User user = userService.findbyUserid(userid);

        subjects.getUsers().remove(user);

        subjectService.SaveSubject(subjects);

        return "redirect:/admin/subject/detail/"+subjects.getId();

    }
}
