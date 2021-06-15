package com.koreait.spring2;

import com.koreait.spring2.model.LocationCodeEntity;
import com.koreait.spring2.model.MainMapper;
import com.koreait.spring2.model.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class MainService {
    @Autowired
    MainMapper mainMapper;

    private final String SERVICEURL="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
    private final String SERVICEKEY="Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
    private String decodeServiceKey="";

    public List<LocationCodeEntity> selLocationCodeList(){
        return mainMapper.selLocationCodeList();
    }

    public void saveData(SearchDTO param){
        try {
            decodeServiceKey = URLDecoder.decode(SERVICEKEY, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        final HttpHeaders HEADERS = new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_XML));

        final HttpEntity<String> entity = new HttpEntity<String>(HEADERS);

        String deal_ym = String.format("%s%02d", param.getDeal_year(), param.getDeal_month());

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(SERVICEURL)
                .queryParam("LAWD_CD",param.getEx_cd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServiceKey)
                .build(false);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();

        System.out.println(result);
    }
}
