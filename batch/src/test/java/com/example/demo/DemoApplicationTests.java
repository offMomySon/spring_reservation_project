package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan("kr.or.connect.reservation.core.presentation.domain")
class DemoApplicationTests {

}
