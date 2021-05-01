package com.carstay.demo.src.simulation;

import com.carstay.demo.config.BaseException;
import com.carstay.demo.config.BaseResponse;
import com.carstay.demo.src.simulation.model.GetSimulationRes;
import com.carstay.demo.src.simulation.model.PostSimulationReq;
import com.carstay.demo.src.simulation.model.PostSimulationRes;
import com.carstay.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app/simulation")


public class SimulationController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SimulationService simulationService;

    @Autowired
    private final SimulationProvider simulationProvider;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    public SimulationController(SimulationProvider simulationProvider, SimulationService simulationService, JwtService jwtService) {
        this.simulationProvider = simulationProvider;
        this.simulationService = simulationService;
        this.jwtService = jwtService;
    }



    /**
     * 사용자 가상공간 아이템 목록 불러오기 API
     * [GET] app/simulation/
     * @return String
     */
    @ResponseBody
    @GetMapping("/getItems/{userId}")
    public BaseResponse<List<GetSimulationRes>> getItems(@PathVariable("userId") String userId) {
        try {
            // Get Review
            List<GetSimulationRes> getSimulationRes = simulationProvider.getItems(userId);
            return new BaseResponse<>(getSimulationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 아이템 조회 API
     * [POST] /app/simulation/addItem
     * @return BaseResponse<PostSimulationRes> //itemNum 꼭 리턴
     */

    /**
     * 자동차 조회 API
     * [POST] /app/simulation/addItem
     * @return BaseResponse<PostSimulationRes> //carNum 꼭 리턴
     */

    /**
     * 아이템 추가 API
     * [POST] /app/simulation/addItem
     * @return BaseResponse<PostSimulationRes>
     */
    @ResponseBody
    @PostMapping("/addItem{userId}")
    public BaseResponse<PostSimulationRes> addItem( @PathVariable("userId") String userId, @RequestBody PostSimulationReq postSimulationReq) {
        try{
            /*if(postSimulationReq.getXLocation()==null)
            {
                    return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
                }
            }*/
            PostSimulationRes postSimulationRes = simulationService.addItem(userId,postSimulationReq);
            return new BaseResponse<>(postSimulationRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
