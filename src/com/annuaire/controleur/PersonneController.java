package com.annuaire.controleur;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller()
@RequestMapping("/my")
public class PersonneController {

    protected final Log logger = LogFactory.getLog(getClass());

    

    
    /**************************** CREER PERSONNE ***************************/

    @RequestMapping(value = "/creationPersonne", method = RequestMethod.GET)
    public String creerPersonne() {
        return "creerPersonne";
    }
    
    /**************************** LISTE PERSONNE ****************************/
    
    @RequestMapping(value = "/listePersonnes", method = RequestMethod.GET)
    public String listPersonne() {
        return "listerPersonnes";
    }

    
    @ModelAttribute("groupe_personne")
    public Map<String, String> productTypes() {
        Map<String, String> types = new LinkedHashMap<String, String>();
        types.put("type1", "Type 1");
        types.put("type2", "Type 2");
        types.put("type3", "Type 3");
        types.put("type4", "Type 4");
        types.put("type5", "Type 5");
        return types;
    }
}