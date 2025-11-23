package com.aulaspring.projetoaula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aulaspring.projetoaula.model.CalculadoraIMC;
import com.aulaspring.projetoaula.model.Registro;
import com.aulaspring.projetoaula.model.RegistroService;

@Controller
public class MainController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/pagina1")
    public String pagina1(){
        return "pagina1";
    }

    @GetMapping("/pagina2")
    public String pagina2(Model model){
        model.addAttribute("registro", new Registro());
        model.addAttribute("lista", registroService.listarRegistros());
        return "pagina2";
    }

    @PostMapping("/pagina2")
    public String registrar(@ModelAttribute("registro") Registro registro, Model model){

        registroService.inserirRegistro(registro);

        return "redirect:/pagina2";
    }

    @GetMapping("/pagina2/delete")
    public String removerRegistro(@RequestParam("id") int id) {

        registroService.deletarRegistro(id);

        return "redirect:/pagina2";
    }

    @GetMapping("/pagina3")
    public String pagina3(){
        return "pagina3";
    }

    @GetMapping("/calc")
    public String calc(Model model){
        model.addAttribute("calc", new CalculadoraIMC(0, 0));
        return "calc";
    }

    @PostMapping("/calc")
    public String calcular(@ModelAttribute("calc") CalculadoraIMC calc, Model model){

        double res = calc.calcularIMC();
        String classificacao = calc.getClassificacao();

        model.addAttribute("calc", calc);
        model.addAttribute("res", res);
        model.addAttribute("classificacao", classificacao);
        
        return "calc";
    }
}
