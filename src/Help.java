import java.awt.*;
import java.awt.event.*;
// 라이프게임 도움말 버튼을 눌렀을 때 보여주는 프레임입니다.
public class Help extends Frame {
    Image img = null; // 이미지 멤버변수
    final int WIDTH=500;
    final int HEIGHT=400;
    public Help(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // 메모리에서 해제 (현재 프레임만 닫힘)
            }
        });
        setResizable(false);//사이즈 조절불가하게함
        setSize(WIDTH,HEIGHT);
        // 화면 정중앙에 표시 하는 코드
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-WIDTH/2,screenSize.height/2-HEIGHT/2); // 화면 정중앙에 표시한다.

        Toolkit tk2 = Toolkit.getDefaultToolkit();
        img = tk2.getImage("help.png"); // 이미지를 가져온다. 이떄 경로는 src밖의 폴더이다.
        setIconImage(img);

        addMouseListener(new MouseAdapter() { // 화면 클릭하면 그냥 닫음.
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // 화면을 클릭하면 이벤트를 처리하여 닫아서 유저 혼란이 없게끔 하였음.
            }
        });

        setVisible(true);
    }

    // paint()는 자동 호출됨
    // 1. 처음 화면이 나타날 때
    // 2. 다른 화면에 가려졌다 다시 화면 나타날 때
    // 3. 최소화되었다가 다시 화면 나타날 때
    @Override
    public void paint(Graphics g) { // 오버라이딩 함
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