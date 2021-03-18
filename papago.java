package WD_J;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class papago extends JFrame implements ActionListener{

   private JButton convertBtn;
   private JButton cancelBtn;
   private JTextArea textIn;
   private JTextArea textOut;
   private final String CLIENT_ID = "wGpjNKwPPorZQXrnWKBb";
   private final String CLIENT_SECRET = "GpBlVoNDJK";
   
   public papago() {
      this.setTitle("�ؽ�Ʈ ��ȯ");
      
      textIn = new JTextArea(10, 14);
      textOut = new JTextArea(10, 14);
      textIn.setLineWrap(true); //�ٹٲ�
      textOut.setLineWrap(true); //�ٹٲ�
      textOut.setEditable(false); // ����x
      
      JPanel textAreaPanel = new JPanel(new GridLayout(1, 2, 20, 20));
      textAreaPanel.add(textIn);
      textAreaPanel.add(textOut);
      
      convertBtn = new JButton("��ȯ");
      cancelBtn = new JButton("���");
      convertBtn.addActionListener(this);
      cancelBtn.addActionListener(this);
      
      
      JPanel btnPanel = new JPanel();
      btnPanel.add(convertBtn);
      btnPanel.add(cancelBtn);
      
      JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
      mainPanel.add(textAreaPanel, BorderLayout.CENTER);
      mainPanel.add(btnPanel, BorderLayout.SOUTH);
      
      this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
      this.add(mainPanel);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // ��ȯ ��ư�� Ŭ���Ǿ��ٸ�
      // ���� textArea (textIn)�� text�� �о ����� ��ȯ�ϰ�
      // �� ����� ���� textArea (textOut)�� append�Ѵ�
      // ��� ��ư�� Ŭ�� �Ǿ��ٸ� ���� (textOut)�� �� ���ڿ��� ����
      if (e.getSource() == convertBtn) {
    	  String str = textIn.getText();
    	  String result = toEnglish(str);
    	  textOut.setText(result);
      }else {
    	  textOut.setText("");
      }
   }
   
   private String toEnglish(String korean) {
      // korean ���ڿ��� ����� ��ȯ�ؼ� ��ȯ�Ѵ�
	   /*
	    * �ؽ�Ʈ -> text
	    * ���� -> english
	    */
	   String result = korean;
//	   result = korean.replace("�ؽ�Ʈ" , "text");
//	   result = result.replace("����", "english");
	   String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	      String text;
	      try {
	         text = URLEncoder.encode(korean, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException("���ڵ� ����", e);
	      }

	      Map<String, String> requestHeaders = new HashMap<>();
	      requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
	      requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

	      result = post(apiURL, requestHeaders, text);

	      //������� ����ϰ� �ϱ�
	      result = result.substring(result.indexOf("translatedText")+"translatedText".length()+3,result.indexOf("engineType")-3);

	      System.out.println(result);


	      return result;

   }
   
   private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
       HttpURLConnection con = connect(apiUrl);
       String postParams = "source=ko&target=en&text=" + text; //�������: �ѱ��� (ko) -> �������: ���� (en)
       try {
           con.setRequestMethod("POST");
           for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
               con.setRequestProperty(header.getKey(), header.getValue());
           }

           con.setDoOutput(true);
           try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
               wr.write(postParams.getBytes());
               wr.flush();
           }

           int responseCode = con.getResponseCode();
           if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ����
               return readBody(con.getInputStream());
           } else {  // ���� ����
               return readBody(con.getErrorStream());
           }
       } catch (IOException e) {
           throw new RuntimeException("API ��û�� ���� ����", e);
       } finally {
           con.disconnect();
       }
   }

   private static HttpURLConnection connect(String apiUrl){
       try {
           URL url = new URL(apiUrl);
           return (HttpURLConnection)url.openConnection();
       } catch (MalformedURLException e) {
           throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
       } catch (IOException e) {
           throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
       }
   }

   private static String readBody(InputStream body){
       InputStreamReader streamReader = new InputStreamReader(body);

       try (BufferedReader lineReader = new BufferedReader(streamReader)) {
           StringBuilder responseBody = new StringBuilder();

           String line;
           while ((line = lineReader.readLine()) != null) {
               responseBody.append(line);
           }

           return responseBody.toString();
       } catch (IOException e) {
           throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
       }
   }
   
   public static void main(String[] args) {
	   JFrame text = new papago();
   }

   
}