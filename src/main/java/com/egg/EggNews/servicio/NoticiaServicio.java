/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.EggNews.servicio;

import com.Egg.eggNews.entidades.Noticia;
import com.Egg.eggNews.repositorio.NoticiaRepositorio;
import com.egg.EggNews.Excepciones.MiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.web.servlet.function.RequestPredicates.param;
import static org.springframework.web.servlet.function.RequestPredicates.param;
import static org.springframework.web.servlet.function.RequestPredicates.param;


/**
 *
 * @author Personal
 */
@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    
 
   @Transactional
    public void Creacion(String titulo , String cuerpo) throws MiException{
        validar(titulo, cuerpo);
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticiaRepositorio.save(noticia);  
}
    
        
     @Transactional
    public void Modificacion (String titulo , String cuerpo , String id) throws MiException{
        validar(titulo, cuerpo);
        
        Optional<Noticia>respuestanoticia = noticiaRepositorio.findById(id);
        
       if(respuestanoticia.isPresent()){
          Noticia noticia = respuestanoticia.get();
          noticia.setCuerpo(cuerpo);
          noticia.setTitulo(titulo);
          noticiaRepositorio.save(noticia);
       }
  
}
    
    
       public List<Noticia> Consulta(){
        
        List<Noticia> noticia = new ArrayList();
        
        noticia = noticiaRepositorio.findAll();
        
        return noticia;
    }
       
   
    
     
   @Transactional
    public void Borrar(String titulo , String cuerpo , String id) throws MiException{
        validar(titulo, cuerpo);
        Optional<Noticia>respuestanoticia = noticiaRepositorio.findById(id);
        
         if(respuestanoticia.isPresent()){
          Noticia noticia = respuestanoticia.get();
      
          noticiaRepositorio.delete(noticia);
       }
    }
          
      
    
    private void validar(String titulo , String cuerpo) throws MiException{
        
        if(titulo.isEmpty() ||  titulo==null){
             throw new MiException("el titulo no puede ser nulo");
        }
        
        if(cuerpo.isEmpty() ||  cuerpo==null){
             throw new MiException("el cuerpo no puede ser nulo");
        }
       
    }
    
    public Noticia getOne(String id){
           return noticiaRepositorio.getOne(id);
       }
    
    
}

