package kr.or.connect.reservation.service;

import java.util.List;

import javax.persistence.Tuple;

import org.javatuples.Pair;

import kr.or.connect.reservation.model.Category;

public interface CategoryService {
	public List<Tuple> getCategoryList();
}
