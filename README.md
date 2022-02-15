#Spring Project v a.2

##프로젝트 개발 일정 (0000-00-00 ~ 0000-00-00)
##Github Action Test
자동화 빌드 시스템 구축
##Github 프로젝트 소스코드 관리 규칙

`Feature` 는 실제로 협업에서 개발자 한명이 Task를 나누어 개발에 들어가는 Module Branch입니다.

`Feature` 는 `Develop` Branch로 머지됩니다.

`Develop` 은 `Feature` 들이모여 이뤄집니다.

`Develop` 에서 어느정도 개발이 완료되어 배포할 때에 `Master` Branch로 머지되고 자체 내부 개발자들의 테스트를 거쳐 `Deploy` Branch로 머지됩니다.

`Master` Branch는 협업자들이 모여서 내부 테스트를 하는 구간이며 테스트를 통과할 시에 `Deploy` Branch로 머지됩니다.

`Deploy` Branch는 배포버전을 위한 코드이며 QA를 진행함과 동시에 Bugfix를 진행합니다.
해당 Release는 QA 또는 기능적 분기에 따라 TAG를 만들어 배포버전을 관리합니다.

`Hotfix` Branch는 배포버전(`Deploy`)에서 문제가 발생하였을 경우 수정하는 Branch이며 `Master` 에서 분기하여 `Develop` 과 `Master` Branch에 머지됩니다.

주의사항

1. `Feature` Branch는 해당하는 Commit들을 하나로 합쳐서 `Develop` Branch에 머지합니다.
2. `Hotfix` Branch가 생성되는 경우 `Feature` Branch들은 `Develop` Branch에 머지가 불가능합니다.
3. `Develop` Branch에서 `Master` Branch로는 PM 또는 개발 관리자 본인만 가능하다. 단, 판단하에 기본의 개발자들도 머지가 가능합니다.
4. `Master` Branch에서 `Deploy` Branch로는 PM 또는 개발 관리자 본인만 가능하다. 단, 판단하에 기본의 개발자들도 머지가 가능합니다.
5. `Deploy` Branch가 다른 Branch에 의해 머지가 되었다면 해당 배포 버전의 TAG를 완성합니다.
6. `Feature` 와 `Hotfix` 는 이름이 안겹치게 다음과 같이 명명합니다. `feature/name` `hotfix/name`
##
# 주석 명세

---

주석은 필수가 아닌 선택사항입니다.

하지만 여러명에서 개발을 할 때 의사소통의 부재가 있을 수 있으니 가능하면 주석으로 다른 개발자에게 설명하도록 합니다.

이에 그 사용성과 효용성을 고려할 때 어려운 기능단위 코드나 클래스 단위 코드에서 남겨주도록 합니다.

또한 자신이 개발한 코드를 해당 프로젝트에서 다른 개발자들이 많이 사용할 수 있는 가능성이 있는 경우 꼭 작성하도록 합니다.

## Javascript

다음은 Javascript의 주석 처리입니다.

Description은 간단한 함수의 기능을 설명합니다.

Prerequisite은 선행 조건. 사용시, 우선시 되야할 조건이나 Parameter의 상태 및 web의 상태 명시

Parameter는 안적어도 무방합니다.

Return은 안적어도 무방합니다.

Date는 최초 작성 날짜로서 안적어도 무방합니다.

Updated Date는 필수 작성 요소입니다.(다른 사람과 의사소통 할 때 반드시 사용 되어야 할 필수적인 요소입니다) 또한 어떤것을 변경 했는지 간단하게 적습니다.

Version은 +1씩 올립니다.

따라서 Description, Prerequisite, Updated Date, Version은 필수적으로 사용해야 합니다.

```
/**
 * Description : GPS로 현재 위치 찾아서 setMap() 해주는 함수
 * Prerequisite : Browser가 GPS를 지원 해야합니다.
 * Parameter : Non (Not Exist)
 * Return : Non
 * Date : 2020-10-12
 * Updated Date : 2020-10-16 position object 변경
 * Updated Date : 2020-10-20 error 처리 변경
 * Version : 2
 * */
function getLocation() {
    if (navigator.geolocation) { // GPS를 지원하면 //해당 주석은 로직상 분기를 가질 때 사용합니다. 사용 안해도 무방합니다.
        navigator.geolocation.getCurrentPosition(function (position) {
            /* alert(position.coords.latitude + ' ' + position.coords.longitude);*/ //해당 주석은 생성 및 남겨 놓으셔도 무방합니다. 사용 안해도 무방합니다.
            setMap(position.coords.latitude, position.coords.longitude, null);
        }, function (error) {
            console.error(error);
        }, {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity
        });
    } else {
        alert('GPS를 지원하지 않습니다');
    }
}
```

