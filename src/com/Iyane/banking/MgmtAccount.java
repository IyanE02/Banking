package com.Iyane.banking;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MgmtAccount {
    private static ArrayList<Account> accounts = new ArrayList<>();

    //단순히 80줄 넘겨 cls의 역할 대체
    public void cls() {
        for (int i = 0; i < 80; i++) {
            System.out.println();
        }
    }

    //계좌
    public static int findAccount(String accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {   //저장된 계좌들을 전부 조회함
            if (accounts.get(i).getAccountNumber().equals(accountNumber)) {
                return i; //해당 계좌의 인덱스를 리턴함
            }
        }
        return -1; //해당 계좌를 찾지 못할경우 -1을 리턴함
    }

    //num자리수의 랜덤 계좌번호 생성
    public String randomString(int num) {
        Random random = new Random();
        char referenceChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            sb.append(referenceChars[random.nextInt(10)]);
        }

        return sb.toString();
    }

    //계좌 생성
    public static void createAccount() {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        String accountNumber = mgmt.randomString(14);   //랜덤 계좌번호 생성 메소드 호출
        System.out.println("당신의 계좌번호 : " + accountNumber + "\n");  //배정된 계좌번호 출력

        System.out.printf("당신의 이름\t: ");
        String name = scan.nextLine();  //이름의 입력을 받음
        System.out.println("\n");

        System.out.printf("당신의 생년월일 : ");
        String birth = scan.nextLine(); //생년월일의 입력을 받음
        System.out.println("\n");

        System.out.printf("계좌의 비밀번호 : ");
        String password = scan.nextLine();  //계좌의 비밀번호를 입력받음
        System.out.println("\n");

        System.out.printf("초기 입금금액\t: ");
        long balance = scan.nextLong(); //계좌를 생성하며 입금할 금액을 입력받음
        System.out.println("\n");

        accounts.add(new Account(accountNumber, password, name, birth, balance));

        System.out.println("성공적으로 계좌가 만들어졌습니다.");
        scan.nextLine();
        scan.nextLine();
        mgmt.cls();
    }

    //잔액 조회
    public static void displayBalance() {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        System.out.println("계좌번호를 입력하십시오.");
        System.out.println();
        System.out.printf("계좌번호 : ");
        String accountNumber = scan.nextLine();
        System.out.println();

        int index = findAccount(accountNumber); //계좌의 인덱스 탐색

        if (index == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
            System.out.println("정확한 계좌번호를 입력하여 주십시오.");
            scan.nextLine();
            mgmt.cls();
            return;
        } else {
            System.out.println("이름 : " + accounts.get(index).getName());
            System.out.printf("잔액 : %,d원\n", accounts.get(index).getBalance());
            scan.nextLine();
            mgmt.cls();
            return;
        }
    }

    //입금
    public static void inputMoney() {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        System.out.println("계좌번호를 입력하십시오.");
        System.out.println();
        System.out.printf("계좌번호 : ");
        String accountNumber = scan.nextLine();
        System.out.println();

        int index = findAccount(accountNumber); //계좌의 인덱스 탐색

        if (index == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
            System.out.println("정확한 계좌번호를 입력하여 주십시오.");
            scan.nextLine();
            mgmt.cls();
            return;
        } else {
            System.out.println("입금액을 입력하십시오.");
            System.out.println();
            System.out.printf("입금액 : ");
            long amount = scan.nextLong();
            System.out.println();

            accounts.get(index).deposit(amount); //입금 실행
            scan.nextLine();
            mgmt.cls();
            return;
        }
    }

    //출금
    public static void outputMoney() {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        System.out.println("계좌번호를 입력하십시오.");
        System.out.println();
        System.out.printf("계좌번호 : ");
        String accountNumber = scan.nextLine();
        System.out.println();

        int index = findAccount(accountNumber); //계좌의 인덱스 탐색

        if (index == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
            System.out.println("정확한 계좌번호를 입력하여 주십시오.");
            scan.nextLine();
            mgmt.cls();
            return;
        } else {
            System.out.println("비밀번호를 입력하십시오.");
            System.out.println();
            System.out.printf("비밀번호 : ");
            String password = scan.nextLine();
            System.out.println();

            if (accounts.get(index).getPassword().equals(password)) {
                System.out.println("출금액을 입력하십시오.");
                System.out.println();
                System.out.printf("출금액 : ");
                long amount = scan.nextLong();
                System.out.println();

                accounts.get(index).withdraw(amount); //출금 실행
                scan.nextLine();
                mgmt.cls();
                return;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                scan.nextLine();
                mgmt.cls();
                return;
            }
        }
    }

    //송금
    public static void sendMoney() {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        System.out.println("당신의 계좌번호를 입력하십시오.");
        System.out.println();
        System.out.printf("계좌번호 : ");
        String myAccountNumber = scan.nextLine();
        System.out.println();

        int myIndex = findAccount(myAccountNumber); //계좌의 인덱스 탐색

        if (myIndex == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
            System.out.println("정확한 계좌번호를 입력하여 주십시오.");
            scan.nextLine();
            mgmt.cls();
            return;
        } else {
            System.out.println("비밀번호를 입력하십시오.");
            System.out.println();
            System.out.printf("비밀번호 : ");
            String password = scan.nextLine();
            System.out.println();

            if (accounts.get(myIndex).getPassword().equals(password)) {
                System.out.println("받는 사람의 계좌번호를 입력하십시오.");
                System.out.println();
                System.out.printf("계좌번호 : ");
                String yourAccountNumber = scan.nextLine();
                System.out.println();

                int yourIndex = findAccount((yourAccountNumber));

                if (yourIndex == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
                    System.out.println("정확한 계좌번호를 입력하여 주십시오.");
                    scan.nextLine();
                    mgmt.cls();
                    return;
                } else {
                    mgmt.cls();
                    System.out.println(accounts.get(yourIndex).getName() + "님이 맞으십니까?");
                    System.out.println("\n" + "Y or N");

                    String isItRight = scan.nextLine();
                    mgmt.cls();

                    if (isItRight.equals("Y") || isItRight.equals("y")) {
                        System.out.println("송금액을 입력하십시오.");
                        System.out.println();
                        System.out.printf("송금액 : ");
                        long amount = scan.nextLong();
                        System.out.println();

                        accounts.get(myIndex).withdrawToSend(amount); //내 계좌에서 출금 실행
                        accounts.get(yourIndex).depositToSend(amount); //상대방 계좌에 입금 실행
                        scan.nextLine();
                        mgmt.cls();
                        return;
                    } else if (isItRight.equals("N") || isItRight.equals("n")) {
                        System.out.println("송금을 취소합니다.");
                        scan.nextLine();
                        return;
                    } else {
                        System.out.println("잘못 입혁하여 송금을 취소합니다.");
                        scan.nextLine();
                        return;
                    }
                }
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                scan.nextLine();
                mgmt.cls();
                return;
            }
        }
    }

    //모든 고객정보 출력
    public static void accountInfos() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + 1 + "번 고객 정보");
            accounts.get(i).getInfo();
            scan.nextLine();
        }
    }

    public static void main(String[] args) {
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        String adminPassword = "admin";

        while (true) {
            System.out.println("====== 로컬 은행 관리 시스템 ======");
            System.out.println("1. 계좌 개설");
            System.out.println("2. 잔액 조회");
            System.out.println("3. 입금");
            System.out.println("4. 출금");
            System.out.println("5. 송금");
            System.out.println("6. 종료");
            System.out.println();
            System.out.println("원하시는 항목을 선택하시고 Enter를 누르십시오.");

            int menu = scan.nextInt();
            switch (menu) {
                case 1:
                    mgmt.cls();
                    System.out.println("계좌 개설 메뉴로 진입합니다.");
                    scan.nextLine();
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.createAccount();
                    break;
                case 2:
                    mgmt.cls();
                    System.out.println("잔액 조회 메뉴로 진입합니다.");
                    scan.nextLine();
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.displayBalance();
                    break;
                case 3:
                    mgmt.cls();
                    System.out.println("입금 메뉴로 진입합니다.");
                    scan.nextLine();
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.inputMoney();
                    break;
                case 4:
                    mgmt.cls();
                    System.out.println("출금 메뉴로 진입합니다.");
                    scan.nextLine();
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.outputMoney();
                    break;
                case 5:
                    mgmt.cls();
                    System.out.println("송금 메뉴로 진입합니다.");
                    scan.nextLine();
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.sendMoney();
                    break;
                case 6:
                    mgmt.cls();
                    System.out.println("종료합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    return;
                case 2627:
                    mgmt.cls();
                    System.out.println("관리자 암호를 입력하여 주십시오.");
                    String admin = scan.nextLine();
                    admin = scan.nextLine();
                    if (admin.equals(adminPassword)) {
                        mgmt.cls();
                        mgmt.accountInfos();
                        break;
                    } else {
                        System.out.println();
                        System.out.println("권한이 없습니다.");
                        scan.nextLine();
                        mgmt.cls();
                        break;
                    }
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    scan.nextLine();
                    mgmt.cls();
            }
        }
    }
}
