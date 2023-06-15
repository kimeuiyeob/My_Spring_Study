# Travel Club Project

# Study Spring

Model2(MVC- model view controller)
JSP, 서브릿(Servlet),로직을 위한 Java class로 나뉘어 처리
MVC(Model-View-Controller)패턴을 웹에 도입한 형태
코드가 간단하고 뷰와 로직처리 분업이 가능


Spring MVC pattern 처리 과정
client url 요청
-> DispatcherServlet이 받음(web.xml에서 설정)
-> DispatcherServlet이 HandlerMapping으로 전달(servlet-context.xml에서 @controller을 스캔)
-> Controller에서 URL에 해당하는 @ReuqestMapping 찾아감
-> Controller은 해당 메소드에서 처리 하는 과정에서 Service를 호출(@Inject XXService)
->Service는 @Inject(DI)로 주입 받고 구현 된 필요 로직을 처리하고 DAO를 주입 받아 데이터 베이스 처리를 위임(@Inject XXDAO)
-> DAo가 database에 쿼리 전달해서 CRUD 함
->database에서부터 return으로 DAO >> Service >> Controller
-> Model객체에 담아 전달 할 페이지(JSP)로 데이터를 전달
-> DispatcherServlet이 ViewResoler에게 페이지 정보를 찾으라고 하고 JSP를 찾아 DispatcherServlet에게 알려줌(servlet-context.xml에서 suffix, prefix하여 /WEB-INF/views/*.jsp 처리 설정을 ViewResolver가 함)
-> Dispatcher가 view에게 전달하여 페이지를 랜딩하고 데이터를 표출(el 언어 사용)


Spring boot
Spring 프로젝트가 제공하는 다양한 라이브러리와 프레임워크로 빨리게 사용할 수 있게 하는 프레임워크.
maven은 pom.xml, gradle은 build.gradle 파일 내용에 따라 클래스 패스.어노데이션, 기타 자바구성클래스를 보고 자동 구성함(현재 나는 maven 사용하고 있음)
spring framework를 더 사용하기 편하게 만든 것

자주 사용하는 프로젝트 조합을 미리 만들어 놓음
일일히 라이브러리 찾으며 추가할 필요 없이 maven에 dependency추가만 하면 라이브러리 설치가 자동으로 됨(jsp에서 cos.jar같은 것 설치했던 것 처럼 할 필요 없음)

설정을 자동으로 수행함
스프링프레임워크에서 필요했던 설정파일이 필요 없어짐

서버를 포함하고 있음
spring 버전 4부터 spring boot라고 불리는데 여기부터 tomcat 서버를 포함하고 있음. 톰캣 설치하고 이클립스에 연동하는 작업이 필요없음

Framework && Library
Framework -> 소프트웨어의 설계와 구현을 재사용이 가능하도록 협업화된 형태로 클래스 제공
Library -> 자주 사용되는 로직을 재사용가능하도록 정리한 코드의 집합

EJB(Enterprise JavaBean)
단점: 객체지향적인 특징과 장점을 포기해야함 -> EJB 빈은 상속과 다형성등의 혜택을 제대로 누릴 수 없음
장점: 엔터프라이즈 서비스들과 애플리케이션 코드를 분리해서 독립적인 서비스로 사용할 수 있게 만들어줌

-> 모든 코드를 하나에 때려넣는게 아니라 나누어서 구현해야 함. 서로 종속되지 않게 하고 팔요한 것은 setter를 통해 받아와서 사용하는 것이 좋음

POJO 특징
특정 규약(contract)에 종속되지 않음(Java 언어와 꼭 필요한 API 외에 종속되지 않는다.)
특정 환경에 종속되지 않음
객체지향원리에 충실
POJO를 사용하는 이유
코드의 간결함 (비즈니스 로직과 특정 환경/low 레벨 종속적인 코드를 분리)
자동화 테스트에 유리
객체지향적 설계의 자유로운 사용
POJO 프레임워크
POJO를 이용한 애플리케이션 개발이 가진 특징과 장점을 그대로 살리면서 EJB에서 제공하는 엔터프라이즈 서비스와 기술을 그대로 사용할 수 있도록 도와주는 프레임워크
-> spring 프레임워크도 POJO 프레임 워크중 하나

IoC(Inversion of Control)
프레임워크 기반의 개발에서는 프레임워크가 필요할 때마다 애플리케이션 코드를 호출하여 사용하는데
이 때 제어권을 가지는 것이 컨테이너(Container)이다.
객체에 대한 제어권을 컨테이너가 가지고 객체의 생성부터 생명주기 관리 등을 컨테이너가 맡아서 하면서
제어권의 흐름이 개발자에서 컨테이너로 바뀌었다고 해서 IoC(Inversion of Control)라고 부름

IOC 용어
bean : 스프링에서 제어권을 가지고 직접 만들어 관계를 부여하는 오브젝트(스프링 컨테이너가 생성하고 관계설정, 사용을 제어해주는 오브젝트)

bean factory : 스프링의 IoC를 담당하는 핵심 컨테이너
Bean을 등록/생성/조회/반환/관리 (보통 bean factory를 바로 사용하지 않고 이를 확장한 application context를 이용)
BeanFactory는 bean factory가 구현하는 interface이다. (getBean()등의 메서드가 정의되어 있다.)

application context : bean factory를 확장한 IoC 컨테이너
bean factory에 추가로 spring의 각종 부가 서비스를 제공ApplicationContext는 application context가 구현해야 하는 interface, BeanFactory를 상속

configuration metadata : application context 혹은 bean factory가 IoC를 적용하기 위해 사용하는 메타정보
스프링의 설정정보는 컨테이너에 어떤 기능을 세팅하거나 조정하는 경우에도 사용하지만 주로 bean을 생성/구성하는 용도로 사용

container (ioC container) : IoC 방식으로 bean을 관리한다는 의미(bean factory나 application context를 가리킴)
하나의 애플리케이션에 보통 여러개의 ApplicationContext 객체가 만들어짐-> 모두 합해서 spring container라고 부를 수 있다.

DI(3 types)
의존성이 삽입된다는 의미로 IoC를 DI라는 표현으로 사용
1. 생성자를 이용한 의존성 주입
필요한 의존성을 모두 포함하는 클래스의 생성자를 만들고 그 생성자를 통해 의존성을 주입
2. setter메소드를 이용한 의존성 주입
의존성을 입력받는 세터(setter) 메소드를 만들고 이를 통해 의존성을 주입
3. 초기화 인터페이스를 이용한 의존성 주입
의존성을 주입하는 함수를 포함한 인터페이스를 작성하고 이 인터페이스를 구현하도록 함으로써 실행시에 이를 통하여 의존성을 주입

-> 이중에서 스프링은 3번을 지원하지 않고 1,2 중에 2번을 권장한다.

AOP(Aspect Oriented Programming)
관점 지향 프로그래밍
: 어떤 로직을 핵심적 관점,부가적 관점으로 나눠서 보고 그 관점을 기준으로 각각 모듈화(공통된 로직이나 기능을 하나의 단위로 묶는 것)하겠다는 것
->Crosscutting Concerns를 Aspect로 모듈화 하고 핵심적인 비즈니스 로직에서 분리하여 재사용하겠다는 취지

Crosscutting Concerns: 다른 부분에 계속 반복해서 쓰는 코드

Aspect : Crosscutting Concerns를 모듈화 한 것. 주로 부가기능을 모듈화함.
Target : Aspect를 적용하는 곳 (클래스, 메서드 .. )
Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
PointCut : JointPoint의 상세한 스펙을 정의한 것. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음

advice 실행시점을 지정하는 어노테이션
@Before (이전) : 어드바이스 타겟 메소드가 호출되기 전
@After (이후) : 타겟 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 타겟 메소드 완료 후
@AfterReturning : (정상적 반환 이후)타겟 메소드가 성공적으로 결과값을 반환 후
@AfterThrowing (예외 발생 이후) : 타겟 메소드가 수행 중 예외 발생 후
@Around (메소드 실행 전후) : 어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 실행

DTO(Data Transfer Object)
계층간 데이터 교환을 위한 객체(Java Beans)
DB에서 데이터를 얻어 Service나 Controller 등으터 보낼 때 사용하는 객체
로직을 갖고 있지 않는 순수한 데이터 객체 (getter/setter 메서드만 가짐)
DB에서 꺼낸 값을 임의로 변경할 필요가 없기 때문에 DTO클래스에는 setter 없음

DAO(Data Access Object)
실제로 DB에 접근하는 객체
Service와 DB를 연결하는 고리의 역할
SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공
