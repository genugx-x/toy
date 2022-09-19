package com.genug.toy.algorithm.programmers.codingtest;

import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DfsBfs {

    private static Logger log = LoggerFactory.getLogger(DfsBfs.class);
    private Integer count = 0;

    @DisplayName("문자 변환")
    public int solution(String begin, String target, String[] words) {
        this.answer = 0;
        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word(begin));
        for (String word : words) {
            wordList.add(new Word(word));
        }

//        for (Word word : wordList) {
//            log.info("Word : {} --- first : {}, end : {}", word.original, word.first, word.end);
//            int count = 1;
//            for (String[] mid : word.mids) {
//                log.info("mid[{}] --- {}", count++, mid);
//            }
//            log.info("------------------------------------------------------------");
//        }

        List<List<Word>> graph = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordList.size(); i++) {
            List<Word> list = new ArrayList<>();
            list.add(wordList.get(i));
            for (int j = 0; j < wordList.size(); j++) {
                if (i == j)
                    continue;
                if (wordList.get(i).first.equals(wordList.get(j).first)) {
                    list.add(wordList.get(j));
                } else if (wordList.get(i).end.equals(wordList.get(j).end)) {
                    list.add(wordList.get(j));
                } else {
                    for (int n = 0; n < wordList.get(i).mids.size(); n++) {
                        if (wordList.get(i).mids.get(n)[0].equals(wordList.get(j).mids.get(n)[0]) &&
                                wordList.get(i).mids.get(n)[1].equals(wordList.get(j).mids.get(n)[1])) {

                            list.add(wordList.get(j));
                        }
                    }
                }
            }
            graph.add(list);
        }

        graph.get(0).get(0).visited = true;
        Queue<SearchInfo> searchInfos = new LinkedList<>();
        searchInfos.add(new SearchInfo(begin, 0, 0));
        Queue<Queue<SearchInfo>> task = new LinkedList<>();
        task.add(searchInfos);
        while(!task.isEmpty()) {
            searchInfos = task.poll();
            Queue<SearchInfo> nextSearchInfos = new LinkedList<>();
            while(!searchInfos.isEmpty()) {
                SearchInfo info = searchInfos.poll();
                bfs(target, info, graph, nextSearchInfos);
            }
            if (!nextSearchInfos.isEmpty())
                task.add(nextSearchInfos);
        }
        return this.answer;
    }

    void bfs(String target, SearchInfo info, List<List<Word>> graph, Queue<SearchInfo> nextSearchInfos) {

        log.info("{}---{}---{}", info.word, info.i, info.count);
        if (info.word.equals(target)) {
            log.info("{} --- {}", info.word, target);
            log.info("문자변환 완료 ---- {}", info.count);
            this.answer = info.count;
            return;
        }

        for (Word word : graph.get(info.i)) {
            if (!word.visited) {
                word.visited = true;
                for (int i = 0; i < graph.size(); i++) {
                    if (graph.get(i).get(0).equals(word)) {
                        nextSearchInfos.add(new SearchInfo(word.original, i, info.count+1));
                        break;
                    }
                }
            }
        }
    }

    class SearchInfo {
        String word;
        int i;
        int count;
        public SearchInfo(String word, int i, int count) {
            this.word = word;
            this.i = i;
            this.count = count;
        }
    }

    class Word {
        String original;
        String first;
        List<String[]> mids;
        String end;
        Boolean visited;

        public Word(String word) {
            this.original = word;
            this.first = word.substring(1);
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length-1; i++) {
                sb.append(chars[i]);
            }
            this.end = sb.toString();
            sb.setLength(0);
            this.mids = new ArrayList<>();
            for (int i = 1; i < chars.length-1; i++) {
                String[] strings = new String[2];
                for (int j = 0; j < i; j++) {
                    sb.append(chars[j]);
                }
                strings[0] = sb.toString();
                sb.setLength(0);
                for (int j = i+1; j < chars.length; j++) {
                    sb.append(chars[j]);
                }
                strings[1] = sb.toString();
                sb.setLength(0);
                this.mids.add(strings);
            }
            this.visited = false;
        }
    }

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
            printVisited(maps, visited);
            tasks.add(nextTask);
            // printVisited(maps, visited);
        }


        queue.add(new int[] {0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            bfs(coordinate[0], coordinate[1], maps, visited);
        }

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
                    System.out.print('★');
                else
                    System.out.print(maps[i][j] == 0 ? '★' : '☆');
            }
            System.out.println();
        }
    }




}
