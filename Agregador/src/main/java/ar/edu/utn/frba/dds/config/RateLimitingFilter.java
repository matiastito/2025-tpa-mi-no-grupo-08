package ar.edu.utn.frba.dds.config;

import static io.github.bucket4j.Bucket.builder;
import static java.time.Duration.ofSeconds;

import io.github.bucket4j.Bucket;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RateLimitingFilter implements Filter {
  private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();
  Logger logger = LoggerFactory.getLogger(RateLimitingFilter.class);

  private Bucket getBucket(String clientId) {
    return buckets.computeIfAbsent(clientId, k ->
        builder()
            .addLimit(limit -> limit.capacity(50).refillGreedy(10, ofSeconds(1)))
            .build()
    );
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String clientIp = httpRequest.getRemoteAddr();
    Bucket bucket = getBucket(clientIp);
    logger.info("RateLimit check.");
    if (bucket.tryConsume(1)) {
      chain.doFilter(request, response);
    } else {
      response.getWriter().write("Too Many Requests");
      response.getWriter().flush();
    }
  }
}