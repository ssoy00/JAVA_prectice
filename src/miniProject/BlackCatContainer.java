package miniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackCatContainer extends JPanel implements MouseListener {
	
	private JLabel catLabel2;
    private JLabel textLabel2;
    private JPanel whitePanel2;

    public BlackCatContainer() {
        setOpaque(false); // 투명 설정
        setLayout(new BorderLayout());

        ImageIcon blackCatIcon = new ImageIcon("src/img/blackcat.png");
        JLabel blackCatLabel = new JLabel(blackCatIcon);
        add(blackCatLabel, BorderLayout.CENTER);
        
        // 텍스트 라벨 초기화
        JLabel textLabel2 = new JLabel("");
        textLabel2.setForeground(new Color(0, 0, 0, 0));
        textLabel2.setFont(new Font("Arial", Font.BOLD, 20));

        // 흰색 배경의 패널 초기화
        whitePanel2 = new JPanel();
        whitePanel2.setBackground(new Color(0, 0, 0, 0));
        whitePanel2.add(textLabel2);
        
        
        // whitePanel을 CatContainer의 SOUTH에 추가
        add(whitePanel2, BorderLayout.SOUTH);
        whitePanel2.setVisible(false); // 초기에는 숨겨둠

        // 마우스 리스너 등록
        blackCatLabel.addMouseListener(this);
    }

    // 마우스가 컴포넌트 위에 올라갔을 때의 이벤트 처리
    public void mouseEntered(MouseEvent e) {
        whitePanel2.setVisible(true); // 텍스트 라벨 보이기
    }

    // 마우스가 컴포넌트에서 나갔을 때의 이벤트 처리
    public void mouseExited(MouseEvent e) {
        whitePanel2.setVisible(false); // 텍스트 라벨 숨기기
    }

    // 다른 마우스 이벤트들에 대한 더미 메소드들
    public void mouseClicked(MouseEvent e) {
        System.exit(0); // 프로그램 종료
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
