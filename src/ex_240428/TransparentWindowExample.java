package ex_240428;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TransparentWindowExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 화면으로 설정
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        // 창 투명하게 설정
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));

        // 캐릭터 그리기
        frame.add(new CharacterComponent());

        frame.setVisible(true);
    }
}

class CharacterComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        // 여기에 캐릭터를 그리는 코드를 작성하세요.
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100); // 캐릭터를 사각형으로 대체
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200); // 적절한 크기로 변경
    }
}
