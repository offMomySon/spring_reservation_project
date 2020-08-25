package kr.or.connect.reservation.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.repository.PromotionRepository;
import kr.or.connect.reservation.service.impl.PromotionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PromotionServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImplTest.class);
	@Mock
	private PromotionRepository promotionRep;

	@InjectMocks
	private PromotionServiceImpl promotionServiceImpl;

	@Test
	public void test() {
		List<PromotionRs> promotionRsList = new ArrayList<>();
		promotionRsList.add(new PromotionRs(1, 1, "A"));
		promotionRsList.add(new PromotionRs(2, 2, "B"));
		logger.info("promotionRsList = {}", promotionRsList);
		
		when(promotionRep.selectAll()).thenReturn(promotionRsList);
		logger.info("promotionRep.selectAll() = {}", promotionRep.selectAll());
		
		assertThat(promotionRep.selectAll(), is(promotionRsList));
	}
}
