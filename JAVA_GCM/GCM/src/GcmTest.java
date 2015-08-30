import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class GcmTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("title을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("messge를 입력하세요.");
		String msg = sc.next();

		// 프로젝트 서버 API key 입력
        Sender sender = new Sender("AIzaSyAV3dvUrqIiRWHAQLs0bJ5ORlxytZaQQu4"); 
        
        // GCM으로부터 발급받은 단말기 RegID 입력.
        String regId = "dmxZ9xdfnFg:APA91bHlIqy7llpg03v6K2mrRqUHPiscYcqQb-kV8IP7RzyqyoMna3IahYVm1zQb__Xsf4jmvjWr60hS9zb9LmaGeawTs78UdTFbpoloZziSW4EPsYh-ryuUD0M1c3H-a1d0HgjzXuS8";
        
        /**
         *  아래의 내용은 메세지 보낼때 여러 속성이 있다는 내용.(바로 밑의 사이트들 참고)
         *  https://developers.google.com/cloud-messaging/http-server-ref
         *  http://arrkaize86.tistory.com/entry/GCM-GCM-%EC%84%9C%EB%B2%84-JSON-%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0-%EC%A0%95%EB%B3%B4
         */
        
	    /*@Test
	    public void testRequiredParameters() {
	    	Message message = new Message.Builder().build();
	    	assertNull(message.getCollapseKey());
	    	assertNull(message.isDelayWhileIdle());
	    	assertTrue(message.getData().isEmpty());
	    	assertNull(message.getTimeToLive());
	    	String toString = message.toString();
	    	assertFalse(toString.contains("collapseKey"));
	    	assertFalse(toString.contains("timeToLive"));
	    	assertFalse(toString.contains("delayWhileIdle"));
	    	assertFalse(toString.contains("data"));
          
	      @Test
	      public void testOptionalParameters() {
	    	  Message message = new Message.Builder()
	    	  	.collapseKey("108")
		        .delayWhileIdle(true)
		        .timeToLive(42)
		        .dryRun(true)
		        .restrictedPackageName("package.name")
		        .addData("k1", "old value")
		        .addData("k1", "v1")
		        .addData("k2", "v2")
		        .build();
		        assertEquals("108", message.getCollapseKey());
		        assertTrue(message.isDelayWhileIdle());
		        assertEquals(42, message.getTimeToLive().intValue());
		        assertTrue(message.isDryRun());
		        assertEquals("package.name", message.getRestrictedPackageName());
		        Map<String, String> data = message.getData();
		        assertEquals(2, data.size());
		        assertEquals("v1", data.get("k1"));
		        assertEquals("v2", data.get("k2"));
		        String toString = message.toString();
		        assertTrue(toString.contains("collapseKey=108"));
		        assertTrue(toString.contains("timeToLive=42"));
		        assertTrue(toString.contains("delayWhileIdle=true"));
		        assertTrue(toString.contains("k1=v1"));
		        assertTrue(toString.contains("k2=v2"));
		        assertTrue(toString.contains("dryRun=true"));
		        assertTrue(toString.contains("restrictedPackageName=package.name"));
	      }
	
	      @Test(expected = UnsupportedOperationException.class)
	      public void testPayloadDataIsImmutable() {
	        Message message = new Message.Builder().build();
	        message.getData().clear();
	      }*/
          
          
        Message message = new Message.Builder()
        .addData("title", title)
        .addData("message", msg)
        .build();
        
        List<String> list = new ArrayList<String>();
        list.add(regId);
        
        MulticastResult multiResult;
        try {
            multiResult = sender.send(message, list, 5);
            if (multiResult != null) {
                List<Result> resultList = multiResult.getResults();
                for (Result result : resultList) {
                    System.out.println(result.getMessageId());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
