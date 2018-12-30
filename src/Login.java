import java.awt.*;
import java.awt.event.*;
public class Login extends Frame{
    Label lid,lpwd;
    TextField tfId,tfPwd;
    Button ok;
    Login(String title){
        super(title); //Frame(String title)호출

        lid = new Label("ID :", Label.RIGHT); // 정렬을 오른쪽 정렬로
        lpwd = new Label("Password :", Label.RIGHT);
        // 약 10개의 글자를 입력할 수 있는 텍스트필드 생성
        tfId = new TextField(10);
        tfPwd = new TextField(10);
        tfPwd.setEchoChar('*');

        ok = new Button("OK");
        // OK버튼과 TextField에 이벤트 처리를 위한 리스너를 추가한다.
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
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-450/2,screenSize.height/2-65/2); // 화면 정중앙에 표시한다.


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true); // 가시화
    }

    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_login.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,450,65,this);
    }

    // 이벤트 처리기(클래스) 내부 클래스로 작성해야 Test2 클래스 내의 멤버 접근가능!
    class EventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = tfId.getText(); // tfId에 입력된 텍스트를 얻는다.
            String password = tfPwd.getText();
            if(!id.equals("admin")){
                System.out.println("입력하신 id는 유효하지 않습니다. 다시 입력해 주세요");
                // id를 다시 입력하도록 focus를 tfid로 옮김
                tfId.requestFocus();;
                tfId.selectAll(); // 입력된 텍스트가 모두 선택되게함
            } else if(!password.equals("admin")){
                System.out.println("입력하신 pw가 틀렸습니다. 다시 입력해 주세요.");
                // pw를 다시 입력하게 포커스를 준다.
                tfPwd.requestFocus();
                tfPwd.selectAll();
            } else{
                System.out.println( id + "님, 성공적으로 로그인 되었습니다.");
                dispose();
            }
        }
    }
}

