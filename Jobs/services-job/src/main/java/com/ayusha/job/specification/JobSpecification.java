
package com.ayusha.job.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ayusha.job.services.entity.JobEntity;

public class JobSpecification implements Specification<JobEntity> {
	private static final long serialVersionUID = 1L;

	String userSearch;

	public JobSpecification(String userSearch) {
		super();
		this.userSearch = userSearch;
	}

	@Override
	public Predicate toPredicate(Root<JobEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> searchFilterPredicates = new ArrayList<>();
		if (userSearch != null) {

			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("jobId")), containsLikePattern));
		}
		if (userSearch != null) {
			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("userId")), containsLikePattern));
			// searchFilterPredicates.add(cb.between("", "", ""), containsLikePattern));
		}
		if (userSearch != null) {

			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("status")), containsLikePattern));
		}
		if (userSearch != null) {

			String containsLikePattern = getContainsLikePattern(userSearch.trim());
			searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("customerName")), containsLikePattern));
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
