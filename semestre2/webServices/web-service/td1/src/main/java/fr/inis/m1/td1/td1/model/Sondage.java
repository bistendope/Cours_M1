package fr.inis.m1.td1.td1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class Sondage {
    private long id;
    private String question;
    private Set<String> reponses;
    private Set<Vote> votes;

    public Sondage() {
        this(0L,"");
    }
    public Sondage(long id) {
        this(id, "");
    }
    public Sondage(long id, String question) {
        this.id = id;
        this.question = question;
        this.reponses = new HashSet<>();
        this.votes = new HashSet<>();
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Set<String> getReponses() {
        return reponses;
    }
    public void setReponses(Set<String> reponses) {
        this.reponses = reponses;
    }
    public void addReponses(String... rep) {
        for(String r: rep) {
            reponses.add(r);
        }
    }
    public void voter(Vote vote) {
        if (reponses.contains(vote.getChoix())) {
            votes.add(vote);
        }
    }

    @JsonIgnore
    public Set<Vote> getVotes() {
        return votes;
    }
}
