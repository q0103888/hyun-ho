package m5d27;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookListViewer extends JFrame implements ActionListener {
   private JTextField idField, titleField, publisherField, yearField, priceField;
   private JButton previousBtn, nextBtn, insertBtn, finishBtn;
   private ResultSet rs;
   private Connection con;
//여기서 값을 입력받아 DB에 저장을 함
//간단한 책 정보를 입력하는 코드

   public BookListViewer() throws Exception {
      // 객체가 생성될 때 DB에 연결하고 그 값을 가져온다

      Class.forName("org.mariadb.jdbc.Driver");

      con = DriverManager.getConnection("jdbc:mysql://localhost:3307/oop", "root", "1111");
      String sql = "select * from books order by book_id desc";
      PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      rs = pstmt.executeQuery();




      // select 문을 실행할 때는 executeQuery() 메서들르 사용하고
      // 그 외, insert, delete, update 문을 실행할 때는
      // executeUpdate() 메서드를 사용함

      // 컴포넌트들을 생성한다
      // 생성된 컴포넌트들을 JFrame 객체(this)에 추가한다
      // 이 JFrame 객체(this)의 Layout을 GridLayout으로 설정한다

      this.setLayout(new GridLayout(0,2));

      this.add(new JLabel("ID", JLabel.CENTER));
      idField = new JTextField(10);
      this.add(idField);

      this.add(new JLabel("Title", JLabel.CENTER));
      titleField = new JTextField(10);
      this.add(titleField);

      this.add(new JLabel("Publisher", JLabel.CENTER));
      publisherField = new JTextField(10);
      this.add(publisherField);

      this.add(new JLabel("Year", JLabel.CENTER));
      yearField = new JTextField(10);
      this.add(yearField);

      this.add(new JLabel("Price", JLabel.CENTER));
      priceField = new JTextField(10);
      this.add(priceField);



      // 버튼 컴포넌트들의 ActionListener를 이 객체(this)로 설정한다.

      previousBtn = new JButton("Previous");
      previousBtn.addActionListener(this);
      this.add(previousBtn);

      nextBtn = new JButton("Next");
      nextBtn.addActionListener(this);
      this.add(nextBtn);

      insertBtn = new JButton("Insert");
      insertBtn.addActionListener(this);
      this.add(insertBtn);

      finishBtn = new JButton("Finish");
      finishBtn.addActionListener(this);
      this.add(finishBtn);

      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setSize(350, 200);
      this.setVisible(true);
   }

   public static void main(String[] args) throws Exception{
      new BookListViewer();
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      try {
         if(e.getSource() == previousBtn) {
            System.out.println("previous Button");
            // 결과 집합의 커서를 이전으로 이동하고
            // 그 커서가 가리키는 결과 레코드의 컬럼 값을 읽어 와서
            // 이 텍스트 필드의 텍스트로 설정한다
            rs.previous();
            setCurrentBookInfo2TextField();
         } else if(e.getSource() == nextBtn) {
            System.out.println("next Button");
            // 결과 집합의 커서를 다음으로 이동하고
            // 그 커서가 가리키는 결과 레코드의 컬럼 값을 읽어 와서
            // 결과 텍스트 필드의 텍스트로 설정한다
            rs.next();
            setCurrentBookInfo2TextField();
         } else if(e.getSource() == insertBtn) {
            System.out.println("insert Button");
         } else if(e.getSource() == finishBtn) {
            System.out.println("프로그램을 종료합니다.");
            con.close();
            System.exit(0);
         }
      } catch (Exception err) {
         System.out.println(err.getMessage());
      }

   }
   
   private void setCurrentBookInfo2TextField() throws Exception {
      // 현재 레코드의 book_id, title, publisher, year, price
      int bookId = rs.getInt(1);
      String title = rs.getString(2);
      String publisher = rs.getString(3);
      Date year = rs.getDate(4);
      int price = rs.getInt(5);
      
      idField.setText(String.valueOf(bookId));
      titleField.setText(title);
      publisherField.setText(publisher);
      yearField.setText(year.toString());
      priceField.setText(String.valueOf(price));
   }


}
