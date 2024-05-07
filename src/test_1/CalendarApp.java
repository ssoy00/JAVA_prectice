package test_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarApp {
    private static LocalDate currentDate;
    private static JPanel datesPanel;

   
    public CalendarApp() {
        JFrame frame = new JFrame("Simple Calendar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false); // 프레임 크기 고정

        // 현재 날짜 가져오기
        currentDate = LocalDate.now();

        // 캘린더 헤더 추가 (월과 연도)
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel(currentDate.format(DateTimeFormatter.ofPattern("MM yyyy")));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 큰 폰트 적용

        // 이전 월 버튼 추가
        JButton prevMonthButton = new JButton("◀");
        prevMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusMonths(1);
                updateCalendar();
                headerLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MM yyyy"))); // 헤더 레이블 업데이트
            }
        });

        // 다음 월 버튼 추가
        JButton nextMonthButton = new JButton("▶");
        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusMonths(1);
                updateCalendar();
                headerLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MM yyyy"))); // 헤더 레이블 업데이트
            }
        });

        headerPanel.add(prevMonthButton, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(nextMonthButton, BorderLayout.EAST);

        // 요일 레이블 추가
        JPanel daysPanel = new JPanel(new GridLayout(1, 7));
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 18)); // 큰 폰트 적용
            daysPanel.add(dayLabel);
        }

        // 날짜 추가
        datesPanel = new JPanel(new GridLayout(0, 7));
        updateCalendar();

        // 스크롤 가능한 패널로 날짜 패널을 감싸기
        JScrollPane scrollPane = new JScrollPane(datesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 수직 스크롤바 비활성화
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 수평 스크롤바 비활성화
        scrollPane.setPreferredSize(new Dimension(350, 200)); // 날짜 패널 크기 고정

        // 프레임에 컴포넌트 추가
        frame.setLayout(new BorderLayout());
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(daysPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH); // 스크롤 패널 추가

        frame.pack();
        frame.setLocationRelativeTo(null); // 화면 중앙에 프레임 표시
        frame.setVisible(true);
    }

    private static void updateCalendar() {
        datesPanel.removeAll();

        int firstDayOfMonth = currentDate.withDayOfMonth(1).getDayOfWeek().getValue();
        int daysInMonth = currentDate.lengthOfMonth();
        int dayOfMonth = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JLabel dateLabel = new JLabel("", JLabel.CENTER);
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // 기본 폰트 적용
                if (i == 0 && j < firstDayOfMonth) {
                    // 첫 주의 시작 날짜 이전은 빈 레이블
                    datesPanel.add(dateLabel);
                } else if (dayOfMonth <= daysInMonth) {
                    // 현재 월의 날짜 표시
                    dateLabel.setText(String.valueOf(dayOfMonth));
                    datesPanel.add(dateLabel);
                    dayOfMonth++;
                }
            }
        }

        datesPanel.revalidate();
        datesPanel.repaint();
    }


    public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new CalendarApp(); });
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
