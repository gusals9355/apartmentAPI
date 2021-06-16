package com.koreait.spring2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.spring2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.stream.Location;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    @Autowired
    MainMapper mainMapper;


    public List<LocationCodeEntity> selLocationCodeList(){
        return mainMapper.selLocationCodeList(null);
    }

    public void saveData(SearchDTO param) {
        List<ApartmentEntity> aList = mainMapper.selApartmentInfoList(param);
        if(aList.size() > 0){
            return;
        }

        final String URL="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        String decodeServiceKey="Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";

//        final HttpHeaders HEADERS = new HttpHeaders();
//        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
//        final HttpEntity<String> entity = new HttpEntity<String>(HEADERS);
        // 디폴트값은 제이슨형태로 되있음

        String deal_ym = String.format("%s%02d", param.getDeal_year(), param.getDeal_month());

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("LAWD_CD",param.getEx_cd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("numOfRows", "1000")
                .build(false);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, /*entity*/ null, String.class);
        String result = responseEntity.getBody();
        System.out.println(result);

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentEntity[] list = null;
        try {
            jsonNode = om.readTree(result);
            list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item"),ApartmentEntity[].class);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<LocationCodeEntity> locationList = mainMapper.selLocationCodeList(param);
        LocationCodeEntity code = locationList.get(0);

        InsertEntity param2 = new InsertEntity();
        param2.setLocation_cd(code.getCd());
        param2.setArr(list);

        for(ApartmentEntity a : list){
            System.out.println(a);
        }

        mainMapper.insApartment(param2);
    }

    public List<ApartmentDTO> view(SearchDTO param){
        param.setLocation_cd(mainMapper.selLocationCodeList(param).get(0).getCd());
        return mainMapper.selApartmentList(param);
    }


}
