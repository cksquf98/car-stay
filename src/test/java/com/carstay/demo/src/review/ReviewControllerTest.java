package com.carstay.demo.src.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReviewControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ReviewDao reviewDao;



    @Test
    void getReviews() {
    }

    @Test
    void getReviewsBySpot() {
    }

    @Test
    void createReview() {
    }

    @Test
    void testCreateReview() {
    }

    @Test
    void createComment() {
//        reviewDao.createReview();
//
//        mvc.perform(post("/api/issues")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(req)))
//                .andDo(print())
//                .andExpect(status().isOk());

    }
}