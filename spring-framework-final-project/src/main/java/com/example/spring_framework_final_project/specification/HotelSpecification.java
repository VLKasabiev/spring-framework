package com.example.spring_framework_final_project.specification;

import com.example.spring_framework_final_project.entities.Hotel;
import com.example.spring_framework_final_project.filters.HotelFilter;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {
    static Specification<Hotel> withFilter(HotelFilter filter) {
        return Specification.where(byId(filter.getId()))
                .and(byHotelname(filter.getHotelname()))
                .and(byTitle(filter.getTitle()))
                .and(byCity(filter.getCity()))
                .and(byAddress(filter.getAddress()))
                .and(byDistanceFromCentre(filter.getMaxDistanceFromCentre()))
                .and(byRating(filter.getMinRating()))
                .and(byNumberOfRating(filter.getMaxNumberOfRatings()));
    }

    static Specification<Hotel> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    static Specification<Hotel> byHotelname(String hotelname) {
        return (root, query, criteriaBuilder) -> {
            if (hotelname == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("hotelname"), hotelname);
        };
    }

    static Specification<Hotel> byTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("title"), title);
        };
    }

    static Specification<Hotel> byCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("city"), city);
        };
    }

    static Specification<Hotel> byAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("address"), address);
        };
    }

    static Specification<Hotel> byDistanceFromCentre(Double maxDistanceFromCentre) {
        return (root, query, criteriaBuilder) -> {
            if (maxDistanceFromCentre == null) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("distanceFromCentre"), maxDistanceFromCentre);
        };
    }

    static Specification<Hotel> byRating(Double minRating) {
        return (root, query, criteriaBuilder) -> {
            if (minRating == null) {
                return null;
            }

            return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
        };
    }

    static Specification<Hotel> byNumberOfRating(Long maxNumberOfRatings) {
        return (root, query, criteriaBuilder) -> {
            if (maxNumberOfRatings == null) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("numberOfRatings"), maxNumberOfRatings);
        };
    }
}
