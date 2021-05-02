package com.carstay.demo.src.simulation;

import com.carstay.demo.src.simulation.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;


@Repository
public class SimulationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public List<GetSimulationRes> getUserItems(String userId) {
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
    public List<GetItemRes> getItems() {
        String getUsersQuery = "select itemNum,itemType,itemName,itemSite,itemImage,itemWidth,itemHeight from CarItem";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetItemRes(
                        rs.getInt("itemNum"),
                        rs.getString("itemType"),
                        rs.getString("itemName"),
                        rs.getString("itemSite"),
                        rs.getString("itemImage"),
                        rs.getFloat("itemWidth"),
                        rs.getFloat("itemHeight"))
        );
    }
    public List<GetCarRes> getCars() {
        String getUsersQuery = "select carNum,carBrand,carName,carWidth,carLength,carHeight from CarType ";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetCarRes(
                        rs.getInt("carNum"),
                        rs.getString("carBrand"),
                        rs.getString("carName"),
                        rs.getFloat("carWidth"),
                        rs.getFloat("carLength"),
                        rs.getFloat("carHeight"))
        );
    }
    public int addItem(String userId, PostSimulationReq postSimulationReq) {
        String createReviewQuery = "insert into Simulation(carTypeId,carItemId,xLocation,yLocation,userId) values (?, ?, ?, ?, ?)";
        Object[] createQueryParams = new Object[]{postSimulationReq.getCarNum(),postSimulationReq.getItemNum(),postSimulationReq.getXLocation(),postSimulationReq.getYLocation(), userId};
        this.jdbcTemplate.update(createReviewQuery, createQueryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}
