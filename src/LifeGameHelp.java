import java.awt.*;
import java.awt.event.*;
public class LifeGameHelp extends Frame {
    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("LifeGameHelp.png");// �̹����� �����´�.
        g.drawImage(img,0,0,1050,600,this);
    }
    public LifeGameHelp(){
        super("���������� ����");
        // ȭ�� ���߾ӿ� ǥ�� �ϴ� �ڵ�
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-1050/2,screenSize.height/2-600/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.
        setSize(1050,600);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
//
//    public static void main(String[] args){
//        new LifeGameHelp();
//    }
}
