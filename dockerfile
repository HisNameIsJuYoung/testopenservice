# base 이미지 설정
FROM rockylinux:9

# jar 파일 위치를 변수로 설정
ARG JAR_FILE=./target/testopenservice-0.0.1-SNAPSHOT.jar

# 환경변수 설정
# ENV CUSTOM_NAME default

# jar 파일을 컨테이너 내부에 복사
COPY ${JAR_FILE} TestOpenService.jar

# 외부 호스트 8080 포트로 노출
EXPOSE 18081

# 실행 명령어
RUN dnf update -y
RUN dnf install java-17-openjdk -y
ENTRYPOINT ["java", "-jar", "TestOpenService.jar"]
