package edu.univorleans.simon;

public interface Board {

    int getSize();
    int getSequenceSize();
    void setRank(int i);
    void light(int i);
    void playLooseSound();

}