## Java

Description, Updated date, Version은 필수적으로 작성 해야 합니다.

```
/**
 * @param : req (설명)
 * @return : Non (설명)
 * Description : Login API를 이용하여 로그인 했을 때 결과를 받는 함수
 * Date : 2020-10-10
 * Updated date : 2020-10-12 Kakao Login 추가
 * Updated date : 2020-10-13 Naver Login 추가
 * Version : 3
 * */
public void apiLoginInit(HttpServletRequest req) throws NoSuchAlgorithmException {
}
```

# URL 설정

---

## URL 설정 규칙

서버의 URL은 기본적으로 Contoller를 거치는 View들은 .do를 붙입니다.

Spring + JSP 의 특징을 모두 가져가기 위한 조치이니 불편하더라도 URL에 .do를 사용하여 개발을 합니다. (단, App Server REST API는 제외)

> Ex ) http:localhost:8080/home.do
Ex ) `App Server` http:localhost:8080/api/testapi
> 

또한 Ajax는 Controller를 거치지만 View가 미동하는 특수한 경우입니다. 사용을 자제하되 해야될 부분은 /ajax/ajaxname.do를 붙여서 사용하도록 합니다.

ajax의 낮은 보안성을 높여주기위한 조치이니 감수해주시길 바랍니다.

> Ex) http:localhost:8080/ajax/ajaxname.do
> 

관리자 페이지의 경우 높은 보안성이 요구되는 특수한 경우입니다.

따라서 관리자 페이지는 .admin으로 붙여서 진행을 하도록 합니다.

> Ex) http://localhost:8080/admin/dashboard.do
> 

### URL 계층

페이지의 계층 별로 URL의 설정을 다르게 합니다. 

아래는 예제 샘플입니다.

- /services/service.do
- /services/service/detail.do
- /user/profile.do
- /user/update.do
- /user/boards/board.do
- /user/boards/write.do
- /user/boards.do
- /auth/signin.do
- /auth/signup.do
- /home.do

## 언어 관련 설정

언어 패키징 작업시 URL 디렉토리에 ko(korean),en(english) 등를 붙여 해당 [Message.properties](http://message.properties)를 불러와 언어 패키징을 진행하도록 합니다.

> Ex) http://localhost:8080/ko/auth/login.do, http://localhost:8080/en/home.do
> 

## 테스트 관련 설정

테스트 관련된 URL은 뒤에 .test를 붙여서 테스트 라는걸 표현한다.

