package gr.boomer.backend.service;

import gr.boomer.backend.model.Boomer;
import gr.boomer.backend.db.BoomerDao;
import gr.boomer.backend.printers.CheapestPartsPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CheapestService {

    static Logger logger = LoggerFactory.getLogger(CheapestService.class);

    public static String run(String serachId) {
        List<Boomer> cheapestParts = BoomerDao.queryCheapestItems(serachId);
        return CheapestPartsPrinter.printCheapestParts(cheapestParts);
    }


}
