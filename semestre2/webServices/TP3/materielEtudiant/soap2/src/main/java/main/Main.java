package main;

import service.AnnuaireI;
import service.AnnuaireImpl;

import javax.xml.ws.Endpoint;

public class Main {
    AnnuaireI server = new AnnuaireImpl();
    Endpoint endpoint = Endpoint.publish("http://localhost:9191/annuaire", server);
}
