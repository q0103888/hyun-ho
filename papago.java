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
      this.setTitle("텍스트 변환");
      
      textIn = new JTextArea(10, 14);
      textOut = new JTextArea(10, 14);
      textIn.setLineWrap(true); //줄바꿈
      textOut.setLineWrap(true); //줄바꿈
      textOut.setEditable(false); // 편집x
      
      JPanel textAreaPanel = new JPanel(new GridLayout(1, 2, 20, 20));
      textAreaPanel.add(textIn);
      textAreaPanel.add(textOut);
      
      convertBtn = new JButton("변환");
      cancelBtn = new JButton("취소");
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
      // 변환 버튼이 클릭되었다면
      // 좌측 textArea (textIn)의 text를 읽어서 영어로 변환하고
      // 이 결과를 우측 textArea (textOut)에 append한다
      // 취소 버튼이 클릭 되었다면 우측 (textOut)을 빈 문자열로 설정
      if (e.getSource() == convertBtn) {
    	  String str = textIn.getText();
    	  String result = toEnglish(str);
    	  textOut.setText(result);
      }else {
    	  textOut.setText("");
      }
   }
   
   private String toEnglish(String korean) {
      // korean 문자열을 영어로 변환해서 반환한다
	   /*
	    * 텍스트 -> text
	    * 영어 -> english
	    */
	   String result = korean;
//	   result = korean.replace("텍스트" , "text");
//	   result = result.replace("영어", "english");
	   String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	      String text;
	      try {
	         text = URLEncoder.encode(korean, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException("인코딩 실패", e);
	      }

	      Map<String, String> requestHeaders = new HashMap<>();
	      requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
	      requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

	      result = post(apiURL, requestHeaders, text);

	      //결과값만 출력하게 하기
	      result = result.substring(result.indexOf("translatedText")+"translatedText".length()+3,result.indexOf("engineType")-3);

	      System.out.println(result);


	      return result;

   }
   
   private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
       HttpURLConnection con = connect(apiUrl);
       String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
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
           if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
               return readBody(con.getInputStream());
           } else {  // 에러 응답
               return readBody(con.getErrorStream());
           }
       } catch (IOException e) {
           throw new RuntimeException("API 요청과 응답 실패", e);
       } finally {
           con.disconnect();
       }
   }

   private static HttpURLConnection connect(String apiUrl){
       try {
           URL url = new URL(apiUrl);
           return (HttpURLConnection)url.openConnection();
       } catch (MalformedURLException e) {
           throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
       } catch (IOException e) {
           throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
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
           throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
       }
   }
   
   public static void main(String[] args) {
	   JFrame text = new papago();
   }

   
}