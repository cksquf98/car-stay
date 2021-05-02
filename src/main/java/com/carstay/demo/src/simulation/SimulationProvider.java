package com.carstay.demo.src.simulation;

import com.carstay.demo.config.BaseException;
import com.carstay.demo.src.friend.model.GetFriendRes;
import com.carstay.demo.src.simulation.model.*;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.carstay.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class SimulationProvider {
    private final SimulationDao simulationDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SimulationProvider(SimulationDao simulationDao, JwtService jwtService) {
        this.simulationDao = simulationDao;
        this.jwtService = jwtService;
    }

    public List<GetSimulationRes> getUserItems(String userId) throws BaseException {
        try {
            System.out.println("provider1");
            List<GetSimulationRes> getSimulationRes = simulationDao.getUserItems(userId);
            System.out.println("provider" + getSimulationRes);
            return getSimulationRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetItemRes> getItems() throws BaseException {
        try {
            System.out.println("provider1");
            List<GetItemRes> getItemRes = simulationDao.getItems();
            System.out.println("provider" + getItemRes);
            return getItemRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetCarRes> getCars() throws BaseException {
        try {
            System.out.println("provider2");
            List<GetCarRes> getCarRes = simulationDao.getCars();
            System.out.println("provider" + getCarRes);
            return getCarRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
