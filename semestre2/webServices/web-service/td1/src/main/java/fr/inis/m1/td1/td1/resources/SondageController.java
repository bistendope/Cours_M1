package fr.inis.m1.td1.td1.resources;


import fr.inis.m1.td1.td1.model.Sondage;
import fr.inis.m1.td1.td1.model.Vote;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping(value = "/sondages")
public class SondageController {

    private static int compteur = 1;
    private Map<Long, Sondage> sondages = new HashMap<>();


    private static Map<Long, Sondage> initSondages() {
        Map<Long, Sondage> ms = new HashMap<>();

        Sondage s1 = new Sondage(compteur++, "Essai");

        s1.addReponses("oui");
        s1.addReponses("non");
        s1.addReponses("c'est quoi la question");
        s1.voter(new Vote("Albert", "oui"));
        s1.voter(new Vote("Bernard", "non"));
        ms.put(s1.getId(), s1);

        return ms;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Collection<Sondage> getSondages() {
        sondages = initSondages();
        return sondages.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sondage> findSondageById(@PathVariable("id") long id) {
        if (sondages.containsKey(id)) {
            return new ResponseEntity<Sondage>(sondages.get(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Sondage>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delSondageById(@PathVariable("id") long id) {
        if (sondages.containsKey(id)) {
            sondages.remove(id);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sondage> updateSondageById0(
            @PathVariable("id") long id,
            @RequestParam(name = "question") String question,
            @RequestParam(name = "responses") Set<String> responses) {
        if (sondages.containsKey(id)) {
            Sondage s = sondages.get(id);
            s.setQuestion(question);
            s.setReponses(responses);
            return new ResponseEntity<Sondage>(s, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Sondage>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Sondage> addSondage(
            @RequestBody Sondage s) {

        sondages.put(s.getId(), s);

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.setLocation(new URI("http://localhost:8080/sondages/" + s.getId()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Sondage>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/votes/", method = RequestMethod.GET)
    public ResponseEntity<Set<Vote>> findVotes(@PathVariable("id") long id) {
        if (sondages.containsKey(id)) {
            return new ResponseEntity<Set<Vote>>(sondages.get(id).getVotes(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Set<Vote>>(HttpStatus.BAD_REQUEST);
        }
    }
}
