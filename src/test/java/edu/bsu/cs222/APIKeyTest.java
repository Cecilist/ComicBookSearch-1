package edu.bsu.cs222;

import edu.bsu.cs222.model.APIKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class APIKeyTest {
    private APIKey apiKey;

    @BeforeEach
    public void setup() throws FileNotFoundException {
        apiKey = new APIKey("fakeAPI.txt");
    }

    @Test
    public void getPublicKey_public_ReturnPublic() {
        Assertions.assertEquals("public", apiKey.getPUBLIC_KEY());
    }

    @Test
    public void getHashKey_hash_ReturnHash() {
        Assertions.assertEquals("hash", apiKey.getHASH_KEY());
    }
}
