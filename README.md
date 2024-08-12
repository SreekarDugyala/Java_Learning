
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyController.class) // Replace with your actual controller class name
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsyncService service; // Replace with your actual service class name

    @Test
    public void testProcessEntitlementFile_Success() throws Exception {
        // Given
        String uri = "valid-uri";
        String filePath = "valid-file-path";
        String resultMessage = "Processing Completed Successfully";
        
        Mockito.when(service.storeEntitlementFile(anyString())).thenReturn(filePath);
        Mockito.when(service.processFile(anyString()))
               .thenReturn(CompletableFuture.completedFuture(resultMessage));
        
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/file")
                .param("uri", uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        
        Mockito.verify(service).storeEntitlementFile(uri);
        Mockito.verify(service).processFile(filePath);
    }

    @Test
    public void testProcessEntitlementFile_Exception() throws Exception {
        // Given
        String uri = "valid-uri";
        String filePath = "valid-file-path";
        
        Mockito.when(service.storeEntitlementFile(anyString())).thenReturn(filePath);
        Mockito.when(service.processFile(anyString()))
               .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Processing error")));
        
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/file")
                .param("uri", uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        
        Mockito.verify(service).storeEntitlementFile(uri);
        Mockito.verify(service).processFile(filePath);
    }

    @Test
    public void testProcessEntitlementFile_InvalidUri() throws Exception {
        // Given
        String uri = "invalid-uri";
        
        Mockito.when(service.storeEntitlementFile(anyString()))
               .thenThrow(new RuntimeException("An error occurred while downloading and storing the file."));
        
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/file")
                .param("uri", uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        
        Mockito.verify(service).storeEntitlementFile(uri);
        Mockito.verify(service, Mockito.never()).processFile(anyString());
    }
}
