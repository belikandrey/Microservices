package com.epam.resourceservice.repository;

import com.epam.resourceservice.entity.ResourceItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<ResourceItem, Integer> {
    List<ResourceItem> deleteAllByIdIn(List<Integer> ids);
}
