package ex_240504;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CalendarApp extends JFrame {
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JButton prevButton, nextButton;
    private JTextField eventField;
    private JButton addEventButton;
    private JTextArea eventTextArea;

    private LocalDate currentDate;

    private final String[] DAYS_OF_WEEK = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public CalendarApp() {
        setTitle("Calendar App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 현재 날짜로 초기화
        currentDate = LocalDate.now();

        // 달력 패널 생성
        calendarPanel = new JPanel(new GridLayout(0, 7));
        addDayLabels();

        // 월 라벨 생성
        monthLabel = new JLabel("", JLabel.CENTER);
        updateMonthLabel();

        // 이전, 다음 버튼 생성
        prevButton = new JButton("<<");
        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateMonthLabel();
            updateCalendar();
        });

        nextButton = new JButton(">>");
        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateMonthLabel();
            updateCalendar();
        });

        // 일정 추가 입력 필드와 버튼 생성
        eventField = new JTextField(20);
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            String event = eventField.getText();
            eventField.setText("");
            if (!event.isEmpty()) {
                addEvent(event);
            }
        });

        // 일정 표시를 위한 텍스트 영역 생성
        eventTextArea = new JTextArea(10, 30);
        eventTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventTextArea);

        // 패널에 컴포넌트 추가
        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.add(prevButton);
        controlsPanel.add(monthLabel);
        controlsPanel.add(nextButton);

        JPanel eventPanel = new JPanel(new FlowLayout());
        eventPanel.add(eventField);
        eventPanel.add(addEventButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(controlsPanel, BorderLayout.NORTH);
        mainPanel.add(calendarPanel, BorderLayout.CENTER);
        mainPanel.add(eventPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        updateCalendar();
    }

    private void updateMonthLabel() {
        String month = currentDate.getMonth().toString();
        int year = currentDate.getYear();
        monthLabel.setText(month + " " + year);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        calendarPanel.revalidate();
        calendarPanel.repaint();

        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        // 이전 달의 나머지 날짜 추가
        for (int i = 0; i < startDayOfWeek; i++) {
            calendarPanel.add(new JLabel());
        }

        // 현재 달의 날짜 추가
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), i);
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(e -> showEvents(date));
            calendarPanel.add(button);
        }

        pack();
    }

    private void showEvents(LocalDate date) {
        String events = getEvents(date);
        eventTextArea.setText(events);
    }

    private void addEvent(String event) {
        LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        String key = date.toString();
        String currentEvents = getEvents(date);
        if (!currentEvents.isEmpty()) {
            currentEvents += "\n";
        }
        currentEvents += event;
        eventTextArea.setText(currentEvents);
    }

    private String getEvents(LocalDate date) {
        // 임의의 로직으로 일정을 가져오는 것으로 가정
        // 여기서는 단순히 년-월-일 형식의 문자열로 반환
        return date.toString();
    }

    private void addDayLabels() {
        for (String day : DAYS_OF_WEEK) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            calendarPanel.add(label);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalendarApp().setVisible(true);
        });
    }
    private void addEvent(String event) {
        LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        String key = date.toString();
        String currentEvents = getEvents(date);
        if (!currentEvents.isEmpty()) {
            currentEvents += "\n";
        }
        currentEvents += event;
        eventTextArea.setText(currentEvents);
        
        // 콘솔에 일정 등록 메시지 출력
        System.out.println("일정이 등록되었습니다: " + event);
    }
}
