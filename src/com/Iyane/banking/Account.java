//은행 계좌 관리 프로그램

package com.Iyane.banking;

import java.util.Scanner;

public class Account {
    private String accountNumber;
    private String password;
    private String name;
    private String birth;
    private long balance;

    //생성자
    public Account() {

    }

    //생성자
    public Account(String accountNumber, String password, String name, String birth, long balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.balance = balance;
    }

    //계좌번호 조회
    public String getAccountNumber() {
        return accountNumber;
    }

    //계좌번호 발급
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //비밀번호 조회
    public String getPassword() {
        return password;
    }

    //비밀번호 변경
    public void setPassword(String password) {
        this.password = password;
    }

    //이름 조회
    public String getName() {
        return name;
    }

    //이름 설정
    public void setName(String name) {
        this.name = name;
    }

    //생년월일 조회
    public String getBirth() {
        return birth;
    }

    //생년월일 설정
    public void setBirth(String birth) {
        this.birth = birth;
    }

    //잔액 확인
    public long getBalance() {
        return balance;
    }

    //잔액 설정
    public void setBalance(long balance) {
        this.balance = balance;
    }

    //입금하기
    public void deposit(long depositMoney) {
        Scanner scan = new Scanner(System.in);
        if (depositMoney <= 0) {
            System.out.println("정확한 금액을 입력하십시오.");
            scan.nextLine();
            return;
        }
        System.out.println("정상적으로 입금되었습니다.");
        setBalance(getBalance() + depositMoney);
        System.out.printf("입금액 : %,d원\n", depositMoney);
        System.out.printf("잔액 : %,d원\n", getBalance());
        scan.nextLine();
    }

    //출금하기
    public void withdraw(long withdrawMoney) {
        Scanner scan = new Scanner(System.in);
        if (withdrawMoney >= balance) {
            System.out.println("잔액이 부족합니다.");
            scan.nextLine();
            return;
        } else if (withdrawMoney <= 0) {
            System.out.println("정확한 금액을 입력하십시오.");
            scan.nextLine();
            return;
        }
        System.out.println("정상적으로 출금되었습니다.");
        setBalance(getBalance() - withdrawMoney);
        System.out.printf("출금액 : %,d원\n", withdrawMoney);
        System.out.printf("잔액 : %,d원\n", getBalance());
        scan.nextLine();
    }

    //송금용 입금하기
    public void depositToSend(long depositMoney) {
        Scanner scan = new Scanner(System.in);
        if (depositMoney <= 0) {
            return;
        }
        setBalance(getBalance() + depositMoney);
    }

    //송금용 출금하기
    public void withdrawToSend(long withdrawMoney) {
        Scanner scan = new Scanner(System.in);
        if (withdrawMoney >= balance) {
            System.out.println("잔액이 부족합니다.");
            scan.nextLine();
            return;
        } else if (withdrawMoney <= 0) {
            System.out.println("정확한 금액을 입력하십시오.");
            scan.nextLine();
            return;
        }
        System.out.println("정상적으로 송금되었습니다.");
        setBalance(getBalance() - withdrawMoney);
        System.out.printf("송금액 : %,d원\n", withdrawMoney);
        System.out.printf("잔액 : %,d원\n", getBalance());
        scan.nextLine();
    }

    //계좌정보 조회
    public void getInfo() {
        System.out.println("계좌번호 : " + getAccountNumber());
        System.out.println("비밀번호 : " + getPassword());
        System.out.println("예금주명 : " + getName());
        System.out.println("생년월일 : " + getBirth());
        System.out.printf("계좌잔고 : %,d원\n", getBalance());
        System.out.println();
        return;
    }
}
