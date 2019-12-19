package com.ayusha.job.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ayusha.payments.services.entity.EstimateEntity;

public class EstimateSearchSpecificatoin implements Specification<EstimateEntity> {
	private static final long serialVersionUID = 1L;

	String userSearch;

	public EstimateSearchSpecificatoin(String userSearch) {
		super();
		this.userSearch = userSearch;
	}

	@Override
	public Predicate toPredicate(Root<EstimateEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> searchFilterPredicates = new ArrayList<>();
		if (userSearch != null) {

			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("jobCode")), containsLikePattern));
		}
		if (userSearch != null) {
			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("requestedBy")), containsLikePattern));
			// searchFilterPredicates.add(cb.between("", "", ""), containsLikePattern));
		}
		Predicate mandatoryPredicates = null;
		Predicate searchPredicates = null;
		if (!predicates.isEmpty()) {
			mandatoryPredicates = cb.and(predicates.toArray(new Predicate[] {}));
		}
		if (!searchFilterPredicates.isEmpty()) {
			searchPredicates = cb.or(searchFilterPredicates.toArray(new Predicate[] {}));
		}
		if (null == mandatoryPredicates) {
			return searchPredicates;
		} else if (null == searchFilterPredicates) {
			return mandatoryPredicates;
		} else {
			return cb.and(mandatoryPredicates, searchPredicates);
		}
	}

	private static String getContainsLikePattern(String searchTern) {
		if (searchTern == null || searchTern.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTern.toLowerCase() + "%";
		}
	}

}
