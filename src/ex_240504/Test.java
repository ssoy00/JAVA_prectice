package ex_240504;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {

    public Test() {
        setTitle("Transparent Image Frame");
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        Container container = getContentPane();

        ImageIcon catIcon = new ImageIcon("./img/cat.png");
        ImageIcon blackCatIcon = new ImageIcon("./img/blackcat.png");
        ImageIcon grayCatIcon = new ImageIcon("./img/graycat.png");

        JLabel catLabel = new JLabel(catIcon, SwingConstants.LEFT);
        JLabel blackCatLabel = new JLabel(blackCatIcon, SwingConstants.CENTER);
        JLabel grayCatLabel = new JLabel(grayCatIcon, SwingConstants.RIGHT);

        // 첫 번째 사진에 대한 이벤트 핸들러
        catLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("첫 번째 사진을 클릭했습니다.");
            }
        });

        // 두 번째 사진에 대한 이벤트 핸들러
        blackCatLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("두 번째 사진을 클릭했습니다.");
            }
        });

        // 세 번째 사진에 대한 이벤트 핸들러
        grayCatLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("세 번째 사진을 클릭했습니다.");
            }
        });

        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 고정된 위치에 이미지 라벨 추가
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(catLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(blackCatLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        container.add(grayCatLabel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
