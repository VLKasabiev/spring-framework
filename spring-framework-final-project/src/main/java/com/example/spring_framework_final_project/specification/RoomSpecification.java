package com.example.spring_framework_final_project.specification;

import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.entities.UnavailableDate;
import com.example.spring_framework_final_project.filters.RoomFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter filter) {
        return Specification.where(byId(filter.getId()))
                .and(byName(filter.getName()))
                .and(byCostRange(filter.getMinCost(), filter.getMaxCost()))
                .and(byMaxOccupancy(filter.getMaxOccupancy()))
                .and(byDate(filter.getArrivalDate(), filter.getDepartureDate()))
                .and(byHotelId(filter.getHotelId()));
    }

    static Specification<Room> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    static Specification<Room> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    static Specification<Room> byCostRange(Long minCost, Long maxCost) {
        return (root, query, criteriaBuilder) -> {
            if (minCost == null && maxCost == null) {
                return null;
            }
            if (minCost == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
            }
            if (maxCost == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
            }

            return criteriaBuilder.between(root.get("cost"), minCost, maxCost);
        };
    }

    static Specification<Room> byMaxOccupancy(Long maxOccupancy) {
        return (root, query, criteriaBuilder) -> {
            if (maxOccupancy == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("maxOccupancy"), maxOccupancy);
        };
    }

static Specification<Room> byDate(LocalDate arrivalDate, LocalDate departureDate) {
    return (root, query, criteriaBuilder) -> {
        if (arrivalDate == null && departureDate == null) {
            return null;
        }

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<UnavailableDate> unavailableDateRoot = subquery.from(UnavailableDate.class);
        subquery.select(criteriaBuilder.count(unavailableDateRoot));
        subquery.where(criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(unavailableDateRoot.get("arrivalDate"), departureDate),
                criteriaBuilder.greaterThanOrEqualTo(unavailableDateRoot.get("departureDate"), arrivalDate),
                criteriaBuilder.equal(unavailableDateRoot.get("room"), root)
        ));

        return criteriaBuilder.or(
                criteriaBuilder.equal(subquery, 0L),
                criteriaBuilder.isEmpty(root.get("unavailableDates"))
        );
    };
}

    static Specification<Room> byHotelId(Long hotelId) {
        return (root, query, criteriaBuilder) -> {
            if (hotelId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("hotel").get("id"), hotelId);
        };
    }
}
