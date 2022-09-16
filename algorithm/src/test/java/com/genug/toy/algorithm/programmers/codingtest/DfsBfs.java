package com.genug.toy.algorithm.programmers.codingtest;

import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class DfsBfs {

    private static Logger log = LoggerFactory.getLogger(DfsBfs.class);
    private Integer count = 0;

    @DisplayName("게임 맵 최단거리")
    public int solution(int[][] maps) {
        this.answer = -1;
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        for (int[] line : maps)
            log.info(Arrays.toString(line));

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++)
                visited[i][j] = (maps[i][j] == 0);
        }
        for (boolean[] line : visited)
            log.info(Arrays.toString(line));

        Queue<int[]> task = new LinkedList<>();
        task.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        printVisited(0, 0, maps, visited);
        tasks.add(task);
        while (!tasks.isEmpty()) {
            task = tasks.poll();
            Queue<int[]> nextTask = new LinkedList<>();
            while (!task.isEmpty()) {
                int[] coordinate = task.poll();
                bfs(coordinate[0], coordinate[1], coordinate[2], maps, visited, nextTask);
            }
            if (nextTask.isEmpty())
                break;
            tasks.add(nextTask);
            // printVisited(maps, visited);
        }


        /*
        queue.add(new int[] {0, 0});
        visited[0][0] = true;
        printVisited(visited);
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            bfs(coordinate[0], coordinate[1], maps, visited);
        }
         */

        return this.answer; // 상대 팀 진영에 도달 못하는 경우 -1
    }

    @DisplayName("깊이 우선 탐색")
    private void dfs(int i, int j, int[][] maps, boolean[][] visited) {
        // log.info("{} {} --- {}", i,  j, ++count);
        visited[i][j] = true;

        // 하
        if (i+1 < maps.length) {
            if (!visited[i+1][j])
                dfs(i+1, j, maps, visited);
        } else
            return;

        // 우
        if (j+1 < maps[0].length && !visited[i][j+1]) {
            dfs(i, j+1, maps, visited);
        }


    }

    private Queue<int[]> queue = new LinkedList<>();
    private Queue<Queue<int[]>> tasks = new LinkedList<>();

    @DisplayName("너비 우선 탐색")
    private void bfs(int i, int j, int[][] maps, boolean[][] visited) {
        // 하
        if (i+1 < maps.length && !visited[i+1][j]) {
            visited[i + 1][j] = true;
            queue.add(new int[]{i + 1, j});
        }
        // 우
        if (j+1 < maps[0].length && !visited[i][j+1]) {
            visited[i][j + 1] = true;
            queue.add(new int[]{i, j + 1});
        }
    }

    private int answer = -1;
    @DisplayName("너비 우선 탐색 - 게임 맵 최단거리")
    private void bfs(int i, int j, int c, int[][] maps, boolean[][] visited, Queue<int[]> nextTask) {
        if (i == maps.length-1 && j == maps[0].length-1) {
            log.info("count = {}", c);
            this.answer = c;
            return;
        }

        // 하
        if (i+1 < maps.length && maps[i+1][j] == 1 && !visited[i+1][j]) {
            visited[i+1][j] = true;
            nextTask.add(new int[]{i+1, j, c+1});
        }
        // 우
        if (j+1 < maps[0].length && maps[i][j+1] == 1 && !visited[i][j+1]) {
            visited[i][j+1] = true;
            nextTask.add(new int[]{i, j+1, c+1});
        }

        // 상
        if (i-1 >= 0 && maps[i-1][j] == 1 && !visited[i-1][j]) {
            visited[i-1][j] = true;
            nextTask.add(new int[]{i-1, j, c+1});
        }

        // 좌
        if (j-1 >= 0 && maps[i][j-1] == 1 && !visited[i][j-1]) {
            visited[i][j-1] = true;
            nextTask.add(new int[]{i, j-1, c+1});
        }
    }

    private void printVisited(int x, int y, int[][] maps, boolean[][] visited) {
        System.out.println("====" + ++count + "====");

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                if (i == x && j == y)
                    System.out.print('♠');
                else if (maps[i][j] == 1 && visited[i][j])
                    System.out.print('♤');
                else
                    System.out.print(maps[i][j] == 0 ? '★' : '☆');
            }
            System.out.println();
        }
    }

    private void printVisited(int[][] maps, boolean[][] visited) {
        System.out.println("====" + ++count + "====");

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                if (maps[i][j] == 1 && visited[i][j])
                    System.out.print('♤');
                else
                    System.out.print(maps[i][j] == 0 ? '★' : '☆');
            }
            System.out.println();
        }
    }

}
