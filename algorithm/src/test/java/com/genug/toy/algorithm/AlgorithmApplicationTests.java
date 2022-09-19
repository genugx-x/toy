package com.genug.toy.algorithm;

import com.genug.toy.algorithm.programmers.codingtest.DfsBfs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    private DfsBfs dfsBfs;

    @Test
    void contextLoads() {
        // int[][] maps = new int[][] {{1,0,1,1},{1,0,1,0},{1,0,1,1},{1,1,1,0}};
        int[][] maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
//        assertEquals(11, dfsBfs.solution(maps));
        
        maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        assertEquals(-1, dfsBfs.solution(maps));

    }

    @Test
    @DisplayName("단어 변환 - 그래프 생성 테스트")
    void test() {
        /*
        String[] words = new String[] {"abcde"};
        System.out.println(words.length);
        for (String word : words) {
            char[] spelling = word.toCharArray();
            List<char[]> list = new ArrayList<>();
            for (int i = 0; i < spelling.length; i++) {
                char[] chars = new char[spelling.length];
                for (int j = 0; j < spelling.length; j++) {
                    if (i == j)
                        chars[j] = ' ';
                    else
                        chars[j] = spelling[j];
                }
                list.add(chars);
            }
            for (char[] chars : list) {
                System.out.println(Arrays.toString(chars));
            }
        }
         */

        assertEquals(4, dfsBfs.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
        assertEquals(0, dfsBfs.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));


    }

}
