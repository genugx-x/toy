package com.genug.toy.algorithm;

import com.genug.toy.algorithm.programmers.codingtest.DfsBfs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    private DfsBfs dfsBfs;

    @Test
    void contextLoads() {
        // int[][] maps = new int[][] {{1,0,1,1},{1,0,1,0},{1,0,1,1},{1,1,1,0}};
        int[][] maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        assertEquals(11, dfsBfs.solution(maps));
        
        maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        assertEquals(-1, dfsBfs.solution(maps));

    }

}
