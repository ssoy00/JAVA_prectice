package miniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CatContainer extends JPanel implements MouseListener {
    private JLabel catLabel;
    private JPanel whitePanel;

    public CatContainer() {
        setOpaque(false); // 투명 설정
        setLayout(new BorderLayout());

        // 고양이 이미지 로드
        ImageIcon catIcon = new ImageIcon("src/img/cat.png");
        catLabel = new JLabel(catIcon);
        add(catLabel, BorderLayout.CENTER);

        // 흰색 배경의 패널 초기화
        whitePanel = new JPanel();
        whitePanel.setBackground(new Color(0, 0, 0, 0));
        
        // whitePanel을 CatContainer의 SOUTH에 추가
        add(whitePanel, BorderLayout.SOUTH);
        whitePanel.setVisible(false); // 초기에는 숨겨둠

        // 마우스 리스너 등록
        catLabel.addMouseListener(this);
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
        openCalendarApp();
    }

    // 다른 마우스 이벤트들에 대한 더미 메소드들
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    // 캘린더 앱을 열기 위한 메소드
    private void openCalendarApp() {
        // 캘린더 앱 창을 열도록 하는 코드
        SwingUtilities.invokeLater(() -> {
            new CalendarApp().setVisible(true);
        });
    }
}
