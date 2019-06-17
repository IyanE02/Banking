package com.Iyane.banking;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

public class MgmtAccount {
    private static Account[] accounts = new Account[1000];
    private static int index = 0;

    //단순히 80줄 넘겨 cls의 역할 대체
    public void cls() {
        for (int i = 0; i < 80; i++) {
            System.out.println();
        }
    }

    //계좌 확인
    public static int findAccount(String accountNumber) {
        for (int i = 0; i < index; i++) {   //저장된 계좌들을 전부 조회함
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return i; //해당 계좌의 인덱스를 리턴함
            }
        }
        return -1; //해당 계좌를 찾지 못할경우 -1을 리턴함
    }

    //num자리수의 랜덤 계좌번호 생성
    public String randomString(int num) {
        String referenceChars = "0123456789";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < num; i++) {
            stringBuilder.append(referenceChars.indexOf((int) (Math.random() % referenceChars.length())));
        }

        return stringBuilder.toString();
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

        accounts[index] = new Account(accountNumber, password, name, birth, balance); //모든 정보들을 저장함
        index++; //다음 생성을 위해 인덱스를 늘려줌

        System.out.println("성공적으로 계좌가 만들어졌습니다.");
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

        int index = findAccount(accountNumber); //계좌의 인덱스 탐색

        if (index == -1) { //리턴받은 인덱스가 오류인 -1일 경우 정확하게 입력하라는 문구 출력
            System.out.println("정확한 계좌번호를 입력하여 주십시오.");
            scan.nextLine();
            mgmt.cls();
            return;
        } else {
            System.out.printf("잔액 : %ld\n", accounts[index].getBalance());
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

            accounts[index].deposit(amount); //입금 실행
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

            if (accounts[index].getPassword().equals(password)) {
                System.out.println("출금액을 입력하십시오.");
                System.out.println();
                System.out.printf("출금액 : ");
                long amount = scan.nextLong();
                System.out.println();

                accounts[index].withdraw(amount); //출금 실행
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

    //모든 고객정보 출력
    public static void accountInfos() {
        for (int i = 0; i < index; i++) {
            System.out.println(i + "번 고객 정보");
            accounts[i].getInfo();
        }
    }

    public static void main(String[] args) {
        Account[] objects = new Account[100];
        MgmtAccount mgmt = new MgmtAccount();
        Scanner scan = new Scanner(System.in);

        boolean isExit = false;

        while(true){
            System.out.println("====== 로컬 은행 관리 시스템 ======");
            System.out.println("1. 계좌 개설");
            System.out.println("2. 잔액 조회");
            System.out.println("3. 입금");
            System.out.println("4. 출금");
            System.out.println("5. 모든 고객정보 출력");
            System.out.println("6. 종료");
            System.out.println();
            System.out.println("원하시는 항목을 선택하시고 Enter를 누르십시오.");

            int menu = scan.nextInt();
            switch (menu){
                case 1:
                    mgmt.cls();
                    System.out.println("계좌 개설 메뉴로 진입합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.createAccount();
                    break;
                case 2:
                    mgmt.cls();
                    System.out.println("잔액 조회 메뉴로 진입합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.displayBalance();
                    break;
                case 3:
                    mgmt.cls();
                    System.out.println("입금 메뉴로 진입합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.inputMoney();
                    break;
                case 4:
                    mgmt.cls();
                    System.out.println("출금 메뉴로 진입합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    mgmt.outputMoney();
                    break;
                case 5:
                    mgmt.cls();
                    System.out.println("관리자 암호를 입력하여 주십시오.");
                    String admin = scan.nextLine();
                    admin = scan.nextLine();
                    String admins = "qwerty1234";
                    if(admin.equals(admins)){
                        mgmt.cls();
                        mgmt.accountInfos();
                    }
                    else {
                        System.out.println();
                        System.out.println("권한이 없습니다.");
                        scan.nextLine();
                        mgmt.cls();
                        return;
                    }
                case 6:
                    mgmt.cls();
                    System.out.println("종료합니다.");
                    scan.nextLine();
                    mgmt.cls();
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    scan.nextLine();
                    mgmt.cls();
            }
        }
    }
}
