import java.awt.*;
import java.awt.event.*;
// ���������� ���� ��ư�� ������ �� �����ִ� �������Դϴ�.
public class Help extends Frame {
    Image img = null; // �̹��� �������
    final int WIDTH=500;
    final int HEIGHT=400;
    public Help(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // �޸𸮿��� ���� (���� �����Ӹ� ����)
            }
        });
        setResizable(false);//������ �����Ұ��ϰ���
        setSize(WIDTH,HEIGHT);
        // ȭ�� ���߾ӿ� ǥ�� �ϴ� �ڵ�
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-WIDTH/2,screenSize.height/2-HEIGHT/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.

        Toolkit tk2 = Toolkit.getDefaultToolkit();
        img = tk2.getImage("help.png"); // �̹����� �����´�. �̋� ��δ� src���� �����̴�.
        setIconImage(img);

        addMouseListener(new MouseAdapter() { // ȭ�� Ŭ���ϸ� �׳� ����.
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // ȭ���� Ŭ���ϸ� �̺�Ʈ�� ó���Ͽ� �ݾƼ� ���� ȥ���� ���Բ� �Ͽ���.
            }
        });

        setVisible(true);
    }

    // paint()�� �ڵ� ȣ���
    // 1. ó�� ȭ���� ��Ÿ�� ��
    // 2. �ٸ� ȭ�鿡 �������� �ٽ� ȭ�� ��Ÿ�� ��
    // 3. �ּ�ȭ�Ǿ��ٰ� �ٽ� ȭ�� ��Ÿ�� ��
    @Override
    public void paint(Graphics g) { // �������̵� ��
        //super.paint(g);
        if(img==null)
            return;
        int imgWidth = img.getWidth(this);
        int imgHeiht = img.getHeight(this);
        g.drawImage(img,0,0,this);
    }

    public Help(){
        this("Help");
    }
//    public static void main(String[] args){
//        new Help();
//    }

}