package com.api.instagram;

import com.transfer.ProtocolBuilder;
import com.transfer.ProtocolBuilderHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * InstagramAPI
 * Version information
 * 2022.02.22 1 author : @woosik
 * Class Overview
 * 해당 클래스는 유지보수 중 입니다.
 * 남은 일 : 디버깅 및 확인
 * Case : instagramAPI.getImages(type) (Autowired);
 */
@Slf4j
@Service
public class InstagramAPI {

    @Value("${IG_TOKEN}")
    private String ACCESS_TOKEN;
    private final String BASE_URL = "https://graph.instagram.com";
    private final String MEDIA_LIST_URL = "me/media";

    /**
     * 해당 TOKEN 을 가진 계정의 게시글 사진 정보를 가지고와서 배열에 담아 반환합니다.<br>
     * <a href="https://developers.facebook.com/docs/instagram-basic-display-api/reference/media#fields">url fields reference</a><br>
     * <a href="https://developers.facebook.com/docs/instagram-basic-display-api/guides/getting-profiles-and-media">request url reference</a><br>
     * <a href="https://www.notion.so/Instagram-Media-API-Guide-18a9e679211f4cdb971119222b0825f4">IG_TOKEN reference</a>
     *
     * @param type {@code String}
     *             <br>FIRST(default) : 각 게시글의 첫번째 사진만 가져오기
     *             <br> ALL : 여러장있는 게시글이 있다면 모든 사진 가져오기
     * @return {@code ArrayList<String>}
     * @since 2022-02-08
     */
    // todo - logic with video
    public List<String> getImages(String type) {
        ArrayList<String> images = new ArrayList<>();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("fields", "id,media_type");
            params.put("access_token", ACCESS_TOKEN);
            StringBuilder stringBuilder = ProtocolBuilderHelper.setGetMethodParams(params);

            ProtocolBuilder protocolBuilder = new ProtocolBuilder()
                    .url(BASE_URL + "/" + MEDIA_LIST_URL + stringBuilder.toString())
                    .conn()
                    .setRequestMethod("GET");
            IGResponse response = (IGResponse) protocolBuilder.openReader("UTF-8", IGResponse.class, true);

            if (IGConstant.FETCH_TYPE.FIRST.equals(type)) { // 각 게시글의 첫번째 사진만 가져오기
                for (IGData data : response.getData()) {
                    params = new HashMap<>();
                    params.put("fields", "media_type,media_url");
                    params.put("access_token", ACCESS_TOKEN);
                    stringBuilder = ProtocolBuilderHelper.setGetMethodParams(params);
                    protocolBuilder = new ProtocolBuilder()
                            .url(BASE_URL + "/" + data.getId() + stringBuilder.toString())
                            .conn()
                            .setRequestMethod("GET");
                    IGData res = (IGData) protocolBuilder.openReader("UTF-8", IGData.class, true);
                    images.add(res.getMedia_url());
                }
            } else if (IGConstant.FETCH_TYPE.ALL.equals(type)) { // 모든사진 가져오기
                for (IGData data : response.getData()) {
                    params.clear();
                    params.put("access_token", ACCESS_TOKEN);
                    if (IGConstant.MEDIA_TYPE.IMAGE.equals(data.getMedia_type())) { // 한장의 이미지인경우
                        params.put("fields", "media_url");
                        stringBuilder = ProtocolBuilderHelper.setGetMethodParams(params);
                        protocolBuilder = new ProtocolBuilder()
                                .url(BASE_URL + "/" + data.getId() + stringBuilder.toString())
                                .conn()
                                .setRequestMethod("GET");
                        IGData res = (IGData) protocolBuilder.openReader("UTF-8", IGData.class, true);
                        images.add(res.getMedia_url());
                    } else if (IGConstant.MEDIA_TYPE.CAROUSEL_ALBUM.equals(data.getMedia_type())) { // 여러개의 이미지인 경우
                        params.put("fields", "media_url,media_type");
                        stringBuilder = ProtocolBuilderHelper.setGetMethodParams(params);

                        protocolBuilder = new ProtocolBuilder()
                                .url(BASE_URL + "/" + data.getId() + "/" + "children" + stringBuilder.toString())
                                .conn()
                                .setRequestMethod("GET");

                        IGResponse res = (IGResponse) protocolBuilder.openReader("UTF-8", IGResponse.class, true);
                        for (IGData igData : res.getData()) {
                            if (IGConstant.MEDIA_TYPE.IMAGE.equals(igData.getMedia_type())) {
                                images.add(igData.getMedia_url());
                            } else if (IGConstant.MEDIA_TYPE.VIDEO.equals(igData.getMedia_type())) {
                                // todo : logic with video
                            }
                        }
                    } else if (IGConstant.MEDIA_TYPE.VIDEO.equals(data.getMedia_type())) { // 비디오인경우
                        params.put("fields", "media_url");
                        stringBuilder = ProtocolBuilderHelper.setGetMethodParams(params);
                        protocolBuilder = new ProtocolBuilder()
                                .url(BASE_URL + "/" + data.getId() + stringBuilder.toString())
                                .conn()
                                .setRequestMethod("GET");
                        IGData res = (IGData) protocolBuilder.openReader("UTF-8", IGData.class, true);
                        // todo : logic with video
                    }
                }
            }
            return images;
        } catch (IOException e) {
            e.printStackTrace();
            return images;
        }
    }
}
