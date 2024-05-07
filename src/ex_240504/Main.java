package ex_240504;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("게임을 시작합니다. 'a' 또는 'd'를 입력하여 캐릭터를 움직여 보세요.");

        // 캐릭터와 장애물 생성
        Character character = new Character();
        Obstacle obstacle = new Obstacle();

        while (true) {
            System.out.println("캐릭터 위치: " + character.getPosition());
            System.out.print("입력: ");
            char input = scanner.next().charAt(0);

            // 캐릭터 이동
            if (input == 'a') {
                character.moveLeft();
            } else if (input == 'd') {
                character.moveRight();
            }

            // 충돌 체크
            if (character.collidesWith(obstacle)) {
                System.out.println("게임 오버! 장애물에 닿았습니다.");
                break;
            }
        }

        scanner.close();
    }
}

class Character {
    private int position;

    public Character() {
        this.position = 0;
    }

    public void moveLeft() {
        this.position--;
    }

    public void moveRight() {
        this.position++;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean collidesWith(Obstacle obstacle) {
        return this.position == obstacle.getPosition();
    }
}

class Obstacle {
    private int position;

    public Obstacle() {
        // 장애물 초기 위치 설정
        this.position = 5;
    }

    public int getPosition() {
        return this.position;
    }
}

