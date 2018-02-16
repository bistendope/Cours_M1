package fr.inis.m1.td1.td1.resources;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/{nom}", method = RequestMethod.GET)
    public String hello(@PathVariable("nom") String nom){ //on prend le param 'nom' de l'url ex:/hello/john
        return "hello " + nom ;
    }
}
