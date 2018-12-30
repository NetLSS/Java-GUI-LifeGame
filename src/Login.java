import java.awt.*;
import java.awt.event.*;
public class Login extends Frame{
    Label lid,lpwd;
    TextField tfId,tfPwd;
    Button ok;
    Login(String title){
        super(title); //Frame(String title)ȣ��

        lid = new Label("ID :", Label.RIGHT); // ������ ������ ���ķ�
        lpwd = new Label("Password :", Label.RIGHT);
        // �� 10���� ���ڸ� �Է��� �� �ִ� �ؽ�Ʈ�ʵ� ����
        tfId = new TextField(10);
        tfPwd = new TextField(10);
        tfPwd.setEchoChar('*');

        ok = new Button("OK");
        // OK��ư�� TextField�� �̺�Ʈ ó���� ���� �����ʸ� �߰��Ѵ�.
        tfId.addActionListener(new EventHandler());
        tfPwd.addActionListener(new EventHandler());
        ok.addActionListener(new EventHandler());

        setLayout(new FlowLayout());
        add(lid);
        add(tfId);
        add(lpwd);
        add(tfPwd);
        add(ok);
        setSize(450,65);
        // setLocation(735,500);
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-450/2,screenSize.height/2-65/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true); // ����ȭ
    }

    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_login.png");// �̹����� �����´�.
        g.drawImage(img,0,0,450,65,this);
    }

    // �̺�Ʈ ó����(Ŭ����) ���� Ŭ������ �ۼ��ؾ� Test2 Ŭ���� ���� ��� ���ٰ���!
    class EventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = tfId.getText(); // tfId�� �Էµ� �ؽ�Ʈ�� ��´�.
            String password = tfPwd.getText();
            if(!id.equals("admin")){
                System.out.println("�Է��Ͻ� id�� ��ȿ���� �ʽ��ϴ�. �ٽ� �Է��� �ּ���");
                // id�� �ٽ� �Է��ϵ��� focus�� tfid�� �ű�
                tfId.requestFocus();;
                tfId.selectAll(); // �Էµ� �ؽ�Ʈ�� ��� ���õǰ���
            } else if(!password.equals("admin")){
                System.out.println("�Է��Ͻ� pw�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���.");
                // pw�� �ٽ� �Է��ϰ� ��Ŀ���� �ش�.
                tfPwd.requestFocus();
                tfPwd.selectAll();
            } else{
                System.out.println( id + "��, ���������� �α��� �Ǿ����ϴ�.");
                dispose();
            }
        }
    }
}

