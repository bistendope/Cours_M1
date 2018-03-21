package fr.miage.orleans.applisondages.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import services.Constantes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static services.Constantes.AUTHORIZATION;

public class SecurityInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
    private static final String SERTOK= "http://localhost:8080";
    private static final String TOKLOG = SERTOK+"/login";
    private static final String TOKCHK = SERTOK + "/checkToken";


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //test presence token
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if((token!=null) && (token.length() > 0)){
            RestTemplate estTemplate1 = new RestTemplate();
            HttpHeaders httpheaders2 = new HttpHeaders();
            httpheaders2.put(AUTHORIZATION, Arrays.asList(token));
            MultiValueMap<String, String> map2 = new LinkedMultiValueMap<String, String>();
            HttpEntity<MultiValueMap<String, String>> httpEntity2 = new HttpEntity<MultiValueMap<String, String>>(map2, httpheaders2);

        }
        //token absent ou invalide
        // réponse statut NOT_ACCEPTABLE
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

        //indiquer URI serveur token
        httpServletResponse.setHeader(Constantes.LOCATION, TOKLOG);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
