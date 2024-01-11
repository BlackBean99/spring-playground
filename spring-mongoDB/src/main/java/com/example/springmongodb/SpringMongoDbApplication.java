package com.example.springmongodb;

import com.example.springmongodb.domain.Applicant;
import com.example.springmongodb.domain.ApplicantMongoDBRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringMongoDbApplication implements CommandLineRunner {
  private final ApplicantMongoDBRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringMongoDbApplication.class, args);
  }

  @PostConstruct
  public void started() {
    // timezone UTC 셋팅
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
  }
  @Override
  public void run(String... args) throws Exception {
    Map<String, Object> qna = new HashMap<>();
    qna.put("year", 24);
    qna.put("name", "이서현");
    qna.put("니가 이 문제를 풀 수 있을까?", "아니요");
    qna.put("당신은 누구입니까?", "이서현2");
    qna.put("temporary", "temporary");
    qna.put("date", LocalDateTime.now());
    repository.save(new Applicant("2", 2021, qna));
    List<Applicant> byYear = repository.findByYear(2021);
    System.out.println(byYear.get(0).getYear());
  }
}
