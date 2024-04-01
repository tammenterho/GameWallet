package com.gameWallet.gameWallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameWalletControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void purchaseTest_ShouldUpdateBalance() throws Exception {
        Long playerId = 1L;
        Long eventId = 1L;
        Long amount = 100L; // Balance is set to 500 in purchaseService
        
        
       
        mockMvc.perform(put("/players/{playerId}/purchase/{eventId}", playerId, eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(amount.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Game purchased successfully\nBalance: 400"));
    }
    
    @Test
    public void purchaseTest_ShouldThrowException() throws Exception {
        Long playerId = 1L;
        Long eventId = 1L;
        Long amount = 700L; // Balance is set to 500 in purchaseService
        
        
        mockMvc.perform(put("/players/{playerId}/purchase/{eventId}", playerId, eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(amount.toString()))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void winTest_win() throws Exception {
        Long playerId = 1L;
        Long winEventId = 2L;
        Long amount = 1000L; 
        
        
        mockMvc.perform(put("/players/{playerId}/win/{winEventId}", playerId, winEventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(amount.toString()))
        		.andExpect(content().string("Win collected\n" + "Balance: 1400")); //balance after tests
    }
    
    @Test
    public void winTest_sameWinCollectException() throws Exception {
        Long playerId = 1L;
        Long winEventId = 2L;
        Long amount = 1000L; 
        

        String jsonContent = String.format("{\"amount\": %d}", amount);
        
        mockMvc.perform(put(String.format("/players/%d/win/%d", playerId, winEventId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

}
