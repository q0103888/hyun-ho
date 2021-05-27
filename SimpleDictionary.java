package m04d01;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDictionary extends JPanel implements ActionListener {

   // �ܾ� �Է� ���� �� �ִ� JTextField
   // �˻� ��ư, �߰� ��ư
   // �ܾ��� ������ ���� �ڷ� ������ Map ��ü ����

   private JTextField inputField = new JTextField(30);
   private JButton searchBtn = new JButton("�˻�");
   private JButton addBtn = new JButton("�߰�");
   private static final String driverClassName = "org.mariadb.jdbc.Driver";
   
   // DB���� ���� URL ������ �޶� db���� ã�ƺ��ƾ� �Ѵ�
   private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
   private static final String DB_SERVER_URL = "jdbc:mariadb://localhost:3307/oop";;
   private static final String DB_USER = "root";
   private static final String DB_USER_PW = "1111";


   // Map��ü�� �ܾ��� �������� ���
   // key�� �ѱ۴ܾ�, value�� �����Ǵ� ���� �ܾ �����Ѵ�
   private Map<String, String> words = new HashMap<>();
   private static final String DIC_FILE_NAME = "dict.props";

   public SimpleDictionary() {
      // Panel�� �⺻ ���̾ƿ��� FlowLayout
      this.add(inputField);
      this.add(searchBtn);
      this.add(addBtn);


      // �� ��ư�� �۸� �̺�Ʈ�� �߻� ������ ó���� �����ʸ� ����
      searchBtn.addActionListener(this);
      addBtn.addActionListener(this);

      this.setPreferredSize(new Dimension(600, 50));

      // ���Ͽ� key=value ���·� ����� ��Ʈ������ �о, words�� �����ϱ�
      // DB���� ���ڵ带 �а�, �� ���ڵ带 �̿��ؼ� dict���� �����Ѵ�.

      buildDictionaryFromDB();

//      buildDictionaryFromFile();
   }

   private void buildDictionaryFromDB() {
      // 1. Database ����: ���� JDBC Driver�� �ε� �ؾ� �Ѵ�
      //      DriverManager(java.sql ��Ű���� ���ǵ� Ŭ����)
      //     Connection con = DriverManager.getConnection();
      //    �޼��带 ȣ���� ������ establish�Ѵ�
      //    �� �� ���� ������ getConnection() �޼��忡 ���� �� ��� �Ѵ�
      //    ���� ����: DB Server�� URL(IP�ּҿ� port ��ȣ����), DB������� ID�� ��ȣ
      // 2. Connection ��ü�� ���� SQL�� ������ ������ ��û�ϰ� �� ����� �޾� ó���Ѵ�.
      //    �ΰ��� ����� �ִ�
      //    ���� SQL��: ���α׷��� ������ ������ SQL���� �����ǰ� ������ ���.
      //    ù��°�� con.createStatement() �޼ҵ� ȣ���� ���ؼ� ��ȯ�Ǵ� Statement ��ü�� �̿��ϴ� ���
      //    �ι�°�� con.perareStatement() �޼��� ȣ���� ���� ��ȯ�Ǵ� PreparedStatement ��ü�� �̿��ϴ� ����� �ִ�.
      //    ���� SQL��: ���α׷��� ������ ������ SQL���� �������� �ʰ� ��� ����Ǵ� SQL��
      //    �� ���������� PreparedStatement ��ü�� �̿��Ѵ�.
      //    ������ ���� �� �غ� �Ǿ��ٴ� �ǹ�(�����˻�, ���缺 �˻�, excution plan���� �غ� �Ϸ�� �ܰ�)
      //    String sql = "select * from dict";
      //    con.perpareStatement(sql);
      //    �� �����ϸ� �ش� sql�� �޾Ƽ� ������ �غ� ��ġ�� �� ��ü�� ��ȯ���ش�.
      //    PreparedStatement pstmt = con.perpareStatement(sql);
      //    pstmt�� ������ sql���� �����Ѵ�.
      //    ���� �غ� �� PreparedStatement�� ���� ��Ű�� ����� ũ�� �ΰ����� �ִ�
      //    ù��° ���
      //    ������ SQL���� insert, delete update���� ���
      //    pstmt.excuteUpdate();�� �����Ѵ� (���� �� ��ȯ ����� ���� ���� ��ȯ)
      //    �ι�° ���
      //    ������ SQL���� select���� ���
      //    ResultSet rs = pstmt.excuteQuery(); 
      // 3. DB Server���� ������ �����Ѵ�
      //    con.close();   
      
      // MySQL JDBC ����̹��� �޸𸮿� ����
      // ����̹� Ŭ���� �̸��� DBMS���� �ٸ���
      // ClassNotFoundException�� �߻��� �� �־� try-catch������ ������ ����ش�
      
      try {
      Class.forName(driverClassName);
      } catch (Exception e) {
         return;
      }
   // DB������ ����
      try (Connection con = 
            DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) {
            
      
      
      // sql(select��) ����
      String sql = "select * from dict";
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      
      while(rs.next()) {
         // ���� �����Ͱ� ����Ű�� Į�� ���� ���´�
         // �� Į���� Ÿ�Կ� ���� ȣ���� �޼��尡 �޶�����
         // char, varchar Ÿ���� �÷��� getString("Į���̸�" �Ǵ� "Į����ġ")
         // int Ÿ���� Į���� getInt()
         // DateTime, Date Ÿ���� �÷� ���� getDate()�� ȣ���ؾ��Ѵ�
         String han = rs.getString("hword");
         String eng = rs.getString("eword");
         
         words.put(han, eng);
      }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } 
      
   }

   private void buildDictionaryFromFile() {
      // Properties��
      // key, value�� Ÿ���� ���� String, String���� ������ ������ ���̴�. 
      Properties props = new Properties();
      //      props.put("���", "apple");


      // ���Ͽ��� �о props ��ü�� <key, value>���� ������ �� �ִ�.
      // ������ �������� FileReader�� �������ش�

      //      FileReader fReader = new FileReader(DIC_FILE_NAME);
      //      props.load(fReader);

      try(FileReader fReader = new FileReader(DIC_FILE_NAME)) {
         props.load(fReader);
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }

      Set<Object> set = props.keySet();
      for(Object obj : set) {
         words.put((String)obj, (String)props.get(obj));
      }
   }



   @Override
   public void actionPerformed(ActionEvent e) {
      String key = inputField.getText();
      if(key.trim().length() == 0) return;

      if(e.getSource() == searchBtn) {
         // �Էµ� �ܾ �����Ͽ�
         // �� �ܾ key ������ ������ �����ȴ� �� ��Ʈ���� �ִ��� �˻� words.get(�ܾ�);
         // �� �ܾ �����Ǵ� ���� ������ JOptionPane.showMessageDialog() �޼��带 ȣ���ؼ� �����Ǵ� ���� �����ش�
         // ���� ������ (null) JOptionPane.showMessageDialog() �޼��带 ȣ���ؼ�
         // �ܾ ã�� �� �����ϴ� ��� ������ش�
         // inputField�� �� ���ڿ��� �����Ѵ�

         System.out.println("[" + key + "]");
         String value = words.get(key);

         if(value != null) {
            // �����Ǵ� �ܾ �ִ� ���
            JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
         } else {
            // �����Ǵ� �ܾ ���� ���
            JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�.", key, JOptionPane.ERROR_MESSAGE);
         }
      } else if(e.getSource() == addBtn) {
         // �Էµ� �ܾ ����
         // JOptionPane.showInputDialog() �޼��带 ȣ���ؼ� �߰��� ���� �ܾ �Է� �޴´�
         // words.put(�Է� �ʵ忡 �Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�)
         // DB�� <key, value>�� ���� �ϳ��� ���ڵ�� �����Ѵ�.

         String value = JOptionPane.showInputDialog(this, key, "�� ���� �Ǵ� ���� �ܾ �Է��ϼ���.");

         if(value.trim().length() == 0) return;
         words.put(key, value);
         words.put(value, key);
         addToDB(key, value);
         addWordToFile(key, value);
         JOptionPane.showMessageDialog(this, "[" + value + "]" + "���� �ܾ �߰��Ǿ����ϴ�.", key, JOptionPane.INFORMATION_MESSAGE);
      }

      //      inputField.setText("");

   }

   private void addToDB(String key, String value) {
	   /*
		 * 1.
		 * ����̹� Ŭ������ �� �ѹ��� �޸𸮿� �����ϸ� ��.
		 * ��ü�� �̹� �����Ǿ� ������ �����ڿ� ����ǹǷ� ���⼭ ������ �ʿ䰡 ����.
		 * 2.
		 * DB ����
		 * Connection ��ü���� ������ SQL ���� �����غ� ��û�ϰ� con.prepareStatement(sql);
		 * PreparedStatement ��ü�� ��ȯ��.
		 * preparedStatement ��ü���� �������� ���� ��û.
		 * preparedStatement.executeUpdate(); => ������ SQL ���� INSERT, DELETE, UPDATE �϶�.
		 * preparedStatement.executeQuery(); => ������ SQL���� SELECT ���϶�.
		 * 3.
		 * SQL�� ����
		 * 4.
		 * DB ��������
		 */

	   try(Connection con = 
			   DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
			   String sql = "insert into dict values(?,?)";
					   
			   PreparedStatement pstmt = con.prepareStatement(sql);
			   //?�ڸ��� ���� ä�� �Ŀ�, ��������
			   //�����غ��sql���� �����϶�� ��û�ؾ� �Ѵ�
			   pstmt.setString(1, key);
			   pstmt.setString(2, value);
			   
			   pstmt.executeUpdate(); //���� ��û
   }catch (Exception e) {
	   System.out.println(e.getMessage());
	   e.printStackTrace();
   }
   }

   private void addWordToFile(String key, String value) {
      try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)) {
         // FileWriter�� write�� ��� ����Ƿ� �������� �߰��� �͸� ���Ͽ� ���´�.
         fWriter.write(key + "=" + value + "\n");
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.add(new SimpleDictionary());

      frame.setTitle("�ѿ�����");
      frame.setResizable(false);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
   }

}