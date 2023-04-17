/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Egg.eggNews.controladores;

import com.Egg.eggNews.entidades.Noticia;

import com.Egg.eggNews.servicios.NoticiaServicio;
import com.egg.EggNews.Excepciones.MiException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/principal")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
        List<Noticia> noticia = noticiaServicio.Consulta();
        modelo.addAttribute("noticia", noticia);
        return "inicio.html";
    }

    @GetMapping("/admin")
    public String Administrador() {
        return "admin.html";
    }

    @GetMapping("/crear")
    public String Crear() {
        return "crear.html";
    }

    @PostMapping("/creado")
    public String Creado(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {
        try {
            noticiaServicio.Creacion(titulo, cuerpo);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "crear.html";
        }
        return "admin.html";
    }

    @GetMapping("/modificar")
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.Consulta();
        modelo.addAttribute("noticias", noticias);
        return "modificar.html";
    }

    @GetMapping("/modi/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "modi.html";
    }

    @PostMapping("/modi/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo) {
        try {
            noticiaServicio.Modificacion(titulo, cuerpo, id);
            return "redirect:../modificar";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "modi.html";
        }
    }

    @GetMapping("/eliminar")
    public String listarEliminar(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.Consulta();
        modelo.addAttribute("noticias", noticias);
        return "eliminar.html";
    }
    
    

    @GetMapping("/Darbaja/{id}")
    public String Darbaja(ModelMap modelo, @PathVariable String id) {
        noticiaServicio.Darbaja(id);
        return "redirect:/";
    }

}
