package ch05;

import java.util.Scanner;

public class Q05_L3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int[] scores = null; // 학생 점수 배열 선언
        int numberOfStudents = 0;

        while (run) {
            System.out.println("""
                    ----------------------------------------------------------
                    1. 학생수 | 2.점수 입력 | 3.점수 리스트 | 4. 분석 | 5.종료
                    ----------------------------------------------------------
                    """);

            System.out.print("선택: ");
            String strNum = sc.nextLine();

            if (strNum.equals("1")) {
                System.out.print("학생 수를 입력하세요: ");
                numberOfStudents = sc.nextInt();
                scores = new int[numberOfStudents];
                sc.nextLine(); // 버퍼 비우기
            } else if (strNum.equals("2")) {
                if (scores == null) {
                    System.out.println("먼저 학생 수를 입력하세요.");
                } else {
                    for (int i = 0; i < numberOfStudents; i++) {
                        System.out.print("학생 " + (i + 1) + "의 점수를 입력하세요: ");
                        scores[i] = sc.nextInt();
                    }
                    sc.nextLine(); // 버퍼 비우기
                }
            } else if (strNum.equals("3")) {
                if (scores == null) {
                    System.out.println("먼저 학생 수를 입력하세요.");
                } else {
                    System.out.println("점수 리스트:");
                    for (int i = 0; i < numberOfStudents; i++) {
                        System.out.println("학생 " + (i + 1) + ": " + scores[i]);
                    }
                }
            } else if (strNum.equals("4")) {
                if (scores == null) {
                    System.out.println("먼저 학생 수를 입력하세요.");
                } else {
                    int highestScore = 0;
                    int totalScore = 0;

                    for (int score : scores) {
                        if (score > highestScore) {
                            highestScore = score;
                        }
                        totalScore += score;
                    }

                    double averageScore = (double) totalScore / numberOfStudents;

                    System.out.println("최고 점수: " + highestScore);
                    System.out.println("평균 점수: " + averageScore);
                }
            } else if (strNum.equals("5")) {
                run = false;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            }
        }

        System.out.println("프로그램 종료");
        sc.close();
    }
}