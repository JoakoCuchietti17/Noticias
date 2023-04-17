/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.EggNews.controlador;


import com.Egg.eggNews.entidades.Noticia;
import com.egg.EggNews.Excepciones.MiException;
import com.egg.EggNews.servicio.NoticiaServicio;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Personal
 */
@Controller
@RequestMapping("/principal")
public class NoticiaControlador {
    
  
  @Autowired
  private NoticiaServicio noticiaServicio;
    
    
   
   
    @GetMapping("/inicio") 
    public String inicio(ModelMap modelo){
        List<Noticia> noticia = noticiaServicio.Consulta();
        modelo.addAttribute("noticia", noticia);
        return "inicio.html";
    }
    
    
    @GetMapping("/crear")
    public String Crear(){
        return "crear.html";
    }
    @PostMapping("/creado")
    public String Creado(@RequestParam String titulo , @RequestParam String cuerpo , ModelMap modelo){
      try {
           noticiaServicio.Creacion(titulo, cuerpo);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
         
            return "crear.html";
        }
      
      return "admin.html";
    }
    
    
  @GetMapping("/modificar")
    public String listar(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.Consulta();
        modelo.addAttribute("noticias" , noticias);
        return "modificar.html";
    }
    @GetMapping("/editar/{id}")
      public String modificar(@PathVariable String id, ModelMap modelo){
          modelo.put("noticia", noticiaServicio.getOne(id));
          return "editar.html";
      }
    @PostMapping("/editar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        try {
            noticiaServicio.Modificacion(titulo, cuerpo, id);
            return "redirect:../modificar";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "editar.html";
        }
    }

    
    
    
    @GetMapping("/eliminar")
    public String eliminar( ModelMap modelo){
       List<Noticia> noticias = noticiaServicio.Consulta();
        modelo.addAttribute("noticias" , noticias);
        return "eliminar.html";
        
    }
    @GetMapping("/editar/{id}")
      public String Borrar(@PathVariable String id, ModelMap modelo) throws MiException{
          modelo.put("noticia", noticiaServicio.getOne(id));

          return "editar.html";
      }
      
    
    @PostMapping("/editar/{id}")
    public String Borrar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        
        try {
            noticiaServicio.Borrar(titulo, cuerpo, id);
            return "redirect:../borrar";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "editar.html";
        }
    
     
}
}
    
