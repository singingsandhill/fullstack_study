package FrameWork;

import java.util.Scanner;

public class V0 {
    public static void main(String[] args) {
//        필드 선언
        boolean run = true; // 실행 상태 결정
        int studentNum = 0; // 학생 수
        int[] scores = null; // 학생들의 점수, 배열로 관리

//        입력 메서드 선언
        Scanner sc = new Scanner(System.in);

//        반복문 실행
        while (run) {
//            반복해서 뜰 초기 화면
            System.out.println("--------------------------------------------------------------");
            System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
            System.out.println("--------------------------------------------------------------");
            System.out.print("선택> ");

//            입력 받기
            int selectNo = Integer.parseInt(sc.nextLine());

//            입력값이 1인 경우 -> 학생 수 입력
            if (selectNo == 1) {
                System.out.print("학생수 >");
                studentNum = Integer.parseInt(sc.nextLine());
//                학생 수 만큼의 길이를 가진 배열 생성
                scores = new int[studentNum];

//                입력값이 2인 경우 -> 점수 입력
            } else if (selectNo == 2) {
                for (int i = 0; i < scores.length; i++) {
                    System.out.print("scores[" + i + " ] >");
                    scores[i] = Integer.parseInt(sc.nextLine());
                }
//              입력값이 3인 경우 -> 점수 리스트 출력
            } else if (selectNo == 3) {
                for (int i = 0; i < scores.length; i++) {
                    System.out.println("scores[" + i + "]: " + scores[i]);
                }
//                입력값이 4인 경우 -> 수치 분석
            } else if (selectNo == 4) {
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

//                입력값이 5인 경우 종료
            } else if (selectNo == 5) {
                run = false;
            }
        }
        System.out.println("프로그램 종료");
    }
}