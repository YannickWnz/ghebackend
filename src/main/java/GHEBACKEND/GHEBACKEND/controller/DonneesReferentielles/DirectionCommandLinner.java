package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import org.springframework.boot.CommandLineRunner;

import GHEBACKEND.GHEBACKEND.service.DonneesReferentielles.DirectionService;

public class DirectionCommandLinner implements CommandLineRunner{

    private final DirectionService directionService;

    public DirectionCommandLinner(DirectionService directionService){
        this.directionService = directionService;
    }

    @Override
    public void run(String... args) throws Exception{
       /*  List<DirectionModel> direction =  */
    }

}
