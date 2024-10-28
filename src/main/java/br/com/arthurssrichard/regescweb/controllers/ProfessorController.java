package br.com.arthurssrichard.regescweb.controllers;

import br.com.arthurssrichard.regescweb.dto.RequisicaoNovoProfessor;
import br.com.arthurssrichard.regescweb.models.Professor;
import br.com.arthurssrichard.regescweb.models.StatusProfessor;
import br.com.arthurssrichard.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired //n vou precisar de fazer um construtor recebendo professorRepository
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index(){
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");

        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/professor/new";
        }
        Professor professor = requisicao.toProfessor();
        this.professorRepository.save(professor);
        return "redirect:/professores";
    }
}
