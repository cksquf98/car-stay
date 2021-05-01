package com.carstay.demo.src.simulation;
import com.carstay.demo.src.friend.model.GetFriendRes;
import com.carstay.demo.src.simulation.model.GetSimulationRes;
import com.carstay.demo.src.simulation.model.PostSimulationReq;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.NamedNativeQuery;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SimulationDao {
    private JdbcTemplate jdbcTemplate;

    public List<GetSimulationRes> getItems(String userId) {
        String getUsersQuery = "select userId,xLocation,yLocation,itemImage,name,width,height,carName,carWidth,carLength,carHeight from Simulation,CarType,CarItem " +
                "where Simulation.carTypeId=CarType.carNum and Simulation.carItemId=CarItem.itemNum and Simulation.userId=?";
        String getUserParams = userId;
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetSimulationRes(
                        rs.getString("userId"),
                        rs.getFloat("xLocation"),
                        rs.getFloat("yLocation"),
                        rs.getString("itemImage"),
                        rs.getString("itemName"),
                        rs.getFloat("itemWidth"),
                        rs.getFloat("itemHeight"),
                        rs.getString("carName"),
                        rs.getFloat("carWidth"),
                        rs.getFloat("carLength"),
                        rs.getFloat("carHeight")),
                getUserParams);


    }

    public int addItem(String userId, PostSimulationReq postSimulationReq) {
        String createReviewQuery = "insert into Simulation(carTypeId,carItemId,xLocation,yLocation,userId) values (?, ?, ?, ?, ?)";
        Object[] createQueryParams = new Object[]{postSimulationReq.getCarNum(),postSimulationReq.getItemNum(),postSimulationReq.getXLocation(),postSimulationReq.getYLocation(), userId};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}
