package ex_240428;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class MonthlyCalendar extends JFrame {
    private DefaultTableModel tableModel;
    private JTable calendarTable;
    private JLabel monthYearLabel;

    public MonthlyCalendar() {
        setTitle("Monthly Calendar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // 현재 연도와 월을 가져옵니다.
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        // 이번 달의 첫째 날을 가져옵니다.
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // 달력의 헤더를 생성합니다.
        String[] headers = {"일", "월", "화", "수", "목", "금", "토"};
        tableModel = new DefaultTableModel(null, headers);
        tableModel.addRow(new Object[7]);
        calendarTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(calendarTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 달력 위에 년도와 월을 나타내는 레이블을 추가합니다.
        monthYearLabel = new JLabel();
        updateMonthYearLabel(year, month);
        monthYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(monthYearLabel, BorderLayout.NORTH);

        // 이번 달의 시작 요일을 가져옵니다.
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        // 이번 달의 일수를 가져옵니다.
        YearMonth yearMonth = YearMonth.from(firstDayOfMonth);
        int daysInMonth = yearMonth.lengthOfMonth();

        // 달력의 내용을 채웁니다.
        int day = 1;
        int row = 0;
        int col = startDayOfWeek - 1;
        while (day <= daysInMonth) {
            if (col == 7) {
                col = 0;
                row++;
            }
            if (col == 0) {
                tableModel.addRow(new Object[7]);
            }
            tableModel.setValueAt(day, row, col);
            day++;
            col++;
        }

        // 날짜를 더블 클릭할 때 이벤트 처리
        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int row = calendarTable.getSelectedRow();
                    int column = calendarTable.getSelectedColumn();
                    int day = (int) tableModel.getValueAt(row, column);
                    LocalDate selectedDate = LocalDate.of(year, month, day);
                    // 여기에 선택한 날짜에 대한 일정을 추가하는 다이얼로그를 표시하는 코드를 추가할 수 있습니다.
                    showAddEventDialog(selectedDate);
                }
            }
        });

        setContentPane(panel);
        setVisible(true);
    }

    private void updateMonthYearLabel(int year, int month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월");
        String monthYearText = YearMonth.of(year, month).format(formatter);
        monthYearLabel.setText(monthYearText);
    }

    private void showAddEventDialog(LocalDate selectedDate) {
        JFrame frame = new JFrame("일정 추가");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("일정을 입력하세요:");
        panel.add(label, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("추가");
        addButton.addActionListener(e -> {
            String event = textArea.getText();
            // 여기에 입력된 일정을 처리하는 코드를 추가할 수 있습니다.
            System.out.println("일정 추가: " + event + " ("+ selectedDate + ")");
            frame.dispose();
        });
        panel.add(addButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MonthlyCalendar::new);
    }

        
    }

