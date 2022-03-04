package com.controller;

import com.api.accountVerify.AccountVerifyAPI;
import com.api.accountVerify.TokenResponse;
import com.api.businessRegistration.BusinessRegistrationAPI;
import com.api.instagram.InstagramAPI;
import com.api.lunarsoft.alarm.LunarAlarmAPI;
import com.api.mail.MailBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.model.SplitFileData;
import com.model.User;
import com.model.common.MFile;
import com.response.DefaultRes;
import com.response.Message;
import com.response.ResMessage;
import com.response.StatusCode;
import com.service.BulkFileService;
import com.service.HomeService;
import com.service.OtherHomeService;
import com.util.*;
import com.util.Encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    @Value("${UPLOAD_PATH}")
    private String path;

    private final FileUploadUtility fileUploadUtility;
    private final HomeService homeService;
    private final OtherHomeService otherHomeService;
    private final BusinessRegistrationAPI businessRegistrationAPI;
    private final AccountVerifyAPI accountVerifyAPI;
    private final LunarAlarmAPI lunarAlarmAPI;
    private final InstagramAPI instagramAPI;
    private final MailBuilder mailBuilder;


    @GetMapping("/get-test.do")
    public ModelAndView getTest() {
        log.info("GET");
        return new ModelAndView("test");
    }

    @PostMapping("/post-test1.do")
    public ResponseEntity<String> postTest1(String test) {
        log.info("POST");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PostMapping("/post-test2.do")
    public ResponseEntity<String> postTest2(@RequestBody String data) {
        log.info("POST");
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(data);
        String val = element.getAsJsonObject().get("test").getAsString();
        System.out.println("val = " + val);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PostMapping("/post-test3.do")
    public ResponseEntity<String> postTest3(@RequestBody User user) {
        log.info("POST");
        System.out.println("user = " + user);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PutMapping("/put-test.do")
    public ResponseEntity<String> putTest(String test) {
        log.info("PUT");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PatchMapping("/patch-test.do")
    public ResponseEntity<String> patchTest(String test) {
        log.info("PATCH");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-test.do")
    public ResponseEntity<String> deleteTest(String test) {
        log.info("DELETE");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @GetMapping("/file.do")
    public ModelAndView fileUploadTest() {
        return new ModelAndView("test");
    }

    @PostMapping("/file.do")
    public ModelAndView fileUploadTest(MultipartFile file) {
        MFile mFile = fileUploadUtility.uploadFile(file, Constant.CDN_PATH.TEST);
        if (mFile == null) {
            System.out.println("NO FILE!");
        } else {
            System.out.println("mFile.getName() = " + mFile.getName());
            System.out.println("mFile.getSize() = " + mFile.getSize());
            System.out.println("mFile.getUrl() = " + mFile.getUrl());
        }
        return new ModelAndView("test");
    }

    @PostMapping("/files.do")
    public ModelAndView filesUploadTest(List<MultipartFile> files) {
        List<MFile> mFiles = fileUploadUtility.uploadFiles(files, Constant.CDN_PATH.TEST);
        if (mFiles.isEmpty()) {
            System.out.println("NO FILES!");
        }
        for (MFile mFile : mFiles) {
            System.out.println("mFile.getName() = " + mFile.getName());
            System.out.println("mFile.getSize() = " + mFile.getSize());
            System.out.println("mFile.getUrl() = " + mFile.getUrl());
        }
        return new ModelAndView("test");
    }

    @PostMapping("/filemap.do")
    public ModelAndView filesUploadTest(@RequestParam Map<String, MultipartFile> files) {
        List<MFile> mFiles = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
            MultipartFile file = files.get(entry.getKey());
            MFile mfile = fileUploadUtility.uploadFile(file, Constant.CDN_PATH.TEST);
            if (mfile != null) {
                mFiles.add(mfile);
            }
        }
        for (MFile mFile : mFiles) {
            System.out.println("mFile.getName() = " + mFile.getName());
            System.out.println("mFile.getSize() = " + mFile.getSize());
            System.out.println("mFile.getUrl() = " + mFile.getUrl());
        }
        return new ModelAndView("test");
    }

    @GetMapping("rollback.do")
    public ModelAndView rollbackTestGet(String string) {
        System.out.println("string = " + string);
//        homeService.sqlRollbackTest(string);
        homeService.recursiveSqlRollbackTest(string);
        return new ModelAndView("test");
    }

    @GetMapping("lunarsoft.do")
    public ModelAndView lunarsoftAlarmTest() {
        //lunarAlarmAPI.signUpTest(new SignUp());
        return new ModelAndView("test");
    }

    @ResponseBody
    @GetMapping(value = "/instagram/media")
    @Cacheable(value = "IG")
    public ResponseEntity<String> getInstagramTest(String type) {
        Message message = new Message();
        List<String> images = instagramAPI.getImages(type);
        log.info(images.toString());
        return new ResponseEntity(
                DefaultRes.res(
                        StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("ajax")
                ), HttpStatus.OK
        );
    }

    @GetMapping("mail.do")
    public ModelAndView mailSendTest() {
        try {
            if (mailBuilder.setSession()
                    .setTo("zlzldntlr@naver.com")
                    .setMailTitle("title test")
                    .setMailContent("<h1>content test</h1>")
                    .send()) {
                /** Mail Send Success */
            } else {
                /** Mail Send Failed*/
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ModelAndView("test");
    }

    @GetMapping("/users.do")
    public ModelAndView seeUsers(HttpServletRequest request) {
        List<User> users = otherHomeService.selectUsers();
        request.setAttribute("users", users);
        return new ModelAndView("test");
    }

    @PostMapping("/user.do")
    public ModelAndView registerUsers(User user) {
        otherHomeService.insertUser(user);
        return new ModelAndView("redirect:/users.do");
    }

    @GetMapping("/encrypt.do")
    public ModelAndView encrypt(HttpServletRequest request) {
        User user = new User();
        user.setNo(1);
        user.setEmail("zlzldntlr@naver.com");
        user.setId("239428424");
        user.setName("name");
        user.setAccess_token("access_token");
        try {
            String token = new EncryptionService().encryptJWT(user);
            log.info(user.toString());
            log.info(token);
            log.info(new EncryptionService().decryptJWT(token).toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new ModelAndView("test");
    }

    @GetMapping("/cookie.do")
    public ModelAndView cookieTest(HttpServletRequest request) {
        return new ModelAndView("test");
    }

    @PostMapping("/encrypt.do")
    public ResponseEntity<String> encrypt(String value) {
        Message message = new Message();
        message.put("status", true);
        try {
            String result = new EncryptionService().encryptAES(value);
            System.out.println(value + " --> " + result);
            message.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PostMapping("/decrypt.do")
    public ResponseEntity<String> decrypt(String value) {
        Message message = new Message();
        try {
            String result = new EncryptionService().decryptAES(value);
            System.out.println(value + " --> " + result);
            message.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @RequestMapping(value = "/bulk/upload", method = RequestMethod.GET)
    public ModelAndView getBulkUpload() {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/upload/bulk", method = RequestMethod.POST)
    public ModelAndView postBulkUpload(HttpServletRequest request) {
        log.info("postBulkUpload started");
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                // Inform user about invalid request
                log.info("is'nt Multipart File");
                return new ModelAndView("test");
            }

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            log.info(iter.toString());
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    // Process the input stream
                    log.info("filename : " + filename);
                    OutputStream out = new FileOutputStream(path + filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                } else {
                    String formFieldValue = Streams.asString(stream);
                    log.info("formFieldValue : " + formFieldValue);
                }
            }
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = (afterTime - beforeTime) / 1000; //두 시간에 차 계산
            System.out.println("시간차이(m) : " + secDiffTime);
        } catch (FileUploadException e) {
            e.printStackTrace();
            log.info("postBulkUpload end");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("postBulkUpload end");
        }
        log.info("postBulkUpload end");
        return new ModelAndView("test");
    }

    @PostMapping("/business-registration")
    public ResponseEntity<String> businessRegistration(@RequestBody Map<String, String> map) {
        Message message = new Message();
        String value = map.get("value");
        System.out.println("value = " + value);
        boolean result = businessRegistrationAPI.isValid(value);
        message.put("status", result);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<String> accountVerify(@RequestBody TokenResponse user_info) {
        Message message = new Message();

        TokenResponse accessToken = accountVerifyAPI.getAccessToken();
        accessToken.setAccount_num(user_info.getAccount_num());
        accessToken.setBirth_date(user_info.getBirth_date());
        accessToken.setBank_type(user_info.getBank_type());
        boolean valid = accountVerifyAPI.isValid(accessToken);

        message.put("status", valid);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @Autowired
    private HashMap<String, PriorityQueue<SplitFileData>> splitFileStorage;
    @Autowired
    private BulkFileService bulkFileService;
    private FileOutputStream fos = null;
    private StringBuilder stringBuilder;
    private String mime_type;

    @GetMapping("/upload/split/general")
    public ModelAndView getBulkSplitUpload() {
        return new ModelAndView("split_file");
    }


    static final int CHUNK_AMOUNT = 20;

    final String SPLIT_WORD = "base64,";

    @ResponseBody
    @PostMapping(value = "/upload/split/general")
    public ResponseEntity<String> splitFileUpload(SplitFileData split) throws JSONException, IOException {
        if (!split.isEof()) {
            if (splitFileStorage.get(split.getFile_name()) != null) {
                stringBuilder = new StringBuilder(new String(split.getFile_data()).trim());
                split.setMime_type(stringBuilder.substring(5, stringBuilder.indexOf(SPLIT_WORD)));
                stringBuilder.delete(0, stringBuilder.indexOf(SPLIT_WORD) + SPLIT_WORD.length());
                split.setFile_data(String.valueOf(stringBuilder).getBytes());
                splitFileStorage.get(split.getFile_name()).add(split);
                if (splitFileStorage.get(split.getFile_name()).size() >= CHUNK_AMOUNT) {
                    /*TODO DB 저장 추가로 진행*/
                    runQueueSystem(split);
                }
            } else {
                PriorityQueue<SplitFileData> priorityQueue = new PriorityQueue<>();
                stringBuilder = new StringBuilder(new String(split.getFile_data()).trim());
                split.setMime_type(stringBuilder.substring(5, stringBuilder.indexOf(SPLIT_WORD)));
                stringBuilder.delete(0, stringBuilder.indexOf(SPLIT_WORD) + SPLIT_WORD.length());
                split.setFile_data(String.valueOf(stringBuilder).getBytes());
                priorityQueue.add(split);
                splitFileStorage.put(split.getFile_name(), priorityQueue);
            }
            stringBuilder.setLength(0);
        } else {
            /*TODO DB 저장 추가로 진행*/
            runQueueSystem(split);
        }
        Message message = new Message();
        return new ResponseEntity(
                DefaultRes.res(
                        StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("ajax")
                ), HttpStatus.OK
        );
    }

    /**
     * runQueueSystem
     * Version information
     * 2022.03.02 1 author : kimwoosik
     * Function Overview
     * Bulk File Upload Queue System
     * Database insert to file blob info
     *
     * @param split : Client to server file blob object
     */
    public void runQueueSystem(SplitFileData split) throws IOException {
        if (fos != null) {
            fos.close();
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        try {
            if (Folder.mkdirs(path)) {
                log.info("{}", "path : " + path + " created");
            }
            SplitFileData temp = null;
            String file_name = null;
            while (splitFileStorage.get(split.getFile_name()) != null && !splitFileStorage.get(split.getFile_name()).isEmpty()) {
                temp = splitFileStorage.get(split.getFile_name()).poll();
                file_name = split.getFile_name().substring(0, temp.getFile_name().lastIndexOf(".")) + "_" + TokenGenerator.RandomIntegerToken(4) + "_b" + temp.getOrder_index() + ".blob";
                fos = new FileOutputStream(path + file_name);
                fos.write(temp.getFile_data());
                jsonObject = new JSONObject();
                jsonObject.put("index", temp.getOrder_index());
                jsonObject.put("file_name", file_name);
                jsonObject.put("file_type", temp.getFile_type());
                jsonObject.put("mime_type", temp.getMime_type());
                jsonArray.put(jsonObject);
            }
            split.setJsonStr(jsonArray.toString());
            if (split.isEof()) {
                /** File Upload End Checking*/
                if (jsonArray.length() != 0) {
                    /** File End But Data is not inserted
                     * insert file data and update file row data ended
                     * */
                    bulkFileService.insertEndFileBulk(split);
                } else {
                    /** File End but Data is Inserted
                     * update file row data ended*/
                }
                bulkFileService.updateEndFileBulk(split);
            } else {
                /** File Not End*/
                bulkFileService.insertFileBulk(split);
            }
            log.info(jsonArray.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (split.isEof()) {
                splitFileStorage.remove(split.getFile_name());
            }
        }
    }

    @GetMapping("/upload/split/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(path + request.getParameter("file"));

        if (new DownloadBuilder().init(response, true)
                .file(file)
                .header()
                .filePush()) {
            /** File download success after process*/
        }
    }

    @GetMapping("/upload/split/bulk/download")
    public void bulkDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String file_name = request.getParameter("file");
        File file = new File(path + file_name);
        DownloadBuilder downloadBuilder = new DownloadBuilder()
                .init(response, true)
                .file(file)
                .header();
        Integer count = bulkFileService.selectDataCountByFileName(file_name); // 몇개의 줄이 있는지
        boolean complete = true;
        SplitFileData splitFileData;
        for (int i = 0; i < count; i++) {
            splitFileData = bulkFileService.selectFileByNameAndIndex(file_name, i * CHUNK_AMOUNT + 1, (i + 1) * CHUNK_AMOUNT);
            if (!downloadBuilder.bulkFilePush(splitFileData, i == count - 1)) { // 실패시 멈춤
                complete = false;
                break;
            }
        }

        if (complete) {
            System.out.println("bulk file download success");
        } else {
            System.out.println("bulk file download fail");
        }
    }

    @GetMapping("/video.do")
    public ModelAndView getVideo() {
        return new ModelAndView("video");
    }

    @Autowired
    private PriorityQueue<SplitFileData> mergeFileStorage;

    @GetMapping("/changevideo.do")
    public void changeVideo() throws IOException {
        log.info("1 ChangeVideo Start");
        OutputStream fileOutputStream = null;
        JSONArray jsonArray = null;
        List<String> read;
        SplitFileData splitFileData = null;
        /** File Upload Queue가 비었을 때 진행 : Scheduling에서 지속적 확인*/
        if (splitFileStorage.isEmpty()) {
            log.info("2 SplitFileStorage is empty start db scan");
            if (mergeFileStorage.isEmpty()) {
                log.info("3 MergeFileStorage is empty");
                /** File Merge Queue가 비었을 때 실행
                 * 1. DB 스캔 오래된 file_bulk를 찾는다, end = 1인것들하고 가장 오래된것들, JsonStr 제외하고 찿아온다
                 * 2. ArrayList<SplitFileData>()를 돌려서 Merge Queue로 넣는다.
                 * 3. Merge Queue를 돌린다.
                 * */
                SplitFileData data = bulkFileService.selectInsertQueue();
                ArrayList<SplitFileData> splits = bulkFileService.selectFileByName(data.getFile_name());
                log.info("4 MergeFileStorage insert start");
                for (SplitFileData queueItem : splits) {
                    log.info("5 MergeFileStorage insert item");
                    mergeFileStorage.add(queueItem);
                }
                /** File Merge Queue가 비지 않았다면 File Merge 그대로 실행*/
                if (!mergeFileStorage.isEmpty()) {
                    log.info("6 MergeFileStorage is peek");
                    splitFileData = mergeFileStorage.peek();
                    fileOutputStream = new FileOutputStream(new File(path + splitFileData.getFile_name()));
                    /** File Merge Queue가 비지 않았다면 File Merge 그대로 실행*/
                    while (!mergeFileStorage.isEmpty()) {
                        log.info("7 splitFileStorage while start");
                        /** File Upload Queue가 비지 않았다면 진행 불가*/
                        if (!splitFileStorage.isEmpty()) {
                            log.info("8 splitFileStorage is empty close init");
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            jsonArray = null;
                            splitFileData = null;
                            read = null;
                            log.info("9 Break");
                            break;
                        } else {
                            log.info("10 splitFileStorage is not empty write file start for 1~20 blob");
                            splitFileData = bulkFileService.selectFileByNo(splitFileData.getNo());
                            jsonArray = new JSONArray(splitFileData.getJsonStr());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                read = Files.readAllLines(Paths.get(path + jsonArray.getJSONObject(i).getString("file_name")));
                                for (String s : read) {
                                    fileOutputStream.write(Base64.getDecoder().decode(s));
                                }
                            }
                            log.info("11 queue poll");
                            if (mergeFileStorage.poll() != null) {
                                bulkFileService.updateCompleteFileBulk(splitFileData);
                            }
                        }
                    }
                }
            } else {
                log.info("12 MergeFileStorage is not Empty");
                splitFileData = mergeFileStorage.peek();
                fileOutputStream = new FileOutputStream(new File(path + splitFileData.getFile_name()));
                /** File Merge Queue가 비지 않았다면 File Merge 그대로 실행*/
                while (!mergeFileStorage.isEmpty()) {
                    /** File Upload Queue가 비지 않았다면 진행 불가*/
                    if (!splitFileStorage.isEmpty()) {
                        log.info("13 splitFileStorage is empty close init");
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                        jsonArray = null;
                        splitFileData = null;
                        read = null;
                        log.info("14 Break");
                        break;
                    } else {
                        log.info("15 splitFileStorage is not empty write file start for 1~20 blob");
                        splitFileData = bulkFileService.selectFileByNo(splitFileData.getNo());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            read = Files.readAllLines(Paths.get(path + jsonArray.getJSONObject(i).getString("file_name")));
                            for (String s : read) {
                                fileOutputStream.write(Base64.getDecoder().decode(s));
                            }
                        }
                        log.info("16 queue poll");
                        if (mergeFileStorage.poll() != null) {
                            bulkFileService.updateCompleteFileBulk(splitFileData);
                        }
                    }
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            jsonArray = null;
            splitFileData = null;
            read = null;
        } else {
            log.info("17 SplitFileStorage is not empty ChangeVideo End");
        }
    }
}
