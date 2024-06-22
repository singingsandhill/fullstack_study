package FrameWork;

import java.util.Scanner;

public class V1 {
    //        필드 선언
    boolean run = true; // 실행 상태 결정
    int studentNum = 0; // 학생 수
    int[] scores = null; // 학생들의 점수, 배열로 관리

    //        입력 메서드 선언
    Scanner sc = new Scanner(System.in);

    public void printMenu() { //반복해서 뜰 초기 화면
        System.out.println("--------------------------------------------------------------");
        System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
        System.out.println("--------------------------------------------------------------");
    }

    public int getSelect() { //입력 받기
        System.out.print("선택> ");
        return Integer.parseInt(sc.nextLine());
    }

    public void getStudentNum() { // 학생 수 입력
        System.out.print("학생수 >");
        studentNum = Integer.parseInt(sc.nextLine());
        scores = new int[studentNum];//학생 수 만큼의 길이를 가진 배열 생성
    }

    public void getScores() { // 점수 입력
        for (int i = 0; i < scores.length; i++) {
            System.out.print("scores[" + i + " ] >");
            scores[i] = Integer.parseInt(sc.nextLine());
        }
    }

    public void printScore() { // 점수 리스트 출력
        for (int i = 0; i < scores.length; i++) {
            System.out.println("scores[" + i + "]: " + scores[i]);
        }
    }

    private void analyze() { //수치 분석
        int max = 0;
        int sum = 0;
        double avg = 0;

        for (int i = 0; i < scores.length; i++) {
            max = (max < scores[i]) ? scores[i] : max;
            sum += scores[i];
        }
        avg = (double) sum / studentNum;
        System.out.println("최고 점수: " + max);
        System.out.println("평균 점수: " + avg);
    }

    public void exit() {
        run = false;
    }

    public void executeCommand(int selectNo) {
        if (selectNo == 1) {
            getStudentNum();
        } else if (selectNo == 2) {
            getScores();
        } else if (selectNo == 3) {
            printScore();
        } else if (selectNo == 4) {
            analyze();
        } else if (selectNo == 5) {
            exit();
        }
    }
    public void run() {
        while(run) {
            printMenu();
            int selectNo = getSelect();
            executeCommand(selectNo);
        }
        System.out.println("프로그램 종료");
    }
    public static void main(String[] args) {
        V1 app = new V1();
        app.run();
    }
}
