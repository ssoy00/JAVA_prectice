package miniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GrayCatContainer extends JPanel implements MouseListener {

    private JLabel grayCatLabel;
    private JPanel whitePanel;

    public GrayCatContainer() {
        setOpaque(false); // 투명 설정
        setLayout(new BorderLayout());

        // 회색 고양이 이미지 로드
        ImageIcon grayCatIcon = new ImageIcon("src/img/graycat.png");
        grayCatLabel = new JLabel(grayCatIcon);
        add(grayCatLabel, BorderLayout.CENTER);

        // 흰색 배경의 패널 초기화
        whitePanel = new JPanel();
        whitePanel.setBackground(new Color(0, 0, 0, 0));
        
        // whitePanel을 GrayCatContainer의 SOUTH에 추가
        add(whitePanel, BorderLayout.SOUTH);
        whitePanel.setVisible(false); // 초기에는 숨겨둠

        // 마우스 리스너 등록
        grayCatLabel.addMouseListener(this);
    }

    // 마우스가 컴포넌트 위에 올라갔을 때의 이벤트 처리
    public void mouseEntered(MouseEvent e) {
        whitePanel.setVisible(true); // 텍스트 라벨 보이기
    }

    // 마우스가 컴포넌트에서 나갔을 때의 이벤트 처리
    public void mouseExited(MouseEvent e) {
        whitePanel.setVisible(false); // 텍스트 라벨 숨기기
    }

    // 마우스가 클릭되었을 때의 이벤트 처리
    public void mouseClicked(MouseEvent e) {
        openWeeklySchedulerApp(); // 위클리 스케쥴러 앱 열기
    }

    // 다른 마우스 이벤트들에 대한 더미 메소드들
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    // 위클리 스케쥴러 앱을 열기 위한 메소드
    private void openWeeklySchedulerApp() {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true); // 위클리 스케쥴러 앱 실행
        });
    }
}
