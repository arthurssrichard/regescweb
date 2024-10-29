package br.com.arthurssrichard.regescweb.controllers;

import br.com.arthurssrichard.regescweb.dto.RequisicaoNovoProfessor;
import br.com.arthurssrichard.regescweb.models.Professor;
import br.com.arthurssrichard.regescweb.models.StatusProfessor;
import br.com.arthurssrichard.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/professores/new")
    public ModelAndView nnew(/* RequisicaoNovoProfessor requisicao */){ //em vez de instanciar o requisicaoNovoProfessor dentro da classe, posso botar aqui e o spring coloca sozinho
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("requisicaoNovoProfessor", new RequisicaoNovoProfessor());
        mv.addObject("listStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public ModelAndView create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/professores/new");
            mv.addObject("requisicao", new RequisicaoNovoProfessor());
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;
        }
        Professor professor = requisicao.toProfessor();
        this.professorRepository.save(professor);
        ModelAndView mv = new ModelAndView("redirect:/professores/" + professor.getId());
        return mv;
    }

    @GetMapping("/professores/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){ //deu bom
            Professor professor = optional.get();
            ModelAndView mv =  new ModelAndView("professores/show");
            mv.addObject("professor", professor);
            return mv;
        }else{
            System.out.printf("Falha, professsor de id %s não encontrado",id);
            return new ModelAndView("redirect:/professores");
        }


    }

}
