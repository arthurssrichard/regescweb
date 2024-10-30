package br.com.arthurssrichard.regescweb.controllers;

import br.com.arthurssrichard.regescweb.dto.RequisicaoFormProfessor;
import br.com.arthurssrichard.regescweb.models.Professor;
import br.com.arthurssrichard.regescweb.models.StatusProfessor;
import br.com.arthurssrichard.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired //n vou precisar de fazer um construtor recebendo professorRepository
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index(){
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");

        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(/* RequisicaoNovoProfessor requisicao */){ //em vez de instanciar o requisicaoNovoProfessor dentro da classe, posso botar aqui e o spring coloca sozinho
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("requisicaoFormProfessor", new RequisicaoFormProfessor());
        mv.addObject("listStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/professores/new");
            mv.addObject("requisicao", new RequisicaoFormProfessor());
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;
        }
        Professor professor = requisicao.toProfessor();
        this.professorRepository.save(professor);
        ModelAndView mv = new ModelAndView("redirect:/professores/" + professor.getId());
        return mv;
    }

    @GetMapping("/{id}")
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

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao){ //instanciando aqui mesmo, autoinjetado, diferente do nnew
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){ //deu bom
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);

            ModelAndView mv =  new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            //mv.addObject("requisicaoNovoProfessor", requisicao);

            return mv;
        }else{
            System.out.printf("Falha, professsor de id %s não encontrado",id);
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;
        }

        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){
            Professor professor = requisicao.toProfessor(optional.get());
            this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores/"+professor.getId());
        }else{
            System.out.printf("Falha, professsor de id %s não encontrado",id);
            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Verifica se o professor com o ID fornecido existe no banco de dados
        Optional<Professor> optionalProfessor = this.professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            // Se o professor existir, prossegue para deletar
            this.professorRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Professor " + id + " deletado com sucesso");
            redirectAttributes.addFlashAttribute("erro", false);
        } else {
            // Se o professor não existir, adiciona mensagem de erro ao redirecionamento
            redirectAttributes.addFlashAttribute("mensagem", "Professor não encontrado no banco");
            redirectAttributes.addFlashAttribute("erro", true);
        }

        return "redirect:/professores";
    }




}
