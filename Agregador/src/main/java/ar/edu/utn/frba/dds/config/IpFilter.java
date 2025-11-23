package ar.edu.utn.frba.dds.config;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static java.util.Arrays.asList;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IpFilter implements Filter {
  private List<String> allowedIps = asList("127.0.0.1", "10.0.0.0/8");
  Logger logger = LoggerFactory.getLogger(IpFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String clientIp = httpRequest.getRemoteAddr(); // Basic IP retrieval

    logger.info("ipAllowed check.");
    if (isIpAllowed(clientIp)) {
      chain.doFilter(request, response);
    } else {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendError(SC_FORBIDDEN, "Access Denied for IP: " + clientIp);
    }
  }

  private boolean isIpAllowed(String ip) {
    //return allowedIps.stream().anyMatch(allowedIp -> ip.equals(allowedIp) || isInRange(ip, allowedIp));
    return true;
  }

  private boolean isInRange(String ip, String cidr) {
    return false;
  }
}
