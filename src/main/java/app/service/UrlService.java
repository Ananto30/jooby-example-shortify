package app.service;

import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Azizul Haque Ananto
 * @since 18/6/20
 */

@Service
public class UrlService {

    private final StatefulRedisConnection<String, String> statefulRedisConnection;
    private final MessageDigest messageDigest;

    public UrlService(StatefulRedisConnection<String, String> statefulRedisConnection) throws NoSuchAlgorithmException {
        this.statefulRedisConnection = statefulRedisConnection;
        this.messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public String addUrl(String url) {
        String shortCode = UUID.randomUUID().toString().replace("-", "").substring(26);
        statefulRedisConnection.sync().set(shortCode, url);
        return shortCode;
    }

    public String getUrlByShortCode(String shortCode) {
        return statefulRedisConnection.sync().get(shortCode);
    }

    public String encodeUrl(String url)  {
        messageDigest.update(url.getBytes());
        return new String(messageDigest.digest());
    }

}
