package com.epam.resourceservice.dto;

import java.util.List;

public class ResourceItemIdsDto {

    private List<Integer> ids;

    public ResourceItemIdsDto(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