> Ex) http://localhost:8080/map.test (map.do → X)
>
>
#Project Setting
##applicationContext.xml
```<?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--공동 개발자1-->
      <bean id="LocalSource1" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url"
                    value="jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&amp;allowMultiQueries=true&amp;autoReconnect=true"/>
          <property name="username" value="test"/>
          <property name="password" value="test"/>
          <!--Connection Pool의 Connection이 뒤졋는지 살았는지 체크-->
          <property name="validationQuery" value="select 1"/>
          <property name="initialSize" value="4"/>
          <property name="maxTotal" value="4"/>
          <property name="maxIdle" value="4"/>
          <property name="minIdle" value="4"/>
      </bean>
  
      <!--공동 개발자2-->
      <bean id="LocalSource2" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url"
                    value="jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&amp;allowMultiQueries=true&amp;autoReconnect=true"/>
          <property name="username" value="test"/>
          <property name="password" value="test"/>
          <!--Connection Pool의 Connection이 뒤졋는지 살았는지 체크-->
          <property name="validationQuery" value="select 1"/>
          <property name="initialSize" value="4"/>
          <property name="maxTotal" value="4"/>
          <property name="maxIdle" value="4"/>
          <property name="minIdle" value="4"/>
      </bean>
  
      <!--배포 전용 설정-->
      <bean id="DeploySource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url"
                    value="jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&amp;allowMultiQueries=true&amp;autoReconnect=true"/>
          <property name="username" value="test"/>
          <property name="password" value="test"/>
          <!--Connection Pool의 Connection이 뒤졋는지 살았는지 체크-->
          <property name="validationQuery" value="select 1"/>
  
          <property name="initialSize" value="10"/>
          <property name="maxTotal" value="30"/>
          <property name="maxIdle" value="30"/>
          <property name="minIdle" value="5"/>
      </bean>
  
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <property name="dataSource" ref="LocalSource1"/>
          <property name="configLocation" value="classpath:/mybatis-config.xml"/>
          <property name="mapperLocations" value="classpath:/sqls/*.xml"/>
      </bean>
  
      <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate" destroy-method="clearCache">
          <constructor-arg ref="sqlSessionFactory"></constructor-arg>
      </bean>
  
      <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
          <constructor-arg ref="LocalSource1"/>
      </bean>
  </beans>
```
##Dispatcher-servlet.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd">

    <!-- Annotation Uses-->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <!-- @ResponseBody로 String 처리할때 한글처리 -->
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!-- Base Back java -->
    <context:component-scan base-package="com">
        <context:include-filter type="annotation" expression="org.springframework.context.annotation.Configuration"/>
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--URI Mapping-->
    <bean id="handlerMapping" class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <mvc:resources mapping="/resources/**" location="/resources/"/>
    <mvc:resources mapping="/favicon.ico" location="/favicon.ico"/>
    <mvc:resources mapping="/robots.txt" location="/"/>
    <mvc:resources mapping="/sitemap.xml" location="/"/>

    <!--View Resolver-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="order" value="1"/>
        <property name="prefix" value="/WEB-INF/view/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

    <!--BeanNameView Resolver (View가 없을 때의 Resolver)-->
    <bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
        <property name="order" value="0"/>
    </bean>

    <!--Download-->
    <bean id="download" class="com.util.FileDownload"/>

    <!--Upload-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="20000000"/> <!-- 파일들 전체 20Mb 제한 -->
        <property name="maxUploadSizePerFile" value="5000000"/> <!--파일당 5Mb 제한-->
        <property name="defaultEncoding" value="utf-8"/>
    </bean>

    <!--Interceptors-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--모든 경로에 Intercept 설정-->
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/resources/**"/>
            <bean class="com.interceptor.BaseInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

    <!--Scheduling Module Driven-->
    <task:annotation-driven/>
</beans>
```
##aws.properties
```
AWSModel.accessKey=testproperties
AWSModel.secretKey=testproperties
AWSModel.bucketName=testproperties
```
##log4j.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <!-- console log -->
    <appender name="console" class="org.apache.log4j.ConsoleAppender">
        <param name="Target" value="System.out"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="[%d{yyyy-MM-dd HH:mm:ss}] %-5p: %c - %m%n"/>
        </layout>
    </appender>

    <!-- 3rdparty Loggers -->
    <logger name="org.springframework.core">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.beans">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.context">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.web">
        <level value="debug"/>
    </logger>
    <logger name="com.mapper">
        <level value="debug"/>
    </logger>
    <logger name="org.mybatis.spring.SqlSessionUtils">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.jdbc">
        <level value="info"/>
    </logger>
    <logger name="org.mybatis.spring.transaction.SpringManagedTransaction">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.web.servlet.DispatcherServlet">
        <level value="info"/>
    </logger>
    <logger name="org.springframework.web.servlet.view.InternalResourceView">
        <level value="info"></level>
    </logger>
    <logger name="com.interceptor.BaseInterceptor">
        <level value="info"></level>
    </logger>
    <logger name="org.springframework.web.servlet.view.JstlView">
        <level value="info"></level>
    </logger>
    <logger name="com.filter.GeneralFilter">
        <level value="info"></level>
    </logger>
    <!-- Root Logger -->
    <root>
        <priority value="debug"/>
        <appender-ref ref="console"/>
    </root>
</log4j:configuration>
```
##path.properties
```
filepath=C:/Users/zlzld/OneDrive/Desktop/projects/webapplication/out/artifacts/webapplication_war_exploded/file/
domain=http://localhost:8080/
resources=http://localhost:8080/resources/
```
##sns.properties
```
google.client_id=googleclientid
google.client_secret=googleclientsecret
kakao.client_id=kakaoclientsecret
kakao.pay_key=kakaopaykey
naver.client_id=naverclientid
naver.client_secret=naverclientsecret
```
##library
web/WEB-INF/lib/cos.jar, web/WEB-INF/lib/javax.mail.jar import 필수
