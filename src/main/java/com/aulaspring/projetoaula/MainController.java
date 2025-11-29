package com.aulaspring.projetoaula;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aulaspring.projetoaula.model.Registro;
import com.aulaspring.projetoaula.model.RegistroService;

@Controller
public class MainController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/calc")
public String calc(Model model){
    model.addAttribute("calc", new Registro());
    model.addAttribute("res", null);
    model.addAttribute("classificacao", "");
    return "calc";
}

@PostMapping("/calc")
public String calcularIMC(@ModelAttribute Registro calc, Model model){
    double imc = calc.getPeso() / (calc.getAltura() * calc.getAltura());
    String classificacao;

    if(imc < 18.5) classificacao = "Abaixo do peso";
    else if(imc < 25) classificacao = "Peso normal";
    else if(imc < 30) classificacao = "Sobrepeso";
    else classificacao = "Obesidade";

    model.addAttribute("res", imc);
    model.addAttribute("classificacao", classificacao);
    model.addAttribute("calc", calc);

    return "calc";
}

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("registro", new Registro());
        return "formRegistro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute Registro registro){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.inserirRegistro(registro);
        return "sucesso";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        RegistroService rs = context.getBean(RegistroService.class);
        ArrayList<Registro> registros = rs.listarRegistros();

        model.addAttribute("registros", registros);
        return "listarRegistro";
    }

    @GetMapping("/upd/registro/{id}")
    public String registroUpd(Model model, @PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        Registro r = rs.obterRegistro(id);

        model.addAttribute("registro", r);
        model.addAttribute("formAction", "/upd/registro/" + id);

        return "editarRegistro";
    }

    @PostMapping("/upd/registro/{id}")
    public String registroUpdSalvar(@ModelAttribute Registro registro, @PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.atualizarRegistro(id, registro);
        return "redirect:/listar";
    }

    @PostMapping("/del/registro/{id}")
    public String deletar(@PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.deletarRegistro(id);
        return "redirect:/listar";
    }

    @GetMapping("/pagina2")
    public String pagina2(Model model){
        RegistroService rs = context.getBean(RegistroService.class);

        model.addAttribute("registro", new Registro());
        model.addAttribute("lista", rs.listarRegistros());

        return "pagina2";
    }

    @PostMapping("/pagina2")
    public String registrarPagina2(@ModelAttribute Registro registro){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.inserirRegistro(registro);
        return "redirect:/pagina2";
    }

    @GetMapping("/pagina2/edit/{id}")
    public String editarPagina2(Model model, @PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        Registro r = rs.obterRegistro(id);

        model.addAttribute("registro", r);
        model.addAttribute("formAction", "/pagina2/edit/" + id);

        return "editarRegistro";
    }

    @PostMapping("/pagina2/edit/{id}")
    public String salvarEdicaoPagina2(@ModelAttribute Registro registro,
                                      @PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.atualizarRegistro(id, registro);

        return "redirect:/pagina2";
    }

    @PostMapping("/pagina2/del/{id}")
    public String deletarPagina2(@PathVariable int id){
        RegistroService rs = context.getBean(RegistroService.class);
        rs.deletarRegistro(id);
        return "redirect:/pagina2";
    }

}
