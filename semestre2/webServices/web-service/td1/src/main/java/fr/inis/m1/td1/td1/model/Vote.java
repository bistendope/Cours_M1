package fr.inis.m1.td1.td1.model;


public class Vote {
    private String votant;
    private String choix;

    public Vote() {
        this(null,null);
    }
    public Vote(String votant, String choix) {
        this.votant = votant;
        this.choix = choix;
    }
    public String getVotant() {
        return votant;
    }
    public void setVotant(String votant) {
        this.votant = votant;
    }
    public String getChoix() {
        return choix;
    }
    public void setChoix(String choix) {
        this.choix = choix;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return !(votant != null ? !votant.equals(vote.votant) : vote.votant != null);
    }
    @Override
    public int hashCode() {
        return votant != null ? votant.hashCode() : 0;
    }
}