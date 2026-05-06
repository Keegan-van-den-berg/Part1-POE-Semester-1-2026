/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Heave
 */
import com.mycompany.assignment1.Message;
public class MessagesUnitTest {
    Message testMessage = new Message();
    public MessagesUnitTest() {
    }
   
    @Test
    public void checkMessageTrue(){
        assertEquals("Message ready to send!",
                testMessage.checkMessage("Hi Mike, can you join us for dinner tonight?"));
    }
    
    @Test
    public void checkMessageFalse(){
        assertEquals("Message exceeds 250 characters by 1; Please reduce the size", 
                testMessage.checkMessage("Hi Mike, can you join us for dinner tonight? "
                        + "We are planning a relaxed evening with great food, "
                        + "some music, and a few friends, and it would be really "
                        + "nice if you could come along and enjoy the time with us, "
                        + "even if you can only stay for a short while."));
    }
    
    @Test
    public void checkRecipientCellTrue(){
        assertEquals("\nRecipient's phone number has successfully been captured!", 
                testMessage.checkRecipientCell("+27718693002"));
    }
    
    @Test
    public void checkRecipientCellFalse(){
        assertEquals("\nError! "
                    + "\nPlease ensure that the number you are entering"
                    + "\nis no more than 10 characters and contains an international code",
                testMessage.checkRecipientCell("0718693002"));
    }
    
    @Test
    public void messageIDCreatedTrue(){
        testMessage.genrateMessageID();
        assertTrue(testMessage.checkMessageID());
    }
    
    @Test
    public void messageIDCreatedFalse(){
        assertFalse(testMessage.checkMessageID());
    }
    
    @Test 
    public void checkMessageSentOne(){
        assertEquals("Message successfully sent!", testMessage.SentMessage(1));
    }
    
    @Test
    public void checkMessageSentTwo(){
        assertEquals("Message has been successfully deleted!", testMessage.SentMessage(2));
    }
    
    @Test
    public void checkMessageSentThree(){
        assertEquals("Message has successfully been stored!", testMessage.SentMessage(3));
    }
    
    @Test
    public void checkMessageSentDefault(){
        assertEquals("Error, please select one of the given option", testMessage.SentMessage(4));
    }
    
    @Test
    public void checkMessageHashCorrectTrue(){
        String[] messages = {"Hi Mike, can you join us for dinner tonight?", "Hi Keegan, did you receive the payment?"};
        
        for (int i = 0; i< messages.length; i++){
            String messageID = testMessage.genrateMessageID();
            testMessage.checkMessage(messages[i]);
            testMessage.getNumOfMessages(i);
            testMessage.getFirstAndLastWord();
            
            if (i == 0){
                assertEquals(messageID.substring(0, 2) + ":0:HITONIGHT",
                    testMessage.createMessageHash());
            } else if (i == 1){
                assertEquals(messageID.substring(0, 2) + ":0:HIPAYMENT",
                    testMessage.createMessageHash());
            }
        }
    }
    
    
}
