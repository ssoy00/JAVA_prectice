package test_1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

    public Main() {

        String name = JOptionPane.showInputDialog("좋아하는 동물을 입력하세요."); 
    	
        setTitle("Transparent Image Frame");
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new GridLayout(1, 3)); // 가로로 3개의 컨테이너 배치
        
        
       
        // 각 컨테이너를 생성하여 메인 프레임에 추가
        CatContainer catContainer = new CatContainer();
        container.add(catContainer);
       
        GrayCatContainer grayCatContainer = new GrayCatContainer();
        container.add(grayCatContainer);
        
        BlackCatContainer blackCatContainer = new BlackCatContainer();
        container.add(blackCatContainer);

        setVisible(true);
    }

    public static void main(String[] args) {
    	
        new Main();
    }
}
