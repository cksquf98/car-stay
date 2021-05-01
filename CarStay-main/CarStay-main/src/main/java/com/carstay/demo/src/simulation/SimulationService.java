package com.carstay.demo.src.simulation;


import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.simulation.SimulationDao;
import com.carstay.demo.src.simulation.SimulationProvider;
import com.carstay.demo.src.simulation.model.PostSimulationReq;
import com.carstay.demo.src.simulation.model.PostSimulationRes;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carstay.demo.config.secret.Secret;
import static com.carstay.demo.config.BaseResponseStatus.*;

@Service
public class SimulationService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SimulationDao simulationDao;
    private final SimulationProvider simulationProvider;
    private final JwtService jwtService;

    @Autowired
    public SimulationService(SimulationDao simulationDao, SimulationProvider simulationProvider, JwtService jwtService) {
        this.simulationDao = simulationDao;
        this.simulationProvider = simulationProvider;
        this.jwtService = jwtService;

    }
    public PostSimulationRes addItem(String userId,PostSimulationReq postSimulationReq) throws BaseException {

        try{
            int simulationId = simulationDao.addItem(userId, postSimulationReq);
            return new PostSimulationRes(simulationId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
