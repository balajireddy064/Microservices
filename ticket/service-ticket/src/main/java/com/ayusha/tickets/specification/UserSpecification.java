package com.ayusha.tickets.specification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ayusha.tickets.entity.TicketEntity;

public class UserSpecification implements Specification<TicketEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String userSearch;

	public UserSpecification(String userSearch) {
		super();
		this.userSearch = userSearch;
	}

	private Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	@Override
	public Predicate toPredicate(Root<TicketEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub

		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> searchFilterPredicates = new ArrayList<>();

		if (userSearch != null) {

//			String containsLikePattern = getContainsLikePattern(userSearch.trim());
//			// searchFilterPredicates.add(cb.like(cb.lower(root.<String>get("firstName")),
//			// containsLikePattern));
//			searchFilterPredicates.add(cb.between(null, yesterday(),
//					userSearch)(root.<String>get("visitDate")));

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
